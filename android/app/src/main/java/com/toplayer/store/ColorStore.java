package com.toplayer.store;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class ColorStore {
    public static String COLOR_CHANGE = "COLOR_CHANGE";
    private static String colorDefault = "white";
    private static final BehaviorSubject<String> colorStream = BehaviorSubject.createDefault(colorDefault);

    public static void setColor(String color) {
        Log.d("ColorStore: setColor", color);
        colorStream.onNext(color);
    }
    public static Observable<String> getColorObservable() {
        return colorStream;
    }
}
