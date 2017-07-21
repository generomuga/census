package census.com.census;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarMain;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for toolbar init
        toolbarMain = (Toolbar) findViewById(R.id.toolBarMain);
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("Main");

        //floating action button
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAddSurvey);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainSurveyActivity.class));
            }
        });

        connectDB();
        selectSample();
        //DbUtils.getDatabase(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
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
            Toast.makeText(MainActivity.this,"Database connected!", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Toast.makeText(MainActivity.this,"Unable to create database", Toast.LENGTH_SHORT).show();
            throw new Error("Unable to create database");
        }

        try{
            databaseHelper.openDataBase();
            Toast.makeText(MainActivity.this,"Open database", Toast.LENGTH_SHORT).show();
        }
        catch (SQLException sqle){
            Toast.makeText(MainActivity.this,"Unable to open database", Toast.LENGTH_SHORT).show();
            throw sqle;
        }


    }

    private void selectSample() {

        try {
            SQLiteDatabase dbLocation = this.openOrCreateDatabase("locations", Context.MODE_PRIVATE, null);
            Toast.makeText(this,"Select module open database",Toast.LENGTH_SHORT).show();

            //dbLocation.execSQL("CREATE TABLE IF NOT EXISTS locations (id INT,regions VARCHAR,province VARCHAR, municipality VARCHAR)");
            //dbLocation.execSQL("INSERT INTO locations (id,regions,province,municipality) VALUES (1,'IV','Laguna', 'Calauan')");

            Cursor c = dbLocation.rawQuery("SELECT * FROM locations", null);

            int regionsIndex = c.getColumnIndex("region");
            int provinceIndex = c.getColumnIndex("province");
            int municipality = c.getColumnIndex("municipality");

            c.moveToFirst();
            while (c != null) {
                Log.i("region", c.getString(regionsIndex));
                //Toast.makeText(this,c.getString(regionsIndex),Toast.LENGTH_SHORT).show();
                c.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
