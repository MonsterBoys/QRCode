package com.xyc.hasee.qrcode;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.youmi.android.normal.spot.SpotManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext = MainActivity.this;
    private ImageView careBtn;
    private TextView net_tv, project_tv, okBtn, img_tv, card_tv, phone_tv, email_tv, txt_tv, msg_tv, file_tv, wifi_tv, location_tv, active_tv;
    private ImageView qrCode;
    private String retval;
    private EditText net_content;
    private  boolean isInitNet=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YouMiUtils.initYoumi(mContext);
        initView();
    }

    public String getNetDatas(String s) {
        OkHttpClient mClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(UrlUtils.net_url + s + UrlUtils.net_url_type)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str_content = response.body().string();
                try {
                    JSONObject object = new JSONObject(str_content);
                    retval = object.getString("retval");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return retval;
    }

    private void initView() {
        careBtn = (ImageView) findViewById(R.id.careBtn);
        qrCode = (ImageView) findViewById(R.id.qrCode);
        net_tv = (TextView) findViewById(R.id.net_tv);
        okBtn = (TextView) findViewById(R.id.okBtn);
        project_tv = (TextView) findViewById(R.id.project_tv);
        card_tv = (TextView) findViewById(R.id.card_tv);
        phone_tv = (TextView) findViewById(R.id.phone_tv);
        email_tv = (TextView) findViewById(R.id.email_tv);
        txt_tv = (TextView) findViewById(R.id.txt_tv);
        msg_tv = (TextView) findViewById(R.id.msg_tv);
        wifi_tv = (TextView) findViewById(R.id.wifi_tv);
        careBtn.setOnClickListener(this);
        net_tv.setOnClickListener(this);
        project_tv.setOnClickListener(this);
        card_tv.setOnClickListener(this);
        phone_tv.setOnClickListener(this);
        email_tv.setOnClickListener(this);
        txt_tv.setOnClickListener(this);
        msg_tv.setOnClickListener(this);
        wifi_tv.setOnClickListener(this);
        okBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.careBtn:
                break;
            case R.id.okBtn:
                Log.d("xyc", "onClick: ="+"1");
                if(isInitNet){
                    String url_data = net_content.getText().toString().trim();
                    if (url_data.length() != 0) {
                        String netDatas = getNetDatas(url_data);
                        if (netDatas != null) {
                            Log.d("xyc", "onClick: ="+"2");
                            Picasso.with(mContext).load(UrlUtils.img_header + netDatas).into(qrCode);
                        }
                    }
                }
                break;
            case R.id.net_tv:
                initNetDatas();
                break;
            case R.id.project_tv:
                break;
            case R.id.card_tv:
                break;
            case R.id.phone_tv:
                break;
            case R.id.email_tv:
                break;
            case R.id.txt_tv:
                break;
            case R.id.msg_tv:
                break;
            case R.id.wifi_tv:
                break;
        }
    }

    private void initNetDatas() {
        LinearLayout diff_layout = (LinearLayout) findViewById(R.id.diff_layout);
        View net_view = LayoutInflater.from(mContext).inflate(R.layout.net_layout, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        net_content = (EditText) net_view.findViewById(R.id.net_content);
        net_view.setLayoutParams(params);
        diff_layout.addView(net_view);
        isInitNet=true;
    }

    @Override
    public void onBackPressed() {
        // 如果有需要，可以点击后退关闭插播广告。
        if (!SpotManager.getInstance(MainActivity.this).disMiss()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        SpotManager.getInstance(MainActivity.this).onDestroy();
        super.onDestroy();
    }


}
