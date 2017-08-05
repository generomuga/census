package census.com.census;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarMain;
    //Nougat version
    //private FloatingActionButton fabAdd;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



        //connectDB();
        //selectSample();
        //DbUtils.getDatabase(this);

        //onClearSharedReference();
    }

    @Override
    protected void onStart() {
        super.onStart();

        onClearSharedReference();
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
            throw new Error("Unable to create database");
        }

        try{
            databaseHelper.openDataBase();
        }
        catch (SQLException sqle){
            throw sqle;
        }
    }

    private void onClearSharedReference(){
        sharedPreferences = this.getSharedPreferences("census.com.census",MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }
}
