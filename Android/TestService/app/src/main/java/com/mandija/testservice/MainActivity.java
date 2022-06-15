package com.mandija.testservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mandija.testservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("PPLNET", "onReceive Frase: " + intent.getStringExtra("frase"));
        }
    };

    private ActivityMainBinding binding;
    Intent servIntent = new Intent(this, RingService.class);
    Intent nameIntent = new Intent(this, NameService.class);
    Intent netIntent = new Intent(this, NetworkService.class);
    NameService nameService;
    Boolean serviceConnected = false;

    ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            nameService = ((NameService.NameServiceBinder) iBinder).getNameService();
            nameService.getName();
            serviceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            nameService = null;
            serviceConnected = false;
        }
    };

    IntentFilter fraseFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fraseFilter = new IntentFilter(NetworkService.FRASE_RECEIVED);

        binding.btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(netIntent);
            }
        });

        binding.btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(netIntent);
            }
        });

        /*binding.btRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService()
            }
        });*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(nameIntent, sc, BIND_AUTO_CREATE);
        registerReceiver(br, fraseFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(sc);
        unregisterReceiver(br);
    }
}