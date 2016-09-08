package com.xyc.hasee.qrcode;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by hasee on 2016/9/7.
 */
public class BaseActivity extends AppCompatActivity {

    private AlertDialog dialog;

    public View showCenterDialog(Context mContext, String title, int layout_id) {
        View comm_layout = LayoutInflater.from(mContext).inflate(R.layout.comm_dialog_layout, null);
        View diff_layout_view = LayoutInflater.from(mContext).inflate(layout_id, null);
        LinearLayout diff_dialog_layout = (LinearLayout) comm_layout.findViewById(R.id.diff_dialog_layout);
        Button cancleBtn = (Button) comm_layout.findViewById(R.id.cancleBtn);
        TextView title_dialog= ((TextView)comm_layout.findViewById(R.id.title_dialog));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        diff_layout_view.setLayoutParams(params);
        diff_dialog_layout.addView(diff_layout_view);
        title_dialog.setText(title);
        dialog = new AlertDialog.Builder(mContext)
                .setView(comm_layout)
                .create();
        dialog.show();
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return comm_layout;
    }
    public void dissMissDialog(){
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
