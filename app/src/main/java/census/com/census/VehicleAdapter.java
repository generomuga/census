package census.com.census;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VehicleAdapter extends ArrayAdapter<VehicleVO> {
    private Context mContext;
    private ArrayList<VehicleVO> listVehicle;
    private VehicleAdapter vehicleAdapter;
    private boolean isFromView = false;

    public VehicleAdapter(Context context, int resource, List<VehicleVO> objects){
        super(context,resource,objects);
        this.mContext = context;
        this.listVehicle = (ArrayList<VehicleVO>) objects;
        this.vehicleAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
                LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                convertView = layoutInflater.inflate(R.layout.spinner_checkbox_text,null);
                holder = new ViewHolder();
                holder.mTextView = (TextView) convertView.findViewById(R.id.text);
                holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.checkbox);
                convertView.setTag(holder);
            }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listVehicle.get(position).getVehicle());
        isFromView = true;
        holder.mCheckBox.setChecked(listVehicle.get(position).isSelected());
        isFromView = false;

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

            }
        });
        return convertView;
    }

}

class ViewHolder {
    public CheckBox mCheckBox;
    public TextView mTextView;
}
