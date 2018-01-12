package github.mysample.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import github.mysample.R;

public class BroadcastActivity extends Activity {
    BroadcastReceiver mBroadcastReceiver;
    IntentFilter mIntentFilter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        ButterKnife.bind(this);

        mBroadcastReceiver = new BroadcastReceiver();
        mIntentFilter = new IntentFilter("github.mysample.broadcast.SOME_ACTION");

        findViewById(R.id.broadcast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("github.mysample.broadcast.SOME_ACTION");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver, mIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }


    @OnClick(R.id.broadcast_button)
    void something() {
        Intent intent = new Intent("github.mysample.broadcast.SOME_ACTION");
        sendBroadcast(intent);
    }
}
