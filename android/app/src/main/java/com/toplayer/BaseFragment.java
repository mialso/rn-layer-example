package com.toplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment, container, false);
        final Button btnToggle = (Button) view.findViewById(R.id.btnToggle);
        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button me = (Button) view.findViewById(R.id.btnToggle);
                me.setText((btnToggle.getText() == "Hide") ? "Show" : "Hide");
            }
        });
        btnToggle.setText("Hide");
        return view;
    }
}
