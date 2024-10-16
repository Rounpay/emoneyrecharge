package com.fintech.emoneyrechargeonlinenew.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.fintech.emoneyrechargeonlinenew.Api.Response.AppUserListResponse;
import com.fintech.emoneyrechargeonlinenew.Auth.dto.LoginResponse;
import com.fintech.emoneyrechargeonlinenew.BuildConfig;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class QRCodePaymentActivity extends AppCompatActivity {

    TextView custCare, upiId, OutletName;
    ImageView qrcode;
    private CustomLoader loader;
    LinearLayout shareView;
    private int REQUEST_PERMISSIONS = 1234;
    private Snackbar mSnackBar;
    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    boolean isDownload;
    View btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_payment);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        custCare = findViewById(R.id.custCare);
        upiId = findViewById(R.id.upiId);
        qrcode = findViewById(R.id.qrcode);
        shareView = findViewById(R.id.shareView);
        btnView = findViewById(R.id.btnView);
        OutletName = findViewById(R.id.OutletName);
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar);
        String mLoginResponse = UtilMethods.INSTANCE.getLoginPref(this);
        LoginResponse LoginDataResponse = new Gson().fromJson(mLoginResponse, LoginResponse.class);
        OutletName.setText(LoginDataResponse.getData().getName() + "");
        UtilMethods.INSTANCE.setAppLogoIconUI(this,findViewById(R.id.logoIv));
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.skipMemoryCache(true);
        requestOptions.error(R.drawable.nodata);
        Glide.with(this).load(ApplicationConstant.INSTANCE.baseQrImageUrl + LoginDataResponse.getData().getUserID() +
                "&appid=" + ApplicationConstant.INSTANCE.APP_ID + "&imei=" + UtilMethods.INSTANCE.getIMEI(this) + "&regKey=&version=" + BuildConfig.VERSION_NAME +
                "&serialNo=" + UtilMethods.INSTANCE.getSerialNo(this) + "&sessionID=" + LoginDataResponse.getData().getSessionID() +
                "&session=" + LoginDataResponse.getData().getSession() + "&loginTypeID=" + LoginDataResponse.getData().getLoginTypeID()).
                apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        btnView.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(qrcode);

        AppUserListResponse companyProfileData = new Gson().fromJson(UtilMethods.INSTANCE.getCompanyProfile(this), AppUserListResponse.class);
        if (companyProfileData != null && companyProfileData.getCompanyProfile() != null) {
            String value = "";
            if (companyProfileData.getCompanyProfile().getCustomerCareMobileNos() != null && !companyProfileData.getCompanyProfile().getCustomerCareMobileNos().isEmpty()) {
                value = " -" + companyProfileData.getCompanyProfile().getCustomerCareMobileNos();
            }
            if (companyProfileData.getCompanyProfile().getCustomerPhoneNos() != null && !companyProfileData.getCompanyProfile().getCustomerPhoneNos().isEmpty()) {
                value = value + " -" + companyProfileData.getCompanyProfile().getCustomerPhoneNos();
            }
            if (companyProfileData.getCompanyProfile().getCustomerWhatsAppNos() != null && !companyProfileData.getCompanyProfile().getCustomerWhatsAppNos().isEmpty()) {
                value = value + " -" + companyProfileData.getCompanyProfile().getCustomerWhatsAppNos();
            }

            custCare.setText("CUSTOMER CARE" + value);
        } else {
            custCare.setVisibility(View.GONE);
        }


        findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDownload = true;
                shareit();
            }
        });

        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDownload = false;
                shareit();
            }
        });

    }


    public void shareit() {
        Bitmap myBitmap = Bitmap.createBitmap(shareView.getWidth(), shareView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(myBitmap);
        shareView.layout(0, 0, shareView.getWidth(), shareView.getHeight());
        shareView.draw(c);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // showWarningSnack(R.string.str_ShowOnPermisstion, "Allow", false);
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSIONS);
        } else {
            saveBitmap(isDownload, myBitmap);

        }


    }

    public void saveBitmap(boolean isDownload, Bitmap bitmap) {
        // Create a media file name
        /*Log.v("first", "first");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());*/

        /*String filePath = getExternalCacheDir()
                + "/QR_Code.jpg";*/

        File filePath = new File(Environment.getExternalStorageDirectory().toString() + "/" + getString(R.string.app_name));

        if (!filePath.exists()) {
            filePath.mkdir();
        }
        File imagePath = new File(filePath + "/QR_Code.jpg");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            Log.v("first", "second");
            if (isDownload) {
                Toast.makeText(this, "Successfully Download", Toast.LENGTH_SHORT).show();
                MediaScannerConnection.scanFile(this, new String[]{imagePath.getPath()}, new String[]{"image/jpeg"}, null);
            } else {
                sendMail(imagePath.toString());
            }
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    public void sendMail(String path) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                "QRCode");
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                "QRCode");
        emailIntent.setType("image/png");
        Uri myUri = Uri.parse("file://" + path);
        emailIntent.putExtra(Intent.EXTRA_STREAM, myUri);
        startActivity(Intent.createChooser(emailIntent, "Share via..."));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS) {

            int permissionCheck = PackageManager.PERMISSION_GRANTED;
            for (int permission : grantResults) {
                permissionCheck = permissionCheck + permission;
            }
            if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {

                shareit();
            } else {
                showWarningSnack(R.string.str_ShowOnPermisstionDenied, "Enable", true);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


    void showWarningSnack(int stringId, String btn, final boolean isForSetting) {
        if (mSnackBar != null && mSnackBar.isShown()) {
            return;
        }
        mSnackBar = Snackbar.make(findViewById(android.R.id.content), stringId,
                Snackbar.LENGTH_INDEFINITE).setAction(btn,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isForSetting) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            startActivity(intent);
                        } else {
                            ActivityCompat.requestPermissions(QRCodePaymentActivity.this, PERMISSIONS, REQUEST_PERMISSIONS);
                        }

                    }
                });

        mSnackBar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        TextView mainTextView = (TextView) (mSnackBar.getView()).
                findViewById(R.id.snackbar_text);
        mainTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen._12sdp));
        mainTextView.setMaxLines(4);
        mSnackBar.show();
    }

}
