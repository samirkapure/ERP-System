package com.example.hp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.widget.BaseAdapter;

import com.example.hp.ui.R;
import com.example.hp.ui.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MonthlyURecordsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<UserModel> userModels;
    private LayoutInflater layoutInflater;

    public MonthlyURecordsAdapter(@NonNull Context context, ArrayList<UserModel> userModels) {
        this.context = context;
        this.userModels = userModels;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return userModels.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MonthlyURecordsAdapter.ViewHolder viewHolder;
        View view = convertView;
        try {
            if (view == null) {
                viewHolder = new MonthlyURecordsAdapter.ViewHolder();
                view = layoutInflater.inflate(R.layout.monthlyu_row_layout, null);
                viewHolder.tvRollNomu = view.findViewById(R.id.tvRollNomu);
                viewHolder.tvNamemu = view.findViewById(R.id.tvNamemu);
                viewHolder.tvDivmu = view.findViewById(R.id.tvDivmu);
                viewHolder.tvDatemu = view.findViewById(R.id.tvDatemu);
                viewHolder.tvMonthmu = view.findViewById(R.id.tvMonthmu);
                view.setTag(viewHolder);
            } else {
                viewHolder = (MonthlyURecordsAdapter.ViewHolder) convertView.getTag();
            }

            viewHolder.tvRollNomu.setText(String.valueOf(userModels.get(position).getRollno()));
            viewHolder.tvNamemu.setText(userModels.get(position).getName());
            viewHolder.tvDivmu.setText(userModels.get(position).getDivision());
            Date date = userModels.get(position).getDate();
            viewHolder.tvDatemu.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
            viewHolder.tvMonthmu.setText(userModels.get(position).getMonth());
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    private class ViewHolder{
        TextView tvRollNomu, tvNamemu,tvDivmu, tvDatemu, tvMonthmu;
    }
}
