package com.example.beauticianapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<AppointmentsData> {

    Context mContext;
    int mResource;

    public ListAdapter(Context context, int resource, List<AppointmentsData> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String customer = getItem(position).getCustomer();
        String phone = getItem(position).getPhone();
        String addresss = getItem(position).getEmail();
        String startingTime = getItem(position).getStartingTime();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView startingHourTextView = (TextView) convertView.findViewById(R.id.myAgendaStartingHour);
        TextView customerTextView = (TextView) convertView.findViewById(R.id.myAgendaCustomer);
        TextView phoneTextView = (TextView) convertView.findViewById(R.id.myAgendaPhone);
        TextView noteTextView = (TextView) convertView.findViewById(R.id.myAgendaEmail);

        startingHourTextView.setText(startingTime);
        customerTextView.setText(customer);
        phoneTextView.setText(phone);
        noteTextView.setText(addresss);

        return convertView;
    }
}