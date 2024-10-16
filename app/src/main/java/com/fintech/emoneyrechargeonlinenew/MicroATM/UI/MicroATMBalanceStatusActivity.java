package com.fintech.emoneyrechargeonlinenew.MicroATM.UI;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fingpay.microatmsdk.utils.Constants;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.Adapter.ReceiptDetailListAdapter;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.dto.ReceiptObject;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.roundpay.emoneylib.Utils.Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MicroATMBalanceStatusActivity extends AppCompatActivity {

    private LinearLayout shareView;
    private AppCompatImageView statusIcon;
    private AppCompatTextView statusTv;
    private AppCompatTextView statusMsg;
    private LinearLayout addressDetailsView;
    private AppCompatTextView companyName;
    private AppCompatTextView address;
    private RecyclerView recyclerView;
    private AppCompatTextView outletDetail;
    TextView titleTv, bankTv, amtTv;
    private LinearLayout btRepeat;
    private LinearLayout btShare;
    private LinearLayout btWhatsapp;
    private AppCompatImageView closeIv,appLogoIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_atm_balance_slip);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        findViews();
        setUiData();
        UtilMethods.INSTANCE.setAppLogoIconUI(this,appLogoIv);
        closeIv.setOnClickListener(v -> finish());


        btShare.setOnClickListener(v -> shareit(false));

        btRepeat.setOnClickListener(v -> finish());

        if (UtilMethods.INSTANCE.isPackageInstalled("com.whatsapp", getPackageManager())) {
            btWhatsapp.setVisibility(View.VISIBLE);
            btWhatsapp.setOnClickListener(view -> shareit(true));
        } else {
            btWhatsapp.setVisibility(View.GONE);
        }
    }


    private void findViews() {
        appLogoIv =findViewById(R.id.appLogoIv);
        shareView = (LinearLayout) findViewById(R.id.shareView);
        titleTv = findViewById(R.id.titleTv);
        statusIcon = (AppCompatImageView) findViewById(R.id.statusIcon);
        statusTv = (AppCompatTextView) findViewById(R.id.statusTv);
        statusMsg = (AppCompatTextView) findViewById(R.id.statusMsg);
        addressDetailsView = (LinearLayout) findViewById(R.id.addressDetailsView);
        companyName = (AppCompatTextView) findViewById(R.id.companyName);
        address = (AppCompatTextView) findViewById(R.id.address);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        outletDetail = (AppCompatTextView) findViewById(R.id.outletDetail);
        btRepeat = (LinearLayout) findViewById(R.id.bt_repeat);
        btShare = (LinearLayout) findViewById(R.id.bt_share);
        btWhatsapp = (LinearLayout) findViewById(R.id.bt_whatsapp);
        closeIv = (AppCompatImageView) findViewById(R.id.closeIv);
        bankTv = findViewById(R.id.bankTv);
        amtTv = findViewById(R.id.amtTv);

    }


    void setUiData() {
        boolean status = getIntent().getBooleanExtra(Constants.TRANS_STATUS, false);
        String response = getIntent().getStringExtra(Constants.MESSAGE);
        double transAmount = getIntent().getDoubleExtra(Constants.TRANS_AMOUNT, 0);
        double balAmount = getIntent().getDoubleExtra(Constants.BALANCE_AMOUNT, 0);
        String bankRrn = getIntent().getStringExtra(Constants.RRN);
        String transType = getIntent().getStringExtra(Constants.TRANS_TYPE);
        int type = getIntent().getIntExtra(Constants.TYPE, Constants.CASH_WITHDRAWAL);
        String cardNum = getIntent().getStringExtra(Constants.CARD_NUM);
        String bankName = getIntent().getStringExtra(Constants.BANK_NAME);
        String cardType = getIntent().getStringExtra(Constants.CARD_TYPE);
        String terminalId = getIntent().getStringExtra(Constants.TERMINAL_ID);
        String fpId = getIntent().getStringExtra(Constants.FP_TRANS_ID);
        String transId = getIntent().getStringExtra(Constants.TRANS_ID);
        String appTid = getIntent().getStringExtra("APP_TID");

        if (status) {
            statusIcon.setImageResource(R.drawable.ic_check_mark);
            ImageViewCompat.setImageTintList(statusIcon, AppCompatResources.getColorStateList(this, R.color.green));
            statusTv.setTextColor(getResources().getColor(R.color.green));
            statusTv.setText("Bank balance fetched successfully");
            if (appTid != null && !appTid.isEmpty()) {
                statusMsg.setText("Transaction with transaction id " + appTid + " completed successfully");
            } else if (transId != null && !transId.isEmpty()) {
                statusMsg.setText("Transaction with transaction id " + transId + " completed successfully");
            } else if (bankRrn != null && !bankRrn.isEmpty()) {
                statusMsg.setText("Transaction with bank rrn " + bankRrn + " completed successfully");
            } else {
                statusMsg.setText("Transaction completed successfully");
            }
        } else {
            statusIcon.setImageResource(R.drawable.ic_cross_mark);
            ImageViewCompat.setImageTintList(statusIcon, AppCompatResources.getColorStateList(this, R.color.color_red));
            statusTv.setTextColor(getResources().getColor(R.color.color_red));
            statusTv.setText("Sorry, bank balance did not fetch");
            if (response != null && !response.isEmpty()) {
                statusMsg.setText(response + "");
            } else {
                statusMsg.setText("Sorry, Transaction Failed, Please try after some time");
            }
        }

        if (type == Constants.CASH_WITHDRAWAL) {
            titleTv.setText("Cash Withdrawal Details");
        } else if (type == Constants.CASH_DEPOSIT) {
            titleTv.setText("Cash Deposit Details");
        } else if (type == Constants.BALANCE_ENQUIRY) {
            titleTv.setText("Balance Enquiry Details");
        } else if (type == Constants.MINI_STATEMENT) {
            titleTv.setText("Mini Statement Details");
        } else if (type == Constants.CARD_ACTIVATION) {
            titleTv.setText("Card Activation Details");
        } else if (type == Constants.PIN_RESET) {
            titleTv.setText("Reset Pin Details");
        } else if (type == Constants.CHANGE_PIN) {
            titleTv.setText("Change Pin Details");
        }

        ArrayList<ReceiptObject> mReceiptObjects = new ArrayList<>();
        if (bankName != null && !bankName.isEmpty()) {
            //  mReceiptObjects.add(new ReceiptObject("Bank Name", bankName));
            bankTv.setText(bankName + "");
        }

        if (appTid != null && !appTid.isEmpty()) {
            mReceiptObjects.add(new ReceiptObject("Transaction Id", appTid));
        } else if (transId != null && !transId.isEmpty()) {
            mReceiptObjects.add(new ReceiptObject("Transaction Id", transId));
        }

        if (transAmount != 0) {
            mReceiptObjects.add(new ReceiptObject("Transaction Amount", Utility.INSTANCE.formatedAmountWithRupees(transAmount + "")));
        }
        /*if (balAmount != 0 ) {*/
        //mReceiptObjects.add(new ReceiptObject("Balance Amount", Utility.INSTANCE.formatedAmountWithRupees(balAmount + "")));
        /* }*/
        amtTv.setText(Utility.INSTANCE.formatedAmountWithRupees(balAmount + ""));


        if (bankRrn != null && !bankRrn.isEmpty()) {
            mReceiptObjects.add(new ReceiptObject("Bank RRN", bankRrn));
        }

       /* if (fpId != null && !fpId.isEmpty()) {
            mReceiptObjects.add(new ReceiptObject("FP Transaction Id", fpId));
        }*/

        if (terminalId != null && !terminalId.isEmpty()) {
            mReceiptObjects.add(new ReceiptObject("Terminal Id", terminalId));
        }

        /*if (transType != null && !transType.isEmpty()) {
            mReceiptObjects.add(new ReceiptObject("Transaction Type", transType));
        } else {
            if (type == Constants.CASH_WITHDRAWAL) {
                mReceiptObjects.add(new ReceiptObject("Transaction Type", "Cash Withdrawal"));
            } else if (type == Constants.CASH_DEPOSIT) {
                mReceiptObjects.add(new ReceiptObject("Transaction Type", "Cash Deposit"));
            } else if (type == Constants.BALANCE_ENQUIRY) {
                mReceiptObjects.add(new ReceiptObject("Transaction Type", "Balance Enquiry"));
            } else if (type == Constants.MINI_STATEMENT) {
                mReceiptObjects.add(new ReceiptObject("Transaction Type", "Mini Statement"));
            } else if (type == Constants.CARD_ACTIVATION) {
                mReceiptObjects.add(new ReceiptObject("Transaction Type", "Card Activation"));
            } else if (type == Constants.PIN_RESET) {
                mReceiptObjects.add(new ReceiptObject("Transaction Type", "Reset Pin"));
            } else if (type == Constants.CHANGE_PIN) {
                mReceiptObjects.add(new ReceiptObject("Transaction Type", "Change Pin"));
            }
        }*/
        if (cardNum != null && !cardNum.isEmpty()) {

            if (cardType != null && !cardType.isEmpty()) {
                bankTv.setText(bankTv.getText().toString() + "\n" + cardNum + " [" + cardType + "]");
                // mReceiptObjects.add(new ReceiptObject("Card Number", cardNum + " [" + cardType + "]"));

            } else {
                bankTv.setText(bankTv.getText().toString() + "\n" + cardNum + "");
                //mReceiptObjects.add(new ReceiptObject("Card Number", cardNum + ""));
            }
        }


        SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMM yyyy hh:mm aa");

        try {
            String dateStr = sdfDate.format(new Date());
            mReceiptObjects.add(new ReceiptObject("Transaction Date", dateStr + ""));
        } catch (Exception e) {

        }

        recyclerView.setAdapter(new ReceiptDetailListAdapter(mReceiptObjects, this));

        //  setCompanyDetail();
        // setOutletDetail();
    }



    public void shareit(boolean isWhatsapp) {
        Bitmap myBitmap = Bitmap.createBitmap(shareView.getWidth(), shareView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(myBitmap);
        shareView.layout(0, 0, shareView.getWidth(), shareView.getHeight());
        shareView.draw(c);
        saveImage(myBitmap, isWhatsapp);

    }

    private void saveImage(Bitmap bitmap, boolean isWhatsapp) {
        if (Build.VERSION.SDK_INT >= 30) {
            ContentValues values = contentValues();
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + getString(R.string.app_name));
            values.put(MediaStore.Images.Media.IS_PENDING, true);

            Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            if (uri != null) {
                try {
                    saveImageToStream(bitmap, this.getContentResolver().openOutputStream(uri));
                    values.put(MediaStore.Images.Media.IS_PENDING, false);
                    this.getContentResolver().update(uri, values, null, null);
                    if (isWhatsapp) {
                        openWhatsapp(uri);
                    } else {
                        sendMail(uri);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        } else {
            File directory = new File(Environment.getExternalStorageDirectory().toString() + "/Pictures/" + getString(R.string.app_name));

            if (!directory.exists()) {
                directory.mkdirs();
            }
            String fileName = System.currentTimeMillis() + ".png";
            File file = new File(directory, fileName);
            try {
                saveImageToStream(bitmap, new FileOutputStream(file));
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
                this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                if (isWhatsapp) {
                    openWhatsapp(Uri.parse("file://" + file));
                } else {
                    sendMail(Uri.parse("file://" + file));
                }
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

    /*public void saveBitmap(Bitmap bitmap, boolean isWhatsapp) {
        // Create a media file name
        Log.v("first", "first");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());

        String filePath = Environment.getExternalStorageDirectory().toString()
                + "/" + timeStamp + ".jpg";
        File imagePath = new File(filePath);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            Log.v("first", "second");
            if (isWhatsapp) {
                openWhatsapp(filePath);
            } else {
                sendMail(filePath);
            }
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }*/

    public void sendMail(Uri myUri) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                "AEPS Receipt");
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                "Receipt");
        emailIntent.setType("image/png");

        emailIntent.putExtra(Intent.EXTRA_STREAM, myUri);
        startActivity(Intent.createChooser(emailIntent, "Share via..."));
    }


    void openWhatsapp(Uri myUri) {

        try {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "AEPS Receipt");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Receipt");
            sendIntent.setType("image/png");
            sendIntent.putExtra(Intent.EXTRA_STREAM, myUri);
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (ActivityNotFoundException e) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp"));
            startActivity(intent);


        }

    }


}
