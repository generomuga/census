package census.com.census;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarMain;
    private ImageButton mFab;
    //Nougat version
    //private FloatingActionButton fabAdd;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect to sqlite database
        connectDB();

        //for toolbar init
        toolbarMain = (Toolbar) findViewById(R.id.toolBarMain);
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("Main");

        /* floating action button (Nougat version)
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAddSurvey);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainSurveyActivity.class));
            }
        });*/


        mFab = (ImageButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("success","asd");
                startActivity(new Intent(MainActivity.this,MainSurveyActivity.class));
            }
        });


       // Toast.makeText(this,"gene", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //onClearSharedReference();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    private void connectDB(){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        try {
            databaseHelper.createDatabase();
        }
        catch (IOException e) {
            Log.e("Error:","Unable to connect database");
        }

        try{
            databaseHelper.openDataBase();
        }
        catch (SQLException sqle){
            Log.e("Error:",sqle.toString());
        }
    }

    private void onClearSharedReference(){
        sharedPreferences = this.getSharedPreferences("census.com.census",MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }
}
