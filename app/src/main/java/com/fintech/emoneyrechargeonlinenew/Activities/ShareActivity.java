package com.fintech.emoneyrechargeonlinenew.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.fintech.emoneyrechargeonlinenew.Auth.dto.LoginResponse;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;

public class ShareActivity extends AppCompatActivity {

    private LinearLayout msgView;
    private LinearLayout whatsappView;
    private LinearLayout facebookView;
    private LinearLayout twitterView;
    private LinearLayout emailView;
    private LinearLayout shareView;
    private AppCompatImageView closeIv;
    private LoginResponse loginPrefResponse;
    private SharedPreferences myPrefs;
    private String shareMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String getLoginPref = myPrefs.getString(ApplicationConstant.INSTANCE.LoginPref, "");
        loginPrefResponse = new Gson().fromJson(getLoginPref, LoginResponse.class);
        shareMessage=""+getString(R.string.share_app);
        findViews();
        clickView();
    }

    private void findViews() {
        msgView = (LinearLayout) findViewById(R.id.msgView);
        whatsappView = (LinearLayout) findViewById(R.id.whatsappView);
        facebookView = (LinearLayout) findViewById(R.id.facebookView);
        twitterView = (LinearLayout) findViewById(R.id.twitterView);
        emailView = (LinearLayout) findViewById(R.id.emailView);
        shareView = (LinearLayout) findViewById(R.id.shareView);
        closeIv = (AppCompatImageView) findViewById(R.id.closeIv);
    }

    private void clickView() {

        msgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt("sms");
            }
        });
        whatsappView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt("com.whatsapp");
            }
        });
        facebookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt("com.facebook.katana");
            }
        });
        twitterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt("com.twitter.android");
            }
        });
        emailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt("email");
            }
        });
        shareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt(null);
            }
        });
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void shareIt(String packageName) {

        try {
            // AppUserListResponse companyData = new Gson().fromJson(UtilMethods.INSTANCE.getCompanyProfile(this), AppUserListResponse.class);

            if (packageName != null && packageName.equalsIgnoreCase("sms")) {
                Uri uri = Uri.parse("smsto:");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", shareMessage);
                startActivity(Intent.createChooser(intent, "choose one"));
            } else if (packageName != null && packageName.equalsIgnoreCase("email")) {
                Uri uri = Uri.parse("mailto:");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(intent, "choose one"));
            } else {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                if (packageName != null) {
                    shareIntent.setPackage(packageName);
                }
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            if (packageName.equalsIgnoreCase("sms")) {
                sendSms();
            } else if (packageName.equalsIgnoreCase("email")) {
                sendEmail();
            } else {
                Toast.makeText(this, "Sorry, App not found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sendEmail() {
        try {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(intent, "choose one"));
        } catch (ActivityNotFoundException anfe) {

        } catch (Exception e) {

        }
    }

    void sendSms() {
        try {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) //At least KitKat
            {
                String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); //Need to change the build to API 19
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                if (defaultSmsPackageName != null)//Can be null in case that there is no default, then the user would be able to choose any app that support this intent.
                {
                    sendIntent.setPackage(defaultSmsPackageName);
                }
                startActivity(Intent.createChooser(sendIntent, "choose one"));

            } else //For early versions, do what worked for you before.
            {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("sms_body", shareMessage);
                startActivity(Intent.createChooser(sendIntent, "choose one"));
            }
        } catch (ActivityNotFoundException anfe) {

        } catch (Exception e) {

        }
    }
}


