package census.com.census;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class SurveyList extends ArrayAdapter<FamilyIdentification> {

    private Activity context;
    List<FamilyIdentification> surveyList;

    public SurveyList(Activity context,List<FamilyIdentification> surveyList){
        super(context,R.layout.list_layout_survey,surveyList);
        this.context = context;
        this.surveyList = surveyList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout_survey,null,true);

        TextView textViewUser = (TextView) listViewItem.findViewById(R.id.textViewUser);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);

        FamilyIdentification familyIdentification = surveyList.get(position);
        textViewUser.setText(familyIdentification.getfName());
        textViewEmail.setText(familyIdentification.getUser());

        return listViewItem;
    }
}
