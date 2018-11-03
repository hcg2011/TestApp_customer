package com.yutou.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prize.RDSDK;
import com.prize.interfaces.RDInterface;
import com.yutou.jianr_mg.R;

public class MainActivity extends Activity {
    private TextView textView;
    private Button open,inter,banner;
    private LinearLayout mLayout;
    private RDSDK sdk;
    private Activity that;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        that=this;
        textView=findViewById(R.id.textView);
        open=findViewById(R.id.open);
        inter=findViewById(R.id.inter);
        banner=findViewById(R.id.banner);
        mLayout=findViewById(R.id.layout);
        //sdk=RDSDK.init(this,"test","1000002",new Handler());
        sdk=RDSDK.init(this,"test","1000002",new Handler());
        textView.setText("初始化成功，SDK版本："+sdk.getVersion());
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdk.showOpen(that, new RDInterface() {
                    @Override
                    public void onClose() {
                        super.onClose();
                    }
                });
            }
        });
        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdk.showInter(that, new RDInterface() {
                    @Override
                    public void onClose() {
                        super.onShow();
                    }
                });
            }
        });
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdk.showBer(that, new RDInterface() {
                    @Override
                    public void rdView(ViewGroup benner) {
                        super.rdView(benner);
                        mLayout.setVisibility(View.VISIBLE);
                        mLayout.addView(benner);
                    }
                });
            }
        });
    }
}
