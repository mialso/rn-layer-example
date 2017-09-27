package com.toplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BackgroundFragment extends Fragment {
    public final static String TAG = BackgroundFragment.class.getSimpleName();
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.background_fragment, container, false);

        return view;
    }
}
