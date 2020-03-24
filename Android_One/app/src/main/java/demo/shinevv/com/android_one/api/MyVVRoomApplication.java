package demo.shinevv.com.android_one.api;

import android.app.Application;

import com.ksyun.player.now.view.VVRoomApplication;

public class MyVVRoomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VVRoomApplication.init(this);
    }
}
