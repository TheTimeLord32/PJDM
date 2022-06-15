package com.mandija.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NetworkService extends Service {
     public static final String FRASE_RECEIVED = "FRASE_RECEIVED";

    public NetworkService() {
    }

    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("PPLSERV", "onStartCommand() called with: intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    try {
                        Log.d("PPLNET", "run() called" + Thread.currentThread().getName());
                        URL url = new URL("http://pjdm.netgroup.uniroma2.it/test/gen.php");
                        BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                        String line = br.readLine();
                        String frase = new JSONObject(line).getString("frase");
                        Intent fraseIntent = new Intent(FRASE_RECEIVED);
                        fraseIntent.putExtra("frase", frase);
                        sendBroadcast(fraseIntent);

                        Log.d("PPLSERV", "run: "+ frase);

                        Thread.sleep(10000);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}