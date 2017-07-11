package census.com.census;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FamilyIdentificationFragment extends Fragment {

    private DatabaseReference dbSurvey;
    private View view;
    private EditText editTextFName;
    private EditText editTextMName;
    private EditText editTextLName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_family_identification, container, false);

        //init views
        initViews();

        //firebase connection
        initFirebase();


        return view;
    }

    private void initViews(){
        editTextFName = (EditText) view.findViewById(R.id.editTextFname);
        editTextMName = (EditText) view.findViewById(R.id.editTextMname);
        editTextLName = (EditText) view.findViewById(R.id.editTextLname);
    }

    private void initFirebase(){
        dbSurvey = FirebaseDatabase.getInstance().getReference("survey");
    }


}
