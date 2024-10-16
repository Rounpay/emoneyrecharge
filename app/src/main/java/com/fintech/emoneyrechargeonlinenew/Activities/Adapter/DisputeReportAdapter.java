package com.fintech.emoneyrechargeonlinenew.Activities.Adapter;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fintech.emoneyrechargeonlinenew.Api.Object.RefundLog;
import com.fintech.emoneyrechargeonlinenew.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class DisputeReportAdapter extends RecyclerView.Adapter<DisputeReportAdapter.MyViewHolder> {
    String charText = "";
    int resourceId = 0;
    private List<RefundLog> rechargeStatus;
    private List<RefundLog> transactionsList;
    private Context mContext;

    public DisputeReportAdapter(List<RefundLog> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.rechargeStatus = new ArrayList<>();
        this.rechargeStatus.addAll(transactionsList);
    }

    @Override
    public DisputeReportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dispute_report_adapter, parent, false);

        return new DisputeReportAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DisputeReportAdapter.MyViewHolder holder, int position) {
        final RefundLog operator = transactionsList.get(position);
        holder.txn.setText("" + operator.getTransactionID());
        holder.operator.setText("" + operator.getOperator());
        holder.amount.setText(mContext.getResources().getString(R.string.rupiya) + " " + operator.getRequestedAmount());
        holder.number.setText("" + operator.getAccountNo());
        holder.acceptRejectDate.setText("" + operator.getRefundActionDate());
        holder.status.setText("" + operator.getrStatus());
        holder.requestDate.setText("" + operator.getRefundRequestDate());
        holder.rechargeDate.setText("" + operator.getEntryDate());
        holder.liveId.setText("" + operator.getLiveID());
        holder.refundStatus.setText("" + operator.getRefundType());

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    // Filter Class
    public void filter(String charText) {
        this.charText = charText.toLowerCase(Locale.getDefault());

        transactionsList.clear();
        if (charText.length() == 0) {
            transactionsList.addAll(rechargeStatus);
        } else {
            for (RefundLog wp : rechargeStatus) {
                if (wp.getAccountNo().toLowerCase(Locale.getDefault()).contains(charText) || wp.getOperator().toLowerCase(Locale.getDefault()).contains(charText)
                        || wp.getOutletMobile().toLowerCase(Locale.getDefault()).contains(charText) || wp.getOutletName().toLowerCase(Locale.getDefault()).contains(charText)
                        || wp.getTransactionID().toLowerCase(Locale.getDefault()).contains(charText) || wp.getrStatus().toLowerCase(Locale.getDefault()).contains(charText)) {
                    transactionsList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView status;
        private AppCompatTextView amount;
        private AppCompatTextView operator;
        private AppCompatTextView txnLabel;
        private AppCompatTextView txn;
        private AppCompatTextView liveIdLabel;
        private AppCompatTextView liveId;
        private AppCompatTextView numberLabel;
        private AppCompatTextView number;
        private AppCompatTextView requestDateLabel;
        private AppCompatTextView requestDate;
        private AppCompatTextView acceptRejectDateLabel;
        private AppCompatTextView acceptRejectDate;
        private AppCompatTextView rechargeDateLabel;
        private AppCompatTextView rechargeDate;
        private AppCompatTextView refundStatusLabel;
        private AppCompatTextView refundStatus;


        public MyViewHolder(View view) {
            super(view);
            status = view.findViewById(R.id.status);
            amount = view.findViewById(R.id.amount);
            operator = view.findViewById(R.id.operator);
            txnLabel = view.findViewById(R.id.txnLabel);
            txn = view.findViewById(R.id.txn);
            liveIdLabel = view.findViewById(R.id.liveIdLabel);
            liveId = view.findViewById(R.id.liveId);
            numberLabel = view.findViewById(R.id.numberLabel);
            number = view.findViewById(R.id.number);
            requestDateLabel = view.findViewById(R.id.requestDateLabel);
            requestDate = view.findViewById(R.id.requestDate);
            acceptRejectDateLabel = view.findViewById(R.id.acceptRejectDateLabel);
            acceptRejectDate = view.findViewById(R.id.acceptRejectDate);
            rechargeDateLabel = view.findViewById(R.id.rechargeDateLabel);
            rechargeDate = view.findViewById(R.id.rechargeDate);
            refundStatusLabel = view.findViewById(R.id.refundStatusLabel);
            refundStatus = view.findViewById(R.id.refundStatus);

        }
    }
}