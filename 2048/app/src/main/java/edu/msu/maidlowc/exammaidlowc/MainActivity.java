package edu.msu.maidlowc.exammaidlowc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private GestureDetectorCompat gestureDetector;

    private static final int DISTANCE = 120;
    private static final int VELOCITY = 150;
    private String DIRECTION = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the gesture detector for us and we are the listener
        gestureDetector = new GestureDetectorCompat(this, this);
        // Set the gesture detector as the double tap listener.
        gestureDetector.setOnDoubleTapListener(this);


    }

    public GameView getGameView(){
        return (GameView) findViewById(R.id.gameView);
    }

    /**
     * Handle the new button
     * @param view Button view
     */
    public void onNew(View view) {

        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
       // add("onDown");
        return true;
    }



    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        if(event1.getX() - event2.getX() > DISTANCE && Math.abs(velocityX) > VELOCITY) {

            DIRECTION = "LEFT";
            GameView gv = getGameView();
            gv.move(DIRECTION);
            int score = gv.getBoard().getScore();
            String points = Integer.toString(score);
            ((TextView)findViewById(R.id.textNumber)).setText(points);
            return true;

        }  else if (event2.getX() - event1.getX() > DISTANCE && Math.abs(velocityX) > VELOCITY) {

            DIRECTION = "RIGHT";
            GameView gv = getGameView();
            gv.move(DIRECTION);
            int score = gv.getBoard().getScore();
            String points = Integer.toString(score);
            ((TextView)findViewById(R.id.textNumber)).setText(points);
            return true;
        }

        if(event1.getY() - event2.getY() > DISTANCE && Math.abs(velocityY) > VELOCITY) {

            DIRECTION = "UP";
            GameView gv = getGameView();
            gv.move(DIRECTION);
            int score = gv.getBoard().getScore();
            String points = Integer.toString(score);
            ((TextView)findViewById(R.id.textNumber)).setText(points);
            return true;

        }  else if (event2.getY() - event1.getY() > DISTANCE && Math.abs(velocityY) > VELOCITY) {

            DIRECTION = "DOWN";
            GameView gv = getGameView();
            gv.move(DIRECTION);
            int score = gv.getBoard().getScore();
            String points = Integer.toString(score);
            ((TextView)findViewById(R.id.textNumber)).setText(points);
            return true;
        }
        return false;
    }
    @Override
    public void onLongPress(MotionEvent event) {
       // add("onLongPress");

    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }
    @Override
    public void onShowPress(MotionEvent event) {
       // add("onShowPress");
    }
    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        //add("onSingleTapUp");
        return true;
    }
    @Override
    public boolean onDoubleTap(MotionEvent event) {
        //add("onDoubleTap");
        return true;
    }
    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        //add("onDoubleTapEvent");
        return true;
    }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        //add("onSingleTapConfirmed");
        return true;
    }

}
