package com.toplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.toplayer.store.ColorStore;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BaseFragment extends Fragment {
    private View view;
    Disposable subscription;
    String BTN_COLOR = "green";
    Observer<String> colorObserver = new Observer<String>() {

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            subscription = d;
        }

        @Override
        public void onNext(@NonNull String s) {
            Log.d("NativeFrag: onNext", s);
            View viewIn = getView();
            if (viewIn == null) {
                Log.e("NativeFrag: onNext: ", "no View");
                return;
            }
            if (s.equals(BTN_COLOR)) {
                viewIn.findViewById(R.id.btnColor).setEnabled(false);
            } else {
                viewIn.findViewById(R.id.btnColor).setEnabled(true);
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {}

        @Override
        public void onComplete() {}
    };

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.base_fragment, container, false);

        final Button btnToggle = (Button) view.findViewById(R.id.btnToggle);
        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button me = (Button) view.findViewById(R.id.btnToggle);
                me.setText((btnToggle.getText() == "Hide") ? "Show" : "Hide");
            }
        });
        btnToggle.setText("Hide");

        final Button btnChangeColor = (Button) view.findViewById(R.id.btnColor);
        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorStore.setColor(BTN_COLOR);
            }
        });
        ColorStore.getColorObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(colorObserver);

        return view;
    }

    @Override
    public void onDestroy() {
        if (!subscription.isDisposed()) {
            subscription.dispose();
        }
        super.onDestroy();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        return view.dispatchTouchEvent(ev);
    }

}
