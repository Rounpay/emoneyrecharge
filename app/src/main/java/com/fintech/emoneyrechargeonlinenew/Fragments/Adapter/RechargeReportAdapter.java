package com.fintech.emoneyrechargeonlinenew.Fragments.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fintech.emoneyrechargeonlinenew.Activities.RechargeActivity;
import com.fintech.emoneyrechargeonlinenew.Activities.RechargeHistory;
import com.fintech.emoneyrechargeonlinenew.Activities.SecondRechargeActivity;
import com.fintech.emoneyrechargeonlinenew.Activities.SlipActivityRechargeReport;
import com.fintech.emoneyrechargeonlinenew.Api.Object.RechargeStatus;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.CustomAlertDialog;
import com.fintech.emoneyrechargeonlinenew.Util.MyPrintDocumentAdapter;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;

import java.util.ArrayList;
import java.util.Locale;

public class RechargeReportAdapter extends RecyclerView.Adapter<RechargeReportAdapter.MyViewHolder> {
    public TextInputLayout tilRemark;
    public EditText etRemark;
    public Button okButton;
    public Button cancelButton;
    String charText = "";
    int resourceId = 0;
    CustomAlertDialog mCustomAlertDialog;
    private ArrayList<RechargeStatus> rechargeStatus;
    private ArrayList<RechargeStatus> transactionsList;
    private Activity mContext;
    private String status;
    private CustomLoader mCustomLoader;
    boolean isPsa;

    public RechargeReportAdapter(ArrayList<RechargeStatus> transactionsList, Activity mContext, boolean isPsa) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.isPsa = isPsa;
        this.rechargeStatus = new ArrayList<>();
        this.rechargeStatus.addAll(transactionsList);
        mCustomAlertDialog = new CustomAlertDialog(mContext, true);
        mCustomLoader = new CustomLoader(mContext, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
    }

