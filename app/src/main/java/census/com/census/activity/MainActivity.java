package census.com.census.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import census.com.census.DatabaseHelper;
import census.com.census.FamilyIdentification;
import census.com.census.R;
import census.com.census.SurveyList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar toolbarMain;
    private ImageButton mFab;
    private ListView mSurveyList;

    private DatabaseReference mDatabase;
    private List surveyList;

    //private List<FamilyIdentification> familyIdentifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //familyIdentifications = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference("data/familyIdentification");

        mSurveyList = (ListView) findViewById(R.id.listViewSurvey);

        surveyList = new ArrayList<>();

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

        mSurveyList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                FamilyIdentification familyIdentification = (FamilyIdentification) surveyList.get(position);
                showUpdateDialog(familyIdentification.getId());
                return false;
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

            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    surveyList.clear();
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        FamilyIdentification familyIdentification = snapshot.getValue(FamilyIdentification.class);
                        surveyList.add(familyIdentification);
                    }
                    SurveyList adapter = new SurveyList(MainActivity.this,surveyList);
                    mSurveyList.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
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
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        else{
            finish();
        }
    }

    private void showUpdateDialog(final String dataId){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.dialog_update_delete,null);
        dialogBuilder.setView(dialogView);

        Button mDelete = (Button) dialogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle("Sample");

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(dataId);
                alertDialog.dismiss();
            }
        });

    }

    private void deleteRecord(String dataId) {
        DatabaseReference drFamilyIdentification = FirebaseDatabase.getInstance().getReference("data").child("familyIdentification").child(dataId);
        DatabaseReference drFamily = FirebaseDatabase.getInstance().getReference("data").child("family").child(dataId);
        DatabaseReference drHealth = FirebaseDatabase.getInstance().getReference("data").child("health").child(dataId);
        DatabaseReference drEnvironment = FirebaseDatabase.getInstance().getReference("data").child("environment").child(dataId);

        drFamilyIdentification.removeValue();
        drFamily.removeValue();
        drHealth.removeValue();
        drEnvironment.removeValue();

        Toast.makeText(this,"Remove!",Toast.LENGTH_LONG).show();
    }
}
