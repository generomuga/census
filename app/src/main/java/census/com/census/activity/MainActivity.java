package census.com.census.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import census.com.census.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init views
        mToolbar = (Toolbar) findViewById(R.id.toolBarMain);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Census");
    }



}
