package com.example.hp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hp.ui.R;
import com.example.hp.ui.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LeaveRecordsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<UserModel> userModels;
    private LayoutInflater layoutInflater;

    public LeaveRecordsAdapter(@NonNull Context context, ArrayList<UserModel> userModels) {
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
        ViewHolder viewHolder;
        View view = convertView;
        try {
            if (view == null) {
                viewHolder = new ViewHolder();
                view = layoutInflater.inflate(R.layout.leave_row_layout, null);
                viewHolder.tvRollNo = view.findViewById(R.id.tvRollNo);
                viewHolder.tvName = view.findViewById(R.id.tvName);
                viewHolder.tvDiv = view.findViewById(R.id.tvDiv);
                viewHolder.tvDate = view.findViewById(R.id.tvDate);
                viewHolder.tvReason = view.findViewById(R.id.tvReason);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tvRollNo.setText(String.valueOf(userModels.get(position).getRollno()));
            viewHolder.tvName.setText(userModels.get(position).getName());
            viewHolder.tvDiv.setText(userModels.get(position).getDivision());
            Date date = userModels.get(position).getDate();
            viewHolder.tvDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
            viewHolder.tvReason.setText(userModels.get(position).getReason());
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    private class ViewHolder{
        TextView tvRollNo, tvName,tvDiv, tvDate, tvReason;
    }
}
