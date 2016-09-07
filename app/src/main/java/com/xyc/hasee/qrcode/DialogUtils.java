package com.xyc.hasee.qrcode;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by hasee on 2016/9/7.
 */
public class DialogUtils {
    public static Dialog showCenterDialog(Context mContext, String title, int  layout_id){
        View net_view = LayoutInflater.from(mContext).inflate(layout_id, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        net_view.setLayoutParams(params);
        AlertDialog dialog=new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPositiveClick();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
        return  dialog;
    }
    public static  void  setPositiveClick(){

    }
}
