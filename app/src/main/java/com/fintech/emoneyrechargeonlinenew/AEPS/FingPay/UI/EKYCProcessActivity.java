package com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.UI;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fintech.emoneyrechargeonlinenew.Fragments.SdkDetail;
import com.fintech.emoneyrechargeonlinenew.R;


public class EKYCProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekyc_process);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("E-KYC verification Process");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        SdkDetail sdkDetail = (SdkDetail) getIntent().getSerializableExtra("SDKDetail");
        TextView contentTv = findViewById(R.id.contentTv);
        TextView installApp = findViewById(R.id.installApp);
        String username = sdkDetail.getApiOutletID();
        String superMerchantId = sdkDetail.getApiPartnerID();
        contentTv.setText(Html.fromHtml(getString(R.string.ekyc_steps, username + "", superMerchantId + "")));

        installApp.setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://roundpay.net/apk/Icici.verification.apk")));
            } catch (ActivityNotFoundException anfe) {

            }
        });
    }
}
