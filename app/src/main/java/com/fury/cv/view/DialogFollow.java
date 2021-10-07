package com.fury.cv.view;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.fury.cv.R;

public class DialogFollow {

    private Dialog mDialog;
    private TextView mDialogOKButton;
    private TextView dialog_universal_info_store;
    private TextView text1;
    private TextView text2;
    private ViewVideo mDialogUniversalInfoActivity;
    private Cut mDialogUniversalInfoActivity2;
    private Join mDialogUniversalInfoActivity3;
    private Create mDialogUniversalInfoActivity4;
    private Format mDialogUniversalInfoActivity5;
    private Gif mDialogUniversalInfoActivity6;
    private Logo mDialogUniversalInfoActivity7;
    private Music mDialogUniversalInfoActivity8;
    private Voice mDialogUniversalInfoActivity9;
    int s;

    public DialogFollow(ViewVideo var1) {
        this.mDialogUniversalInfoActivity = var1;
        s = 1;
    }
    public DialogFollow(Cut var1) {
        this.mDialogUniversalInfoActivity2 = var1;
        s = 2;
    }
    public DialogFollow(Join var1) {
        this.mDialogUniversalInfoActivity3 = var1;
        s = 3;
    }
    public DialogFollow(Create var1) {
        this.mDialogUniversalInfoActivity4 = var1;
        s = 4;
    }
    public DialogFollow(Format var1) {
        this.mDialogUniversalInfoActivity5 = var1;
        s = 5;
    }
    public DialogFollow(Gif var1) {
        this.mDialogUniversalInfoActivity6 = var1;
        s = 6;
    }
    public DialogFollow(Logo var1) {
        this.mDialogUniversalInfoActivity7 = var1;
        s = 7;
    }
    public DialogFollow(Music var1) {
        this.mDialogUniversalInfoActivity8 = var1;
        s = 8;
    }
    public DialogFollow(Voice var1) {
        this.mDialogUniversalInfoActivity9 = var1;
        s = 9;
    }

    private void initDialogButtons() {
        this.mDialogOKButton.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                DialogFollow.this.mDialog.dismiss();

            }
        });
        this.dialog_universal_info_store.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("bazaar://collection?slug=by_author&aid=" + "448376501836"));
                    intent.setPackage("com.farsitel.bazaar");
                    if (s==1){
                        mDialogUniversalInfoActivity.startActivity(intent);
                    }else if (s==2){
                        mDialogUniversalInfoActivity2.startActivity(intent);
                    }else if (s==3){
                        mDialogUniversalInfoActivity3.startActivity(intent);
                    }else if (s==4){
                        mDialogUniversalInfoActivity4.startActivity(intent);
                    }else if (s==5){
                        mDialogUniversalInfoActivity5.startActivity(intent);
                    }else if (s==6){
                        mDialogUniversalInfoActivity6.startActivity(intent);
                    }else if (s==7){
                        mDialogUniversalInfoActivity7.startActivity(intent);
                    }else if (s==8){
                        mDialogUniversalInfoActivity8.startActivity(intent);
                    }else if (s==9){
                        mDialogUniversalInfoActivity9.startActivity(intent);
                    }
                }catch (Exception e){
                    if (s==1){
                        mDialogUniversalInfoActivity.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("bazaar://collection?slug=by_author&aid=" + "448376501836")));
                    }else if (s==2){
                        mDialogUniversalInfoActivity2.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("bazaar://collection?slug=by_author&aid=" + "448376501836")));
                    }else if (s==3){
                        mDialogUniversalInfoActivity3.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("bazaar://collection?slug=by_author&aid=" + "448376501836")));
                    }else if (s==4){
                        mDialogUniversalInfoActivity4.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("bazaar://collection?slug=by_author&aid=" + "448376501836")));
                    }else if (s==5){
                        mDialogUniversalInfoActivity5.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("bazaar://collection?slug=by_author&aid=" + "448376501836")));
                    }else if (s==6){
                        mDialogUniversalInfoActivity6.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("bazaar://collection?slug=by_author&aid=" + "448376501836")));
                    }else if (s==7){
                        mDialogUniversalInfoActivity7.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("bazaar://collection?slug=by_author&aid=" + "448376501836")));
                    }else if (s==8){
                        mDialogUniversalInfoActivity8.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("bazaar://collection?slug=by_author&aid=" + "448376501836")));
                    }else if (s==9){
                        mDialogUniversalInfoActivity9.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("bazaar://collection?slug=by_author&aid=" + "448376501836")));
                    }
                }

                DialogFollow.this.mDialog.dismiss();

            }
        });
    }


    public void dismissDialog() {
        this.mDialog.dismiss();
    }

    public void showDialog() {
        if (this.mDialog == null) {
            if (s == 1){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity, R.style.CustomDialogTheme);
            }else if (s == 2){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity2, R.style.CustomDialogTheme);
            }else if (s == 3){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity3, R.style.CustomDialogTheme);
            }else if (s == 4){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity4, R.style.CustomDialogTheme);
            }else if (s == 5){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity5, R.style.CustomDialogTheme);
            }else if (s == 6){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity6, R.style.CustomDialogTheme);
            }else if (s == 7){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity7, R.style.CustomDialogTheme);
            }else if (s == 8){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity8, R.style.CustomDialogTheme);
            }else if (s == 9){
                this.mDialog = new Dialog(this.mDialogUniversalInfoActivity9, R.style.CustomDialogTheme);
            }
        }
        this.mDialog.setContentView(R.layout.dialog_follow);
        this.mDialog.setCancelable(true);
        this.mDialog.show();
        this.text1 = (TextView) this.mDialog.findViewById(R.id.text1);
        this.text2 = (TextView) this.mDialog.findViewById(R.id.text2);
        this.mDialogOKButton = (TextView) this.mDialog.findViewById(R.id.dialog_universal_info_cancel);
        this.dialog_universal_info_store = (TextView) this.mDialog.findViewById(R.id.dialog_universal_info_store);


        initDialogButtons();
    }
}
