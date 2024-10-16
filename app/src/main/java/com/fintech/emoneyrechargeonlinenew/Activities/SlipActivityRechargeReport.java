package com.fintech.emoneyrechargeonlinenew.Activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.fintech.emoneyrechargeonlinenew.Api.Response.AppUserListResponse;
import com.fintech.emoneyrechargeonlinenew.Auth.dto.LoginResponse;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SlipActivityRechargeReport extends AppCompatActivity {

    public AppCompatImageView operatorImage;
    TextView tvAmount;
    TextView tvRechargeMobileNo;
    TextView tvliveId, tvTxnStatus;
    TextView tvoperatorname;
    TextView tvpdate;
    TextView tvptime;
    TextView tvtxid, outletDetail;
    TextView tvShare, address,accountTitle;
    LinearLayout shareView;
    RelativeLayout rlCancel;

    String amount = "";
    String RechargeMobileNo ,intentTransactionRemark;
    String liveId = "";
    String pdate = "";
    String ptime = "";
    String operatorname = "";
    String txid = "";
    String txStatus = "";
    String typerecharge = "";
    String imageurl = "";
    LinearLayout manin_lin;


    LinearLayout llVia;
    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private int REQUEST_PERMISSIONS = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_report_print_popup);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        UtilMethods.INSTANCE.setAppLogoIconUI(this,findViewById(R.id.logoIv));
        getIds();
    }

    private void getIds() {
        amount = getIntent().getExtras().getString("amount");
        RechargeMobileNo = getIntent().getExtras().getString("RechargeMobileNo");
        intentTransactionRemark = getIntent().getExtras().getString("transactionRemark");
        liveId = getIntent().getExtras().getString("liveId");
        operatorname = getIntent().getExtras().getString("operatorname");
        pdate = getIntent().getExtras().getString("pdate");
        ptime = getIntent().getExtras().getString("ptime");
        txid = getIntent().getExtras().getString("txid");
        txStatus = getIntent().getExtras().getString("txStatus");
        typerecharge = getIntent().getExtras().getString("typerecharge");
        imageurl = getIntent().getExtras().getString("imageurl");

        outletDetail = (TextView) findViewById(R.id.outletDetail);
        setOutletDetail();
        manin_lin = (LinearLayout) findViewById(R.id.manin_lin);
        operatorImage = (AppCompatImageView) findViewById(R.id.operatorImage);
        rlCancel = (RelativeLayout) findViewById(R.id.rl_cancel);
        llVia = (LinearLayout) findViewById(R.id.ll_via);
        tvTxnStatus = findViewById(R.id.tv_txstatus);
        rlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        address = (TextView) findViewById(R.id.address);
        accountTitle = (TextView) findViewById(R.id.accountTitle);
        AppUserListResponse companyData = new Gson().fromJson(UtilMethods.INSTANCE.getCompanyProfile(this), AppUserListResponse.class);

        if (companyData != null) {
            String company = "";
            if (!companyData.getCompanyProfile().getName().isEmpty()) {
                company = companyData.getCompanyProfile().getName() + "\n";
            }
            if (!companyData.getCompanyProfile().getAddress().isEmpty()) {
                company = company + Html.fromHtml(companyData.getCompanyProfile().getAddress()) + "\n";
            }
            if (!companyData.getCompanyProfile().getPhoneNo().isEmpty()) {
                company = company + "Landline No : " + companyData.getCompanyProfile().getPhoneNo() + "\n";
            }
            if (!companyData.getCompanyProfile().getMobileNo().isEmpty()) {
                company = company + "Mobile No : " + companyData.getCompanyProfile().getMobileNo() + "\n";
            }
            if (!companyData.getCompanyProfile().getEmailId().isEmpty()) {
                company = company + "Email : " + companyData.getCompanyProfile().getEmailId();
            }
            address.setText(company);
        } else {
            address.setVisibility(View.GONE);
        }
        tvAmount = (TextView) findViewById(R.id.tv_amount);
        tvRechargeMobileNo = (TextView) findViewById(R.id.tv_RechargeMobileNo);
        tvliveId = (TextView) findViewById(R.id.tv_liveId);
        tvoperatorname = (TextView) findViewById(R.id.tv_operatorname);
        tvpdate = (TextView) findViewById(R.id.tv_pdate);
        tvptime = (TextView) findViewById(R.id.tv_ptime);

        tvShare = (TextView) findViewById(R.id.tv_share);
        tvShare = (TextView) findViewById(R.id.tv_share);
        shareView = findViewById(R.id.shareView);
        tvtxid = (TextView) findViewById(R.id.tv_txid);

       /* if (typerecharge.equalsIgnoreCase("SUCCESS")) {
            manin_lin.setVisibility(View.VISIBLE);

        } else {
            manin_lin.setVisibility(View.GONE);

        }*/

        if (imageurl != null) {
            Glide.with(this).load(imageurl)
                    .thumbnail(0.5f)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .apply(new RequestOptions().placeholder(R.drawable.rnd_placeholder).error(R.drawable.rnd_placeholder).diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(operatorImage);
        } else {
            //operatorImage.operatorImage.setImageResource(R.drawable.ic_operator_default_icon);
        }

        tvAmount.setText(getString(R.string.rupiya) + " " + amount);
        tvRechargeMobileNo.setText(RechargeMobileNo);
        tvliveId.setText(liveId);
        tvpdate.setText(pdate);
        tvptime.setText(ptime);
        tvoperatorname.setText(operatorname);
        tvtxid.setText(txid);
        tvTxnStatus.setText(txStatus);

        accountTitle.setText(intentTransactionRemark+"");

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareIt();
            }
        });
    }


    void setOutletDetail() {
        LoginResponse LoginDataResponse = new Gson().fromJson(UtilMethods.INSTANCE.getLoginPref(this), LoginResponse.class);

        String outletDetailStr = "";
        if (LoginDataResponse.getData().getName() != null && !LoginDataResponse.getData().getName().isEmpty()) {
            outletDetailStr = outletDetailStr + "Name : " + LoginDataResponse.getData().getName();
        }
        if (LoginDataResponse.getData().getOutletName() != null && !LoginDataResponse.getData().getOutletName().isEmpty()) {
            outletDetailStr = outletDetailStr + " | Shop Name : " + LoginDataResponse.getData().getOutletName();
        }
        if (LoginDataResponse.getData().getMobileNo() != null && !LoginDataResponse.getData().getMobileNo().isEmpty()) {
            outletDetailStr = outletDetailStr + " | Contact No : " + LoginDataResponse.getData().getMobileNo();
        }
        if (LoginDataResponse.getData().getEmailID() != null && !LoginDataResponse.getData().getEmailID().isEmpty()) {
            outletDetailStr = outletDetailStr + " | Email : " + LoginDataResponse.getData().getEmailID();
        }
        if (LoginDataResponse.getData().getAddress() != null && !LoginDataResponse.getData().getAddress().isEmpty()) {
            outletDetailStr = outletDetailStr + " | Address : " + LoginDataResponse.getData().getAddress();
        }
        outletDetail.setText(outletDetailStr);

    }
    public void shareIt() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSIONS);

            } else {
                getBitmap();
            }
        } else {
            getBitmap();
        }
    }

    private void getBitmap() {
        shareView.setDrawingCacheEnabled(true);
        Bitmap myBitmap = shareView.getDrawingCache();
        saveImage(myBitmap);
        shareView.setDrawingCacheEnabled(false);
    }

    private void saveImage(Bitmap bitmap) {
        if (android.os.Build.VERSION.SDK_INT >= 30) {
            ContentValues values = contentValues();
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + getString(R.string.app_name));
            values.put(MediaStore.Images.Media.IS_PENDING, true);
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "QR_CODE");

            Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            if (uri != null) {
                try {
                    saveImageToStream(bitmap, this.getContentResolver().openOutputStream(uri));
                    values.put(MediaStore.Images.Media.IS_PENDING, false);
                    this.getContentResolver().update(uri, values, null, null);
                    sendMail(uri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        } else {
            File directory = new File(Environment.getExternalStorageDirectory().toString() + "/Pictures/" + getString(R.string.app_name));

            if (!directory.exists()) {
                directory.mkdirs();
            }
            String fileName = "QR_CODE.png";
            File file = new File(directory, fileName);
            try {
                saveImageToStream(bitmap, new FileOutputStream(file));
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
                this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                sendMail(Uri.parse("file://" + file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private ContentValues contentValues() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        }
        return values;
    }

    private void saveImageToStream(Bitmap bitmap, OutputStream outputStream) {
        if (outputStream != null) {
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.close();
            } catch (FileNotFoundException e) {
                Log.e("GREC", e.getMessage(), e);
            } catch (IOException e) {
                Log.e("GREC", e.getMessage(), e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMail(Uri myUri) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                "QR CODE");
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                "QR CODE");
        emailIntent.setType("image/png");
        emailIntent.putExtra(Intent.EXTRA_STREAM, myUri);
        startActivity(Intent.createChooser(emailIntent, "Share via..."));
    }

}
