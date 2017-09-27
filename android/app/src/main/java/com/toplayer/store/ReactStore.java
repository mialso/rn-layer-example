package com.toplayer.store;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;

public class ReactStore extends ReactContextBaseJavaModule {
    String FONT_COLOR = "FONT_COLOR";
    Disposable subscription;
    Observer<String> colorObserver = new Observer<String>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {
            subscription = d;
        }

        @Override
        public void onNext(@NonNull String s) {
            Log.d("onNext", s);
            WritableMap params = Arguments.createMap();
            params.putString("color", s);
            sendEvent(context, "COLOR_CHANGE", params);            }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    ReactContext context;
    public ReactStore(final ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
    }

    @Override
    public void initialize() {
        ColorStore.getColorObservable().subscribe(colorObserver);
    }

    @Override
    public void onCatalystInstanceDestroy() {
        if (subscription.isDisposed()) {
            return;
        }
        subscription.dispose();
    }

    @Override
    public String getName() {
        return "ReactStore";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(FONT_COLOR, "blue");
        return constants;
    }

    @ReactMethod
    public void show(String color, int someInt) {
        // do smth
        Log.d("log_tag", "log_message");
    }

    @ReactMethod
    public void bannerClick(String bannerName) {
        Log.d("banner_click", bannerName);
    }
    @ReactMethod
    public void getColor(Callback success) {
        success.invoke("green");
    }

    private void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    @ReactMethod
    public void pushAction(String actionName, ReadableMap data) {
        if (actionName.equals(ColorStore.COLOR_CHANGE)) {
            ColorStore.setColor(data.getString("colorName"));
        }
    }
}
