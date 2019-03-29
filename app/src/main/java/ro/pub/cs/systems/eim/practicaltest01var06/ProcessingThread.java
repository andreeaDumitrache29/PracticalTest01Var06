package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {

    private int number;
    private Context context = null;
    private boolean isRunning = true;

    public ProcessingThread(Context context, int number) {
        this.context = context;
        this.number = number;
    }

    @Override
    public void run() {
        Log.d("Tag", "Thread has started!: ");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, number);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
