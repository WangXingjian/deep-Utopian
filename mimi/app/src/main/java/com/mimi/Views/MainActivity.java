package com.mimi.Views;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mimi.Network.BasicService;
import com.mimi.Network.CommonAPIHelper;
import com.mimi.Network.SingleAPIHepler;
import com.mimi.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SingleAPIHepler<String> helper = new SingleAPIHepler<String>(this) {
            @Override
            public void doNext(String s) {

            }
        };
        helper.excuteRxJava(helper.getService(BasicService.class).getBasicData());
    }
}
