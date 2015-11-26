package com.twnel.deeplinklibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.twnel.deeplink.TwnelDeepLink;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button butOpenChat;
    private Button butOpenChatWithoutAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butOpenChat = (Button) findViewById(R.id.but_open_chat);
        butOpenChatWithoutAlert = (Button) findViewById(R.id.but_open_chat_without_dialog);
        butOpenChat.setOnClickListener(this);
        butOpenChatWithoutAlert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.but_open_chat){
            new TwnelDeepLink.Builder()
                    .context(this)
                    .companyId("twnelvipalerts")
                    .appPackageName("com.twnel.deeplinklibrary")
                    .activityClassName("com.twnel.deeplinklibrary.MainActivity")
                    .showDialog(true)
                    .dialogTitle("Install Twnel")
                    .dialogMessage("You can chat with us via Twnel 24/7")
                    .dialogNextButtonText("Next")
                    .build()
                    .navigate();
        }else {
            new TwnelDeepLink.Builder()
                    .context(this)
                    .companyId("twnelvipalerts")
                    .appPackageName("com.twnel.deeplinklibrary")
                    .activityClassName("com.twnel.deeplinklibrary.MainActivity")
                    .showDialog(false)
                    .build()
                    .navigate();
        }
    }
}
