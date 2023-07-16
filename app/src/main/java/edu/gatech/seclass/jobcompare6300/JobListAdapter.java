package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

public class JobListAdapter extends ArrayAdapter<Job> {

    private List<Job> jobList;
    private Context myContext;

    public JobListAdapter(List<Job> jobList, Context context) {
        super(context, R.layout.adapter_view, jobList);
        this.jobList = jobList;
        this.myContext = context;
    }

    private static class JobHolder {
        public TextView jobTitle;
        public TextView jobCompany;
        public CheckBox chkBox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        JobHolder holder = new JobHolder();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.adapter_view, parent, false);
            holder.jobTitle = (TextView) v.findViewById(R.id.compareJobTitle);
            holder.jobCompany = (TextView) v.findViewById(R.id.compareJobCompany);
            holder.chkBox = (CheckBox) v.findViewById(R.id.compareJobCheckbox);

            holder.chkBox.setOnCheckedChangeListener((MainActivity) myContext);
            v.setTag(holder);
        }
        else{
            holder = (JobHolder) v.getTag();
        }
        Job job = jobList.get(position);
        holder.jobTitle.setText(job.getTitle());
        holder.jobCompany.setText(job.getCompany());
        holder.chkBox.setChecked(job.isSelected());
        holder.chkBox.setTag(job);

        if (job instanceof CurrentJob){
            holder.jobTitle.setTextColor(Color.RED);
            holder.jobCompany.setTextColor(Color.RED);
        }

        return v;
    }
}
