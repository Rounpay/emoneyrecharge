package com.fintech.emoneyrechargeonlinenew.Activities.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fintech.emoneyrechargeonlinenew.Activities.AddMoneyActivity;
import com.fintech.emoneyrechargeonlinenew.Api.Object.PaymentGatewayType;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;

import java.util.ArrayList;

/**
 * Created by Vishnu on 18-01-2017.
 */

public class GatewayTypeAdapter extends RecyclerView.Adapter<GatewayTypeAdapter.MyViewHolder> {

    int resourceId = 0;
    private ArrayList<PaymentGatewayType> operatorList;
    private Context mContext;
    RequestOptions requestOptions;

    public GatewayTypeAdapter(ArrayList<PaymentGatewayType> operatorList, Context mContext) {
        this.operatorList = operatorList;
        this.mContext = mContext;
        requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_launcher_round);
        requestOptions.error(R.mipmap.ic_launcher_round);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_provider_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final PaymentGatewayType operator = operatorList.get(position);
        holder.title.setText(operator.getPg());
        holder.ll_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddMoneyActivity) mContext).startGateway(operator);
            }
        });

        Glide.with(mContext)
                .load(ApplicationConstant.INSTANCE.pgIconUrl + operator.getPgType() + ".png")
                .apply(requestOptions)
                .into(holder.opImage);

    }

    @Override
    public int getItemCount() {
        return operatorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView opImage;
        LinearLayout ll_top;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            opImage = (ImageView) view.findViewById(R.id.opImage);
            ll_top = view.findViewById(R.id.ll_top);

        }
    }

}
