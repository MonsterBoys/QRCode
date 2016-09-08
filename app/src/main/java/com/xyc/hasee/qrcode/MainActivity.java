package com.xyc.hasee.qrcode;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import net.youmi.android.normal.spot.SpotManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext = MainActivity.this;
    private ImageView careBtn;
    private TextView net_tv, project_tv, okBtn, img_tv, card_tv, phone_tv, email_tv, txt_tv, msg_tv, file_tv, wifi_tv, location_tv, active_tv;
    private ImageView qrCode;
    private String retval;
    private EditText net_content;
    private boolean isInitNet = false;
    private Button text_input;
    private Button speech_input;
    private String s;
    private String net_content_str;
    private TextView input_content;
    private static final int RESULT_SPEECH = 1;
    private Button sureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YouMiUtils.initYoumi(mContext);
        initView();
    }

    public void getNetDatas(String s) {
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
        input_content = (TextView) findViewById(R.id.input_content);
        text_input = ((Button) findViewById(R.id.text_input));
        speech_input = ((Button) findViewById(R.id.speech_input));

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
        text_input.setOnClickListener(this);
        speech_input.setOnClickListener(this);

    }

    public void setNetDatas() {
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net_content_str = net_content.getText().toString();
                if(net_content_str!=null){
                    input_content.setText(net_content_str);
                    if(UrlUtils.isUrl(net_content_str)){
                        dissMissDialog();
                        getNetDatas(net_content_str);
                    }else {
                       Toast.makeText(mContext,"请输入合法的URL", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_input:
                View com_layout = showCenterDialog(mContext, "文字输入", R.layout.net_layout);
                net_content = (EditText) com_layout.findViewById(R.id.net_content);
                sureBtn = (Button) com_layout.findViewById(R.id.sureBtn);
                setNetDatas();
                break;
            case R.id.speech_input:
                speechOut();
                break;
            case R.id.careBtn:
                break;
            case R.id.okBtn:
                if (isInitNet) {
                    if (retval != null) {
                        Picasso.with(mContext).load(UrlUtils.img_header + retval).into(qrCode);
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

    private void speechOut() {
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() != 0) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "请说标准普通话");//注意不要硬编码
            //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);//通常情况下，第一个结果是最准确的。
            startActivityForResult(intent, RESULT_SPEECH);
        } else {
            showCenterDialog(mContext, "", R.layout.speech_layout);
        }
    }


    private void initNetDatas() {
        View net_view = LayoutInflater.from(mContext).inflate(R.layout.net_layout, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        net_content = (EditText) net_view.findViewById(R.id.net_content);
        net_view.setLayoutParams(params);
        isInitNet = true;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH:
                if (resultCode == RESULT_OK && data != null) {
                    List<String> list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    input_content.setText(list.get(0));
                }
                break;
        }
    }
}
