package com.example.eleaveloginregister;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class MyAdapterApproval extends RecyclerView.Adapter<MyAdapterApproval.MyViewHolder>{

    Context context;
    ArrayList<Leave> list;


    public MyAdapterApproval(Context context, ArrayList<Leave> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_approval,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Leave leave = list.get(position);

        holder.date.setText(leave.getLeaveStartDate());
        holder.status.setText(leave.getStatus());
        holder.name.setText(leave.getName());
        holder.leaveType.setText(leave.getLeaveType());
        holder.reason.setText(leave.getLeaveReason());
        holder.duration.setText(leave.getLeaveDuration());

        holder.btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goUpdateStatus = new Intent(context,UpdateStatus.class);
                goUpdateStatus.putExtra("Key", (Serializable) leave);
                context.startActivity(goUpdateStatus);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public Button btnStatus;

        TextView name,date,status,duration,reason,leaveType;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tvDate);
            name = itemView.findViewById(R.id.tvName5);
            status = itemView.findViewById(R.id.tvStatus);
            duration = itemView.findViewById(R.id.tvDuration);
            reason = itemView.findViewById(R.id.tvReason);
            leaveType= itemView.findViewById(R.id.tvLeaveType);

            btnStatus = itemView.findViewById(R.id.btnStatus);
        }
    }

}
