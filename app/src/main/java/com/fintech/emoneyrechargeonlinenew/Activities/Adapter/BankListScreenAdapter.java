package com.fintech.emoneyrechargeonlinenew.Activities.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fintech.emoneyrechargeonlinenew.Activities.BankListScreen;
import com.fintech.emoneyrechargeonlinenew.Api.Object.BankListObject;
import com.fintech.emoneyrechargeonlinenew.AppUser.Activity.FosCollectionBankListActivity;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;

import java.util.ArrayList;



public class BankListScreenAdapter extends RecyclerView.Adapter<BankListScreenAdapter.MyViewHolder> {

    int resourceId = 0;
    private ArrayList<BankListObject> operatorList;
    private Context mContext;

    public BankListScreenAdapter(ArrayList<BankListObject> operatorList, Context mContext) {
        this.operatorList = operatorList;
        this.mContext = mContext;
    }

    @Override
    public BankListScreenAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_list_adapter, parent, false);

        return new BankListScreenAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BankListScreenAdapter.MyViewHolder holder, int position) {
        final BankListObject operator = operatorList.get(position);
        holder.title.setText(operator.getBankName());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext).load(ApplicationConstant.INSTANCE.basebankLogoUrl + operator.getLogo()).
                apply(requestOptions).into(holder.banklogo);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mContext instanceof BankListScreen)
                ((BankListScreen) mContext).ItemClick(operator);
                else if(mContext instanceof FosCollectionBankListActivity)
                    ((FosCollectionBankListActivity) mContext).ItemClick(operator);
            }
        });
    }

    @Override
    public int getItemCount() {
        return operatorList.size();
    }

    public void filter(ArrayList<BankListObject> newList) {
        operatorList = new ArrayList<>();
        operatorList.addAll(newList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView opImage, banklogo;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            banklogo = view.findViewById(R.id.banklogo);

        }
    }

}



/*
public class BankListScreenAdapter extends RecyclerView.Adapter<BankListScreenAdapter.MyViewHolder> {

    private ArrayList<BankListObject> operatorList;
    private Context mContext;
    int resourceId = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView opImage;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            opImage = (ImageView) view.findViewById(R.id.opImage);

        }
    }

    public BankListScreenAdapter(ArrayList<BankListObject> operatorList, Context mContext) {
        this.operatorList = operatorList;
        this.mContext = mContext;
    }

    @Override
    public BankListScreenAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_list_adapter, parent, false);

        return new BankListScreenAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BankListScreenAdapter.MyViewHolder holder, int position) {
        final BankListObject operator = operatorList.get(position);
        holder.title.setText(operator.getBankName());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BankListScreen) mContext).ItemClick(operator.getBankId(), operator.getBankName(), operator.getAccVeri(), operator.getShortCode());
            }
        });
    }

    @Override
    public int getItemCount() {
        return operatorList.size();
    }

}
*/
