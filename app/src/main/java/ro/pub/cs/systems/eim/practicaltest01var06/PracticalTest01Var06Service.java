package ro.pub.cs.systems.eim.practicaltest01var06;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Random;

public class PracticalTest01Var06Service extends Service {
    public PracticalTest01Var06Service() {
    }

    ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Random rand = new Random();
        int r = rand.nextInt();
        processingThread = new ProcessingThread(this, r);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }

}
