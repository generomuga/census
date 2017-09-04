package census.com.census.activity;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;


public class CensusApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
