package census.com.census.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import census.com.census.R;

public class FamilyIdentificationFragment extends Fragment {

    private View view;
    public static EditText mFname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_family_identification, container, false);

        mFname = (EditText) view.findViewById(R.id.editTextFname);

        return view;
    }

}





