package com.aisiliu.viewpagerdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by aisi on 2017/8/23.
 */

public class WelcomeActivity extends AppCompatActivity {

    private boolean isFirst = true;

    private static  final int TIME = 2000;

    private static final int GO_MAIN = 1001;

    private static final int GO_GUIDE = 1002;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_MAIN:
                    toMainActivity();
                    break;
                case GO_GUIDE:
                    toGuideActivity();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.welcome);
        init();
    }

    private void toMainActivity(){
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void toGuideActivity(){
        Intent intent = new Intent(WelcomeActivity.this,GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private void init(){
        SharedPreferences preferences  = getSharedPreferences("aisi",MODE_PRIVATE);
        isFirst = preferences.getBoolean("isFirstCome",true);
        if (isFirst){
            handler.sendEmptyMessageDelayed(GO_GUIDE,TIME);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstCome",false);
            editor.commit();
        }else{
            handler.sendEmptyMessageDelayed(GO_MAIN,TIME);
        }
    }
}
