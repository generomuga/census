package census.com.census.activity;

import android.content.Intent;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import census.com.census.DatabaseHelper;
import census.com.census.FamilyIdentification;
import census.com.census.R;
import census.com.census.SurveyList;
import census.com.census.events.DeleteErrorEnvironmentEvent;
import census.com.census.events.DeleteErrorFamilyEvent;
import census.com.census.events.DeleteErrorFamilyIdentificationEvent;
import census.com.census.events.DeleteErrorHealthEvent;
import census.com.census.events.DeleteSuccessEnvironmentEvent;
import census.com.census.events.DeleteSuccessFamilyEvent;
import census.com.census.events.DeleteSuccessFamilyIdentificationEvent;
import census.com.census.events.DeleteSuccessHealthEvent;
import census.com.census.presenter.MainPresenter;
import census.com.census.presenter_impl.MainPresenterImpl;
import census.com.census.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private ImageButton mFab;

    private List surveyList;
    private ListView mSurveyList;

    private Toolbar toolbarMain;

    private MainPresenter mainPresenterListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainPresenterListener = new MainPresenterImpl(this);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference("data/familyIdentification");

        mSurveyList = (ListView) findViewById(R.id.listViewSurvey);

        surveyList = new ArrayList<>();

        //for toolbar init
        /*toolbarMain = (Toolbar) findViewById(R.id.toolBarMain);
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("Main");

        /*mFab = (ImageButton) findViewById(R.id.fab);
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
                showUpdateDialog(familyIdentification.getId(),familyIdentification.getfName()+' '+familyIdentification.getlName());
                return false;
            }
        });*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        else {
            mDatabase.orderByChild("lName").addValueEventListener(new ValueEventListener() {
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
                    //do nothing
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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

    private void showUpdateDialog(final String dataId,final String name){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.dialog_update_delete,null);
        dialogBuilder.setView(dialogView);

        Button mDelete = (Button) dialogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle(name);

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
        mainPresenterListener.checkList(dataId);
    }

    @Override
    public void onSuccessDelete() {

    }

    @Override
    public void onErrorDelete() {

    }

    @Subscribe
    public void onDeleteSuccessFamilyIdentification(DeleteSuccessFamilyIdentificationEvent deleteSuccessEvent){
        Toast.makeText(this,"Deleted!",Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onDeleteErrorFamilyIdentification(DeleteErrorFamilyIdentificationEvent deleteErrorFamilyIdentificationEvent){
        deleteErrorFamilyIdentificationEvent.getMessage();
    }

    @Subscribe
    public void onDeleteSuccessFamily(DeleteSuccessFamilyEvent deleteSuccessFamilyEvent){

    }

    @Subscribe
    public void onDeleteErrorFamily(DeleteErrorFamilyEvent deleteErrorFamilyEvent){
        deleteErrorFamilyEvent.getMessage();
    }

    @Subscribe
    public void onDeleteSuccessHealth(DeleteSuccessHealthEvent deleteSuccessHealthEvent){

    }

    @Subscribe
    public void onDeleteErrorHealth(DeleteErrorHealthEvent deleteErrorHealthEvent){
        deleteErrorHealthEvent.getMessage();
    }

    @Subscribe
    public void onDeleteSuccessEnvironment(DeleteSuccessEnvironmentEvent deleteSuccessEnvironmentEvent){

    }

    @Subscribe
    public void onDeleteErrorEnvironment(DeleteErrorEnvironmentEvent deleteErrorEnvironmentEvent){
        deleteErrorEnvironmentEvent.getMessage();
    }



}
