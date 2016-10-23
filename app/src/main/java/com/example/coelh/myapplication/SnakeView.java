package com.example.coelh.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by coelh on 19/10/2016.
 */

public class SnakeView extends SurfaceView{

    private SnakeCallBack callBack;

    public SnakeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        callBack = new SnakeCallBack();
        getHolder().addCallback(callBack);
    }
}