    @Override
    public RechargeReportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_recharge_report, parent, false);

        return new RechargeReportAdapter.MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(final RechargeReportAdapter.MyViewHolder holder, int position) {
        final RechargeStatus operator = transactionsList.get(position);
        holder.operatorName.setText("" + operator.getOperator());
        holder.txn.setText("" + operator.getTransactionID());
        holder.balance.setText("" + mContext.getResources().getString(R.string.rupiya) + " " + operator.getBalance());
        holder.lastbalance.setText("" + mContext.getResources().getString(R.string.rupiya) + " " + operator.getLastBalance());
        holder.amount.setText("" + mContext.getResources().getString(R.string.rupiya) + " " + operator.getRequestedAmount());
        holder.debit.setText("" + mContext.getResources().getString(R.string.rupiya) + " -" + operator.getAmount());
        holder.comm.setText("" + mContext.getResources().getString(R.string.rupiya) + " " + operator.getCommission());
        holder.source.setText(operator.getRequestMode());
        if (operator.getCommType() == true) {
            holder.CommissionLabel.setText("Cash Back : ");
            //  holder.CommissionLabel.setText("Surcharge : ");
            holder.comm.setTextColor(mContext.getResources().getColor(R.color.green));
        } else {
            holder.comm.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.CommissionLabel.setText("Cash Back : ");
            // holder.CommissionLabel.setText("Commision : ");
        }

        //  holder.opid.setText(" "  + operator.getOpID());
        holder.date.setText(operator.getEntryDate());
        holder.mobile.setText("" + operator.getAccount());
        holder.liveID.setText("" + operator.getLiveID());
        String faqsearchDescription = transactionsList.get(position).getType_().toLowerCase(Locale.getDefault());
        String faqsearchDescription2 = transactionsList.get(position).getAccount().toLowerCase(Locale.getDefault());
        String faqsearchDescription3 = transactionsList.get(position).getOperator().toLowerCase(Locale.getDefault());
        if (faqsearchDescription.contains(charText)) {
            int startPos = faqsearchDescription.indexOf(charText);
            int endPos = startPos + charText.length();
            Spannable spanText = Spannable.Factory.getInstance().newSpannable(transactionsList.get(position).getType_()); // <- EDITED: Use the original string, as `country` has been converted to lowercase.
            spanText.setSpan(new ForegroundColorSpan(Color.BLUE), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanText.setSpan(new StyleSpan(Typeface.ITALIC), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.status.setText(spanText, TextView.BufferType.SPANNABLE);

        } else {
            holder.status.setText(transactionsList.get(position).getType_());
        }
        if (faqsearchDescription3.contains(charText)) {
            int startPos = faqsearchDescription3.indexOf(charText);
            int endPos = startPos + charText.length();
            Spannable spanText = Spannable.Factory.getInstance().newSpannable(transactionsList.get(position).getOperator()); // <- EDITED: Use the original string, as `country` has been converted to lowercase.
            spanText.setSpan(new ForegroundColorSpan(Color.BLUE), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanText.setSpan(new StyleSpan(Typeface.ITALIC), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.status.setText(spanText, TextView.BufferType.SPANNABLE);

        } else {
            holder.status.setText(transactionsList.get(position).getType_());
        }
        if (faqsearchDescription2.contains(charText)) {
            int startPos = faqsearchDescription2.indexOf(charText);
            int endPos = startPos + charText.length();
            Spannable spanText = Spannable.Factory.getInstance().newSpannable(transactionsList.get(position).getAccount()); // <- EDITED: Use the original string, as `country` has been converted to lowercase.
            spanText.setSpan(new ForegroundColorSpan(Color.BLUE), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanText.setSpan(new StyleSpan(Typeface.ITALIC), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.mobile.setText(spanText, TextView.BufferType.SPANNABLE);

        } else {
            holder.mobile.setText(transactionsList.get(position).getAccount());
        }

        if (operator.getType_().equalsIgnoreCase("SUCCESS")) {
            status = "Success";
            holder.status.setText(status);
            holder.dispute.setVisibility(View.VISIBLE);
            holder.status.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.rect_bal_dis));


            ViewCompat.setBackgroundTintList(holder.status, ContextCompat.getColorStateList(mContext, R.color.green));
        } else if (operator.getType_().equalsIgnoreCase("FAILED")) {
            status = "Failed";
            holder.status.setText(status);
            holder.dispute.setVisibility(View.GONE);
            holder.status.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.rect_bal_dis));
            ViewCompat.setBackgroundTintList(holder.status, ContextCompat.getColorStateList(mContext, R.color.red));


        } else if (operator.getType_().equalsIgnoreCase("REFUNDED")) {
            status = "Refund";
            holder.status.setText(status);
            holder.dispute.setVisibility(View.GONE);
            holder.status.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.rect_bal_dis));
            ViewCompat.setBackgroundTintList(holder.status, ContextCompat.getColorStateList(mContext, R.color.color_cyan));

        } else if (operator.getType_().equalsIgnoreCase("PENDING") || operator.getType_().equalsIgnoreCase("REQUEST SEND")
                || operator.getType_().equalsIgnoreCase("REQUEST SENT")) {
            status = "Pending";
            holder.status.setText(status);
            holder.dispute.setVisibility(View.GONE);
            holder.status.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.rect_bal_dis));
            ViewCompat.setBackgroundTintList(holder.status, ContextCompat.getColorStateList(mContext, R.color.yellow));
        } else {
            status = "other";

        }


        if (!operator.getType_().equalsIgnoreCase("FAILED") && !operator.getType_().equalsIgnoreCase("PENDING")) {
            if (operator.getRefundStatus().equalsIgnoreCase("1")) {
                if (operator.getRefundStatus_().equalsIgnoreCase("DISPUTE")) {
                    holder.dispute.setVisibility(View.VISIBLE);
                    holder.dispute.setClickable(true);
                    holder.dispute.setEnabled(true);
                   // holder.dispute.setText("Dispute");
                    holder.dispute.setText("Complaint");
                    ViewCompat.setBackgroundTintList(holder.dispute, ContextCompat.getColorStateList(mContext, R.color.colorlink));
                } else {
                    holder.dispute.setVisibility(View.GONE);
                }
            } else if (operator.getRefundStatus().equalsIgnoreCase("3")) {
                if (operator.getRefundStatus_().equalsIgnoreCase("REFUNDED")) {
                    holder.dispute.setVisibility(View.VISIBLE);
                    holder.dispute.setClickable(true);
                    holder.dispute.setText("Refunded");
                    holder.dispute.setEnabled(false);
                    ViewCompat.setBackgroundTintList(holder.dispute, ContextCompat.getColorStateList(mContext, R.color.green));
                } else {
                    holder.dispute.setVisibility(View.GONE);
                }
            } else if (operator.getRefundStatus().equalsIgnoreCase("4")) {
                if (operator.getRefundStatus_().equalsIgnoreCase("REJECTED")) {
                    holder.dispute.setVisibility(View.VISIBLE);
                    holder.dispute.setClickable(true);
                    holder.dispute.setText("Rejected");
                    holder.dispute.setEnabled(false);
                    ViewCompat.setBackgroundTintList(holder.dispute, ContextCompat.getColorStateList(mContext, R.color.color_red));
                } else {
                    holder.dispute.setVisibility(View.GONE);
                }
            } else if (operator.getRefundStatus().equalsIgnoreCase("2")) {
                if (operator.getRefundStatus_().equalsIgnoreCase("UNDER REVIEW")) {
                    holder.dispute.setVisibility(View.VISIBLE);
                    holder.dispute.setClickable(true);
                    holder.dispute.setText("Under Review");
                    holder.dispute.setEnabled(false);
                    ViewCompat.setBackgroundTintList(holder.dispute, ContextCompat.getColorStateList(mContext, R.color.grey));
                } else {
                    holder.dispute.setVisibility(View.GONE);
                }
            }
        }


        if (operator.getOperator() != null && operator.getOperator().toString().length() > 0) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(mContext).load(ApplicationConstant.INSTANCE.baseIconUrl + operator.getOid() + ".png").
                    apply(requestOptions).into(holder.operatorImage);
        } else {
            holder.operatorImage.setImageResource(R.drawable.rnd_placeholder);
        }

        holder.Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, SlipActivityRechargeReport.class);

                i.putExtra("amount", "" + operator.getRequestedAmount());
                i.putExtra("RechargeMobileNo", "" + operator.getAccount());
                i.putExtra("transactionRemark", "" + UtilMethods.INSTANCE.getTransactionRemark(mContext, Integer.parseInt(operator.getOid())));
                i.putExtra("liveId", "" + operator.getLiveID());
                i.putExtra("operatorname", "" + operator.getOperator());
                i.putExtra("pdate", "" + operator.getEntryDate());
                i.putExtra("ptime", "" + operator.getModifyDate());
                i.putExtra("txid", "" + operator.getTransactionID());
                i.putExtra("txStatus", "" + operator.getType_());
                i.putExtra("typerecharge", "" + operator.getType_());
                i.putExtra("imageurl", "" + ApplicationConstant.INSTANCE.baseIconUrl + operator.getOid() + ".png");

                mContext.startActivity(i);

            }
        });
        holder.print.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                sendReport(operator.getTransactionID());

            }
        });


        holder.dispute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dispute_popup, null);
                tilRemark = (TextInputLayout) view.findViewById(R.id.til_remark);
                etRemark = (EditText) view.findViewById(R.id.remark);
                okButton = (Button) view.findViewById(R.id.okButton);
                cancelButton = (Button) view.findViewById(R.id.cancelButton);
                final Dialog dialog = new Dialog(mContext, R.style.alert_dialog_light);
                dialog.setCancelable(false);
                dialog.setContentView(view);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                etRemark.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        /*if (!validateText()) {
                            return;
                        }*/
                    }
                });
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String remarkText;
                        if (etRemark.getText() != null && etRemark.getText().toString().trim().length() > 0) {
                            remarkText = etRemark.getText().toString().trim();
                        } else {
                            remarkText = "";
                        }
                        if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

                            mCustomLoader.show();
                            mCustomLoader.setCancelable(false);
                            mCustomLoader.setCanceledOnTouchOutside(false);
                            UtilMethods.INSTANCE.RefundRequest(mContext, operator.getTid(),operator.getTransactionID(), remarkText,mCustomLoader, holder.dispute,new UtilMethods.ApiCallBack() {
                                @Override
                                public void onSucess(Object object) {
                                    if (mContext instanceof RechargeHistory) {
                                        ((RechargeHistory) mContext).HitApi();
                                    } else if (mContext instanceof SecondRechargeActivity) {
                                        ((SecondRechargeActivity) mContext).HitApi();
                                    } else if (mContext instanceof RechargeActivity) {
                                        ((RechargeActivity) mContext).HitApi();
                                    }
                                }
                            });
                            dialog.dismiss();
                        } else {
                            UtilMethods.INSTANCE.NetworkError(mContext, mContext.getResources().getString(R.string.err_msg_network_title),
                                    mContext.getResources().getString(R.string.err_msg_network));
                        }

                    }
                });
                dialog.show();

            }
        });
    }

    private boolean validateText() {
        if (etRemark.getText().toString().trim().isEmpty()) {
            tilRemark.setError(mContext.getString(R.string.err_msg_text));
            okButton.setEnabled(false);
            return false;
        } else {
            okButton.setEnabled(true);
        }
        return true;
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void saveandprint(String string) {
        MyPrintDocumentAdapter pd = new MyPrintDocumentAdapter(mContext, string);
        pd.printDocument(string);
    }

    // Filter Class
    public void filter(String charText) {
        this.charText = charText.toLowerCase(Locale.getDefault());

        transactionsList.clear();
        if (charText.length() == 0) {
            transactionsList.addAll(rechargeStatus);
        } else {
            for (RechargeStatus wp : rechargeStatus) {
                if (wp.getType_().toLowerCase(Locale.getDefault()).contains(charText) ||
                        wp.getAccount().toLowerCase(Locale.getDefault()).contains(charText) ||
                        wp.getOperator().toLowerCase(Locale.getDefault()).contains(charText)) {
                    transactionsList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    void sendReport(String reportId) {
        if (mCustomAlertDialog != null) {
            mCustomAlertDialog.sendReportDialog(1,"",new CustomAlertDialog.DialogSingleCallBack() {
                @Override
                public void onPositiveClick(String mobile, String emailId) {

                }
            });
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView balance, userName, operatorName, CommissionLabel;
        public AppCompatTextView lastbalance;
        public AppCompatTextView txn;
        public AppCompatTextView amount;
        public AppCompatTextView opid;
        public AppCompatTextView date, debit, comm;
        public AppCompatImageView operatorImage;
        public AppCompatTextView mobile;
        public AppCompatTextView status;
        public AppCompatTextView liveID;
        TextView dispute, source;
        ImageView Share, print;

        public MyViewHolder(View view) {
            super(view);
            operatorName = (AppCompatTextView) view.findViewById(R.id.operatorName);
            userName = (AppCompatTextView) view.findViewById(R.id.userName);
            txn = (AppCompatTextView) view.findViewById(R.id.txn);
            balance = (AppCompatTextView) view.findViewById(R.id.balance);
            amount = (AppCompatTextView) view.findViewById(R.id.amount);
            source = view.findViewById(R.id.source);
            date = (AppCompatTextView) view.findViewById(R.id.date);
            mobile = (AppCompatTextView) view.findViewById(R.id.mobile);
            liveID = (AppCompatTextView) view.findViewById(R.id.liveID);
            status = (AppCompatTextView) view.findViewById(R.id.status);
            dispute = (TextView) view.findViewById(R.id.dispute);
            Share = (ImageView) view.findViewById(R.id.Share);
            print = (ImageView) view.findViewById(R.id.print);
            CommissionLabel = view.findViewById(R.id.CommissionLabel);
            operatorImage = (AppCompatImageView) view.findViewById(R.id.operatorImage);
            debit = (AppCompatTextView) view.findViewById(R.id.debit);
            comm = (AppCompatTextView) view.findViewById(R.id.comm);
            lastbalance = (AppCompatTextView) view.findViewById(R.id.lastbalance);
        }
    }
}
