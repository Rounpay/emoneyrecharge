package com.fintech.emoneyrechargeonlinenew.AppUser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.fintech.emoneyrechargeonlinenew.AppUser.Activity.ChannelReportActivity;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.AreaMaster;
import com.fintech.emoneyrechargeonlinenew.R;

import java.util.ArrayList;

public class ChannelAreaListAdapter extends RecyclerView.Adapter<ChannelAreaListAdapter.MyViewHolder>  {

    private ArrayList<AreaMaster> filterListItem = new ArrayList<>();
    private Context mContext;
    AlertDialog alertDialogServiceList;

    public ChannelAreaListAdapter(ArrayList<AreaMaster> operatorList, Context mContext, AlertDialog alertDialogServiceList) {
        this.filterListItem = operatorList;
        this.mContext = mContext;
        this.alertDialogServiceList=alertDialogServiceList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_channel_area_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final AreaMaster operator = filterListItem.get(position);

        holder.area.setText(operator.getArea());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogServiceList.dismiss();
                Intent intent=new Intent(mContext, ChannelReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("AREA_ID",operator.getAreaID());
                mContext.startActivity( intent );

            }
        });


//            holder.title.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v)
//                {
//                    Toast.makeText(mContext, ""+operator.getAreaID(), Toast.LENGTH_SHORT).show();
//                }
//            });

    }

    @Override
    public int getItemCount() {
        return filterListItem.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView area;



        public MyViewHolder(View view) {
            super(view);


            area = view.findViewById(R.id.area);


        }
    }

}
