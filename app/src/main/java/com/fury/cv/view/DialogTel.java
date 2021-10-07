package com.fury.cv.view;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.fury.cv.R;
import com.google.firebase.crash.FirebaseCrash;

public class DialogTel {

    private Dialog mDialog;
    private TextView mDialogOKButton;
    private Store mDialogUniversalInfoActivity;


    public DialogTel(Store var1) {
        this.mDialogUniversalInfoActivity = var1;

    }

    private void initDialogButtons() {
        this.mDialogOKButton.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                DialogTel.this.mDialog.dismiss();
                Telegram();

            }
        });
    }

    public void Telegram(){

        Uri uri2 = Uri.parse("http://t.me/Fury_studio");
        Intent inestagram2 = new Intent(Intent.ACTION_VIEW, uri2);
        inestagram2.setPackage("org.telegram.messenger");
        try {
            mDialogUniversalInfoActivity.startActivity(inestagram2);
        } catch (ActivityNotFoundException e) {
            mDialogUniversalInfoActivity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://t.me/Fury_studio")));
        }
        mDialogUniversalInfoActivity.Instagram();

    }

    public void dismissDialog() {
        this.mDialog.dismiss();
    }

    public void showDialog() {
        if (this.mDialog == null) {
            this.mDialog = new Dialog(this.mDialogUniversalInfoActivity, R.style.CustomDialogTheme);
        }
        this.mDialog.setContentView(R.layout.dialog_tel);
        this.mDialog.setCancelable(true);
        this.mDialog.show();
        TextView s = (TextView)mDialog.findViewById(R.id.dialog_universal_info_title);
        TextView s1 = (TextView)mDialog.findViewById(R.id.dialog_universal_info_text);
        TextView s2 = (TextView)mDialog.findViewById(R.id.tedad_gift);
        this.mDialogOKButton = (TextView) this.mDialog.findViewById(R.id.dialog_universal_info_ok);
        try {
            s.setTypeface(Store.face);
            s1.setTypeface(Store.face);
            s2.setTypeface(Store.face);
            mDialogOKButton.setTypeface(Store.face);
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("setTypeface"));
        }
        initDialogButtons();
    }
}
