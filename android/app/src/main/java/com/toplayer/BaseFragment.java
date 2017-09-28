package com.toplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.toplayer.store.ColorStore;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class BaseFragment extends Fragment {
    public final static String TAG = BaseFragment.class.getSimpleName();

    private View view;
    private TextView textView;
    private Button toggleButton;
    private Button colorButton;
    private CompositeDisposable subscriptions = new CompositeDisposable();
    String BTN_COLOR = "green";

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.base_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.text);

        toggleButton = (Button) view.findViewById(R.id.btnToggle);
        toggleButton.setOnClickListener(ignored -> toggleButton.setText((toggleButton.getText() == "Hide") ? "Show" : "Hide"));
        toggleButton.setText("Hide");

        colorButton = (Button) view.findViewById(R.id.btnColor);
        colorButton.setOnClickListener(ignored -> ColorStore.setColor(BTN_COLOR));
        ColorStore.getColorObservable()
            .doOnSubscribe(subscriptions::add)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(s -> {
                Log.d("NativeFrag: onNext", s);
                //NP: why do we need this check? most likely redundant
                if (getView() == null) {
                    Log.e("NativeFrag: onNext: ", "no View");
                    return;
                }
                colorButton.setEnabled(!s.equals(BTN_COLOR));
            });

        return view;
    }

    @Override
    public void onDestroyView() {
        subscriptions.dispose();
        super.onDestroyView();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        view.dispatchTouchEvent(ev);
//        textView.setText(textView.getText() + "\n" + ev.toString() + (view.dispatchTouchEvent(ev) ? "HANDLED" : ""));
        return false;
    }

}
