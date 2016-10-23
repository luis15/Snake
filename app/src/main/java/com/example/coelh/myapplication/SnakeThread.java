package com.example.coelh.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by coelh on 19/10/2016.
 */

public class SnakeThread extends Thread{
    private SurfaceHolder holder;
    private Snake snake;
    private boolean threadIsRunning = true;

    private Paint backgroundPaint;
    private Paint snakePaint;
    private Paint foodPaint;
    private Paint board;

    private int radius =70;

    public SnakeThread(SurfaceHolder holder){
        this.holder =holder;
        snake = new Snake();


        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);
        snakePaint = new Paint();
        snakePaint.setColor(Color.BLACK);
        foodPaint = new Paint();
        foodPaint.setColor(Color.BLUE);
        board = new Paint();
        board.setColor(Color.RED);



    }
    @Override
    public void run(){
        Canvas canvas = null;
        while(threadIsRunning){
            snake.move();
            canvas = holder.lockCanvas();
            try{
                synchronized (holder){
                    drawGamesElements(canvas);
                }
                Thread.sleep(300);
            }
            catch (InterruptedException e){

            }
            finally {
                if (canvas!=null){
                    holder.unlockCanvasAndPost(canvas);
                }
            }

        }

    }
    public void drawGamesElements(Canvas canvas){
        int dimX = canvas.getWidth()/(2*radius);
        int dimY = canvas.getHeight()/(2*radius);

        snake.setDimesion(dimX, dimY);

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);

        for(int i = 0; i < dimX; i++){
            for(int j = 0; j < dimY; j++){
                canvas.drawCircle(2*radius*(i%dimX)+radius,
                        2*radius*(j%dimY)+radius,
                        radius,
                        board);
            }
        }


        Snake.SnakeNode node = snake.getTail();

        while(node != null){
            canvas.drawCircle(2*radius*(node.x%dimX)+radius,
                    2*radius*(node.y%dimY)+radius,
                    radius,
                    snakePaint
                    );
            node = node.getPrevious();
        }

        node = snake.getFood();
        canvas.drawCircle(2*radius*(node.x%dimX)+radius,
                2*radius*(node.y%dimY)+radius,
                radius,
                foodPaint
        );
    }
    public void setRunning(boolean running){
        threadIsRunning = running;
    }
}
