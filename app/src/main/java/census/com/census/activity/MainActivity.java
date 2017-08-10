package census.com.census.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

import census.com.census.DatabaseHelper;
import census.com.census.R;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar toolbarMain;
    private ImageButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        //connect to sqlite database
        connectDB();

        //for toolbar init
        toolbarMain = (Toolbar) findViewById(R.id.toolBarMain);
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("Main");

        mFab = (ImageButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainSurveyActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        else {
            //do  nothing
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        else{
            finish();
        }
    }
}
