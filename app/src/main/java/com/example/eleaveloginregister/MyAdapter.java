package com.example.eleaveloginregister;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<Leave> list;

    public MyAdapter(Context context, ArrayList<Leave> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Leave leave = list.get(position);
        holder.name.setText(leave.getName());
        holder.date.setText(leave.getLeaveStartDate());
        holder.status.setText(leave.getStatus());
        holder.leaveType.setText(leave.getLeaveType());
        holder.reason.setText(leave.getLeaveReason());
        holder.duration.setText(leave.getLeaveDuration());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,date,status,duration,reason,leaveType;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName5);
            date = itemView.findViewById(R.id.tvDate);
            status = itemView.findViewById(R.id.tvStatus);
            duration = itemView.findViewById(R.id.tvDuration);
            reason = itemView.findViewById(R.id.tvReason);
            leaveType= itemView.findViewById(R.id.tvLeaveType);

        }
    }

}
