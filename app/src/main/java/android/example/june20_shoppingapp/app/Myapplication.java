package android.example.june20_shoppingapp.app;

import android.app.Application;

public class Myapplication extends Application {
    private static Myapplication sInstance;

    @Override
    public void onCreate() {   //first method get called when we start the application, before the activity
        super.onCreate();
    }
}
