package census.com.census;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

public class HealthFragment extends Fragment {

    View view;

    public static RadioButton radioButtonEatYes;
    public static RadioButton radioButtonEatNo;
    public static RadioButton radioButtonHerbalYes;
    public static RadioButton radioButtonHerbalNo;
    public static RadioButton radioButtonVegYes;
    public static RadioButton radioButtonVegNo;
    public static RadioButton radioButtonIodizeYes;
    public static RadioButton radioButtonIodizeNo;
    public static RadioButton radioButtonFamilyYes;
    public static RadioButton radioButtonFamilyNo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_health, container, false);

        //init views
        initViews();

        return view;
    }

    private void initViews(){
        radioButtonEatYes = (RadioButton) view.findViewById(R.id.radioButtonEatYes);
        radioButtonEatNo = (RadioButton) view.findViewById(R.id.radioButtonEatNo);
        radioButtonHerbalYes = (RadioButton) view.findViewById(R.id.radioButtonHerbalYes);
    }

}
