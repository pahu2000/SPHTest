package per.colin.sphtest;

import android.app.Application;

import androidx.room.Room;

import per.colin.sphtest.db.AbstractDatabase;

public class AppApplication extends Application {

    public static AbstractDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(), AbstractDatabase.class, "sphdata").build();
    }
}
