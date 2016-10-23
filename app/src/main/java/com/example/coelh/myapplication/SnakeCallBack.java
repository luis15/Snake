package com.example.coelh.myapplication;

import android.view.SurfaceHolder;

/**
 * Created by coelh on 19/10/2016.
 */

public class SnakeCallBack implements SurfaceHolder.Callback {

    private SnakeThread thread;

    public SnakeThread getThread(){
        return thread;
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        thread.setRunning(false);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new SnakeThread(holder);
        thread.start();
    }
}
