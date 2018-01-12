package github.mysample.broadcast;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null) {
            if (intent.getAction().equals("github.mysample.broadcast.SOME_ACTION")) {
                Toast.makeText(context, "SOME_ACTION is received", Toast.LENGTH_LONG).show();
            } else {
                ConnectivityManager connectivityManager = (ConnectivityManager)
                        context.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    try {
                        Toast.makeText(context, "N/w is connected", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "N/W is changed", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
