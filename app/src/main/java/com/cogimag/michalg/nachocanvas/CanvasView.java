package com.cogimag.michalg.nachocanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.graphics.Path;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by MichalG HP Envy on 2/8/2018.
 * Idea for extending View class from https://www.javatpoint.com/andriod-simple-graphics-example
 *
 */

public class CanvasView extends View {
    private float xStart, yStart;
    static Paint paint;
    Path path;
    Boolean clear;
    public CanvasView(Context ctxt, AttributeSet attrs) {
        super(ctxt, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        path = new Path();
        clear = true;
    }

    @Override
    protected void onDraw(Canvas cnv) {
        if (clear) {
            path.reset();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            cnv.drawPaint(paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            paint.setColor(Color.BLACK);
            clear = false;
        }
        else  {
            super.onDraw(cnv);
            cnv.drawPath(path,paint);
        }
    }

    protected boolean handleTouch(MotionEvent event) {
//        Log.i("canvas view", "handling touch event");
        float xCurrent = event.getX();
        float yCurrent = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xCurrent,yCurrent);
                xStart = xCurrent;
                yStart = yCurrent;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.i("handle touch", "action move");
                path.lineTo(xCurrent,yCurrent);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                path.lineTo(xCurrent,yCurrent);
//                invalidate();
                break;
        }
        return true;
    }
    protected void clearCanvas() {
        clear = true;
        invalidate();
    }
    protected static void changeColor(String color) {
        paint.setStrokeWidth(5);
        switch (color) {
            case "Red":
                paint.setColor(Color.RED);
                break;
            case "Orange":
                paint.setColor(Color.rgb(255,165, 0));
                break;
            case "Yellow":
                paint.setColor(Color.YELLOW);
                break;
            case "Green":
                paint.setColor(Color.GREEN);
                break;
            case "Blue":
                paint.setColor(Color.BLUE);
                break;
            case "Black":
                paint.setColor(Color.BLACK);
                break;
            case "Eraser":
                paint.setColor(Color.WHITE);
                paint.setStrokeWidth(15);
                break;
        }
    }

}
