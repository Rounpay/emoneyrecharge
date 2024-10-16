package com.fintech.emoneyrechargeonlinenew.Activities.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fintech.emoneyrechargeonlinenew.Activities.UpdateProfileActivity;
import com.fintech.emoneyrechargeonlinenew.Api.Object.UserDox;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;

import java.util.List;


public class UploadDocsAdapter extends RecyclerView.Adapter<UploadDocsAdapter.MyViewHolder> {

    private List<UserDox> mList;
    private Context mContext;

    public UploadDocsAdapter(Context mContext, List<UserDox> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public UploadDocsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_upload_docs, parent, false);

        return new UploadDocsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UploadDocsAdapter.MyViewHolder holder, int position) {
        final UserDox operator = mList.get(position);

        if (!operator.getOptional()) {
            String docName = operator.getDocName().replace(" ", "\n   ");
            SpannableString ss1 = new SpannableString(docName);
            ss1.setSpan(new ForegroundColorSpan(Color.BLACK), 0, docName.length(), 0);// set color
            ss1.setSpan(new RelativeSizeSpan(1f), 0, docName.length(), 0); // set size

            String astric = " *";
            SpannableString ss2 = new SpannableString(astric);
            ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, astric.length(), 0);// set color
            ss2.setSpan(new RelativeSizeSpan(1f), 0, astric.length(), 0); // set size
            CharSequence formatedText = TextUtils.concat(ss1, ss2);
            holder.docTv.setText(formatedText);
        } else {
            holder.docTv.setText(operator.getDocName().replace(" ", "\n"));
        }

        if (operator.getVerifyStatus() == 0) {
            holder.docStatusTv.setText("NOT UPLOADED");
            holder.docStatusTv.setTextColor(Color.BLACK);
        } else if (operator.getVerifyStatus() == 1) {
            holder.docStatusTv.setText("NOT VERIFIED");
            holder.docStatusTv.setTextColor(Color.BLUE);
        } else if (operator.getVerifyStatus() == 2) {
            holder.docStatusTv.setText("VERIFIED");
            holder.docStatusTv.setTextColor(Color.GREEN);
        } else if (operator.getVerifyStatus() == 3) {
            holder.docStatusTv.setText("REJECTED");
            holder.docStatusTv.setTextColor(Color.RED);
        }


        if (operator.getDocUrl() != null && !operator.getDocUrl().isEmpty()) {
            if (operator.getVerifyStatus() == 3 || operator.getVerifyStatus() == 0) {
                holder.uploadDoc.setText("Change");
            } else {
                holder.uploadDoc.setText("View");
            }
        } else {
            holder.uploadDoc.setText("Upload");
        }

        holder.docInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UpdateProfileActivity) mContext).showInfo(operator.getRemark(), v);
            }
        });

        holder.uploadInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator.getVerifyStatus() == 0) {
                    ((UpdateProfileActivity) mContext).showInfo(operator.getDocName() + " not uploaded yet", v);
                } else if (operator.getVerifyStatus() == 1) {
                    ((UpdateProfileActivity) mContext).showInfo(operator.getDocName() + " under screening", v);
                } else if (operator.getVerifyStatus() == 2) {
                    ((UpdateProfileActivity) mContext).showInfo(operator.getDocName() + " has been verified", v);
                } else if (operator.getVerifyStatus() == 3) {
                    ((UpdateProfileActivity) mContext).showInfo(operator.getDocName() + " Rejected Due To: " + operator.getdRemark(), v);
                }
            }
        });

        holder.uploadDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.uploadDoc.getText().equals("Upload") || holder.uploadDoc.getText().equals("Change")) {
                    ((UpdateProfileActivity) mContext).uploadDocs(operator.getDocTypeID(), operator.getUserId(), operator.getDocName());
                    /*((UpdateProfileActivity) mContext).uploadDocs(operator.getDocTypeID(), operator.getUserId());*/
                } else {
                    try {
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(ApplicationConstant.INSTANCE.baseUrl + "/Image/KYC/" + operator.getDocUrl())));
                    } catch (android.content.ActivityNotFoundException anfe) {

                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView docTv;
        private AppCompatImageView docInfo;
        private AppCompatTextView uploadDoc;
        private AppCompatImageView uploadInfo;
        private AppCompatTextView docStatusTv;

        public MyViewHolder(View view) {
            super(view);
            docTv = view.findViewById(R.id.docTv);
            docInfo = view.findViewById(R.id.docInfo);
            uploadDoc = view.findViewById(R.id.uploadDoc);
            uploadInfo = view.findViewById(R.id.uploadInfo);
            docStatusTv = view.findViewById(R.id.docStatusTv);

        }
    }


}




