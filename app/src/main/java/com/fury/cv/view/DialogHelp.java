package com.fury.cv.view;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.fury.cv.R;
import com.google.firebase.crash.FirebaseCrash;

public class DialogHelp {

    private Dialog mDialog;
    private TextView mDialogOKButton;
    private Voice mDialogUniversalInfoActivity9;
    private Music mDialogUniversalInfoActivity8;
    private Logo mDialogUniversalInfoActivity7;
    private Gif mDialogUniversalInfoActivity6;
    private Format mDialogUniversalInfoActivity5;
    private Create mDialogUniversalInfoActivity4;
    private Join mDialogUniversalInfoActivity3;
    private Cut mDialogUniversalInfoActivity2;
    private Store mDialogUniversalInfoActivity;
    String txthelp;
    int s ;
    public DialogHelp(Store var1,String help) {
        this.mDialogUniversalInfoActivity = var1;
        s=1;
        txthelp = help;
    }

    public DialogHelp(Cut var1,String help) {
        this.mDialogUniversalInfoActivity2 = var1;
        s=2;
        txthelp = help;
    }
    public DialogHelp(Join var1,String help) {
        this.mDialogUniversalInfoActivity3 = var1;
        s=3;
        txthelp = help;
    }
    public DialogHelp(Create var1,String help) {
        this.mDialogUniversalInfoActivity4 = var1;
        s=4;
        txthelp = help;
    }
    public DialogHelp(Format var1,String help) {
        this.mDialogUniversalInfoActivity5 = var1;
        s=5;
        txthelp = help;
    }
    public DialogHelp(Gif var1,String help) {
        this.mDialogUniversalInfoActivity6 = var1;
        s=6;
        txthelp = help;
    }
    public DialogHelp(Logo var1,String help) {
        this.mDialogUniversalInfoActivity7 = var1;
        s=7;
        txthelp = help;
    }
    public DialogHelp(Music var1,String help) {
        this.mDialogUniversalInfoActivity8 = var1;
        s=8;
        txthelp = help;
    }
    public DialogHelp(Voice var1,String help) {
        this.mDialogUniversalInfoActivity9 = var1;
        s=9;
        txthelp = help;
    }

    private void initDialogButtons() {
        this.mDialogOKButton.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {

                DialogHelp.this.mDialog.dismiss();

            }
        });
    }



    public void dismissDialog() {
        this.mDialog.dismiss();
    }

    public void showDialog() {
        if (this.mDialog == null) {
            if (s == 1){
                try {
                    this.mDialog = new Dialog(this.mDialogUniversalInfoActivity, R.style.CustomDialogTheme);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("style 1"));
                }
            }else if (s == 2){
                try {
                    this.mDialog = new Dialog(this.mDialogUniversalInfoActivity2, R.style.CustomDialogTheme);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("style 2"));
                }
            }else if (s == 3){
                try {
                    this.mDialog = new Dialog(this.mDialogUniversalInfoActivity3, R.style.CustomDialogTheme);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("style 3"));
                }
            }else if (s == 4){
                try {
                    this.mDialog = new Dialog(this.mDialogUniversalInfoActivity4, R.style.CustomDialogTheme);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("style 4"));
                }
            }else if (s == 5){
                try {
                    this.mDialog = new Dialog(this.mDialogUniversalInfoActivity5, R.style.CustomDialogTheme);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("style 5"));
                }
            }else if (s == 6){
                try {
                    this.mDialog = new Dialog(this.mDialogUniversalInfoActivity6, R.style.CustomDialogTheme);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("style 6"));
                }
            }else if (s == 7){
                try {
                    this.mDialog = new Dialog(this.mDialogUniversalInfoActivity7, R.style.CustomDialogTheme);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("style 7"));
                }
            }else if (s == 8){
                try {
                    this.mDialog = new Dialog(this.mDialogUniversalInfoActivity8, R.style.CustomDialogTheme);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("style 8"));
                }
            }else if (s == 9){
                try {
                    this.mDialog = new Dialog(this.mDialogUniversalInfoActivity9, R.style.CustomDialogTheme);
                } catch (Exception e) {
                    FirebaseCrash.report(new Exception("style 9"));
                }
            }
        }
        this.mDialog.setContentView(R.layout.dialog_help);
        this.mDialog.setCancelable(true);
        this.mDialog.show();
        TextView s = (TextView)mDialog.findViewById(R.id.dialog_universal_info_title);
        TextView s1 = (TextView)mDialog.findViewById(R.id.dialog_universal_info_text);
        this.mDialogOKButton = (TextView) this.mDialog.findViewById(R.id.dialog_universal_info_ok);
        s1.setText(txthelp);
        if (this.s == 1){
            try {
                s.setTypeface(mDialogUniversalInfoActivity.face);
                s1.setTypeface(mDialogUniversalInfoActivity.face);
                mDialogOKButton.setTypeface(mDialogUniversalInfoActivity.face);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("setTypeface 1"));
            }
        }else if (this.s == 2){
            try {
                s.setTypeface(mDialogUniversalInfoActivity2.face);
                s1.setTypeface(mDialogUniversalInfoActivity2.face);
                mDialogOKButton.setTypeface(mDialogUniversalInfoActivity2.face);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("setTypeface 2"));
            }
        }else if (this.s == 3){
            try {
                s.setTypeface(mDialogUniversalInfoActivity3.face);
                s1.setTypeface(mDialogUniversalInfoActivity3.face);
                mDialogOKButton.setTypeface(mDialogUniversalInfoActivity3.face);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("setTypeface 3"));
            }
        }else if (this.s == 4){
            try {
                s.setTypeface(mDialogUniversalInfoActivity4.face);
                s1.setTypeface(mDialogUniversalInfoActivity4.face);
                mDialogOKButton.setTypeface(mDialogUniversalInfoActivity4.face);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("setTypeface 4"));
            }
        }else if (this.s == 5){
            try {
                s.setTypeface(mDialogUniversalInfoActivity5.face);
                s1.setTypeface(mDialogUniversalInfoActivity5.face);
                mDialogOKButton.setTypeface(mDialogUniversalInfoActivity5.face);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("setTypeface 5"));
            }
        }else if (this.s == 6){
            try {
                s.setTypeface(mDialogUniversalInfoActivity6.face);
                s1.setTypeface(mDialogUniversalInfoActivity6.face);
                mDialogOKButton.setTypeface(mDialogUniversalInfoActivity6.face);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("setTypeface 6"));
            }
        }else if (this.s == 7){
            try {
                s.setTypeface(mDialogUniversalInfoActivity7.face);
                s1.setTypeface(mDialogUniversalInfoActivity7.face);
                mDialogOKButton.setTypeface(mDialogUniversalInfoActivity7.face);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("setTypeface 7"));
            }
        }else if (this.s == 8){
            try {
                s.setTypeface(mDialogUniversalInfoActivity8.face);
                s1.setTypeface(mDialogUniversalInfoActivity8.face);
                mDialogOKButton.setTypeface(mDialogUniversalInfoActivity8.face);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("setTypeface 8"));
            }
        }else if (this.s == 9){
            try {
                s.setTypeface(mDialogUniversalInfoActivity9.face);
                s1.setTypeface(mDialogUniversalInfoActivity9.face);
                mDialogOKButton.setTypeface(mDialogUniversalInfoActivity9.face);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("setTypeface 9"));
            }
        }

        initDialogButtons();
    }
}
