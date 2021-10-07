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

public class DialogIns {

    private Dialog mDialog;
    private TextView mDialogOKButton;
    private Store mDialogUniversalInfoActivity;


    public DialogIns(Store var1) {
        this.mDialogUniversalInfoActivity = var1;

    }

    private void initDialogButtons() {
        this.mDialogOKButton.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                DialogIns.this.mDialog.dismiss();
                Instagram();

            }
        });
    }

    public void Instagram(){
        Uri uri = Uri.parse("http://instagram.com/_u/fury_studio_ir");
        Intent inestagram = new Intent(Intent.ACTION_VIEW, uri);
        inestagram.setPackage("com.instagram.android");
        try {
            mDialogUniversalInfoActivity.startActivity(inestagram);
        } catch (ActivityNotFoundException e) {
            mDialogUniversalInfoActivity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/fury_studio_ir")));
        }

        mDialogUniversalInfoActivity.coin_plus = 2 + mDialogUniversalInfoActivity.coinint;
        String n = String.valueOf(mDialogUniversalInfoActivity.coin_plus);
        mDialogUniversalInfoActivity.CoinSingle.setText(n);
        mDialogUniversalInfoActivity.one_play_editor.putInt("COIN", mDialogUniversalInfoActivity.coin_plus);
        mDialogUniversalInfoActivity.one_play_editor.apply();
            FirebaseCrash.report(new Exception("instagram_save anjamshod"));

    }

    public void dismissDialog() {
        this.mDialog.dismiss();
    }

    public void showDialog() {
        if (this.mDialog == null) {
            this.mDialog = new Dialog(this.mDialogUniversalInfoActivity, R.style.CustomDialogTheme);
        }
        this.mDialog.setContentView(R.layout.dialog_ins);
        this.mDialog.setCancelable(true);
        this.mDialog.show();
        TextView s = (TextView)mDialog.findViewById(R.id.dialog_universal_info_title);
        TextView s1 = (TextView)mDialog.findViewById(R.id.dialog_universal_info_text);
        TextView s2 = (TextView)mDialog.findViewById(R.id.tedad_gift);
        this.mDialogOKButton = (TextView) this.mDialog.findViewById(R.id.dialog_universal_info_ok);
        try {
            s.setTypeface(mDialogUniversalInfoActivity.face);
            s1.setTypeface(mDialogUniversalInfoActivity.face);
            s2.setTypeface(mDialogUniversalInfoActivity.face);
            mDialogOKButton.setTypeface(mDialogUniversalInfoActivity.face);
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("setTypeface"));
        }
        initDialogButtons();
    }
}
