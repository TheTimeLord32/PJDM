package com.mandija.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class NameService extends Service {

    private String name = "Thomas";

    public NameService() {
    }

    public String getName() {
        return name;
    }

    private NameServiceBinder binder = new NameServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("PPLSERV", "onBind() called with: intent = [" + intent + "]");
        // TODO: Return the communication channel to the service.
        return binder;
    }

    class NameServiceBinder extends Binder {
        public NameService getNameService() {
            return NameService.this;
        }
    }
}