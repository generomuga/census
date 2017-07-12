package census.com.census;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class FamilyIdentificationFragment extends Fragment {

    private View view;
    public static EditText editTextFName;
    private EditText editTextMName;
    private EditText editTextLName;
    private EditText editTextHouseNo;
    private EditText editTextStreetNo;
    private EditText editTextBarangay;
    private EditText editTextMunicipality;
    private EditText editTextProvince;
    private RadioButton radioButtonResident;
    private RadioButton radioButtonNonResident;
    private RadioButton radioButtonOwner;
    private RadioButton radioButtonExtended;
    private RadioButton radioButtonActive;
    private RadioButton radioButtonInactive;

    //onSaveDataListener saveDataListener;

    public interface onSaveDataListener{
        void saveData(String s);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*try{
            saveDataListener = (onSaveDataListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_family_identification, container, false);

        //init views
        initViews();
        //saveDataListener.saveData(editTextFName.getText().toString());

        return view;
    }

    private void initViews(){
        editTextFName = (EditText) view.findViewById(R.id.editTextFname);
        editTextMName = (EditText) view.findViewById(R.id.editTextMname);
        editTextLName = (EditText) view.findViewById(R.id.editTextLname);
        editTextHouseNo = (EditText) view.findViewById(R.id.editTextHouseNo);
        editTextStreetNo = (EditText) view.findViewById(R.id.editTextStreetNo);
        editTextBarangay = (EditText) view.findViewById(R.id.editTextBrgy);
        editTextMunicipality = (EditText) view.findViewById(R.id.editTextMunicipality);
        editTextProvince = (EditText) view.findViewById(R.id.editTextProvince);
        radioButtonResident = (RadioButton) view.findViewById(R.id.radioButtonResident);
        radioButtonNonResident = (RadioButton) view.findViewById(R.id.radioButtonNonResident);
        radioButtonOwner = (RadioButton) view.findViewById(R.id.radioButtonOwner);
        radioButtonExtended = (RadioButton) view.findViewById(R.id.radioButtonExtended);
        radioButtonActive = (RadioButton) view.findViewById(R.id.radioButtonActive);
        radioButtonInactive = (RadioButton) view.findViewById(R.id.radioButtonInactive);
    }


}
