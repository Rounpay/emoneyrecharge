package com.fintech.emoneyrechargeonlinenew.QRScan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fintech.emoneyrechargeonlinenew.QRScan.Activity.VPAListActivity;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.VpaListResponse;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VPAListAdapter extends RecyclerView.Adapter<VPAListAdapter.MyViewHolder>  {

    private List<VpaListResponse> benisFilterList, listItem;
    private Context mContext;
    private RequestOptions requestOptions;

    public VPAListAdapter(List<VpaListResponse> listItem, Context mContext) {
        this.benisFilterList = listItem;
        this.listItem = listItem;
        this.mContext = mContext;
        requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.placeholder_square);
        requestOptions.error(R.drawable.placeholder_square);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_vpalist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final VpaListResponse vpaListResponse = listItem.get(position);
        if(vpaListResponse!=null){

            holder.accountHolderTv.setText(vpaListResponse.getAccountHolder()+"");
            holder.vpaTv.setText(vpaListResponse.getVpa()+"");


            Glide.with(mContext)
                    .load(ApplicationConstant.INSTANCE.UPIIconUrl + UtilMethods.INSTANCE.getUPILogoFromVPAStr(vpaListResponse.getVpa()) + ".png")
                    .apply(requestOptions)
                    .into(holder.upiIconIv);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mContext instanceof VPAListActivity)
                    ((VPAListActivity)mContext).itemClick(vpaListResponse);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());

        List<VpaListResponse> filterList = new ArrayList<>();
        if (charText.length() == 0) {
            filterList.addAll(benisFilterList);
        } else {
            for (VpaListResponse item : benisFilterList) {
                if ((item.getVpa() + "").toLowerCase(Locale.getDefault()).contains(charText) ||
                        (item.getAccountHolder() + "").toLowerCase(Locale.getDefault()).contains(charText))
                {
                    filterList.add(item);
                }
            }
        }

        listItem = filterList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView accountHolderTv,vpaTv;
        public ImageView upiIconIv;

        public MyViewHolder(View view) {
            super(view);
            accountHolderTv = (TextView) view.findViewById(R.id.accountHolderTv);
            vpaTv = (TextView) view.findViewById(R.id.vpaTv);
            upiIconIv = (ImageView) view.findViewById(R.id.upiIconIv);
        }
    }

}
