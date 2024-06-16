package com.chandnadhruv.lab09;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite leftPaddle, rightPaddle, circle;
    Paint p = new Paint();
    int borderThickness = 25;
    int playerWidth = 50;
    int playerHeight = 200;
    int offset = 100;
    int leftBorder, rightBorder, topBorder, bottomBorder, width, height, gameHeight, gameWidth;
    int dP = -5;
    int up = 1;
    int down = -1;
    int ballLength = 50;
    int balldY = 5;
    int balldX = -5;
    boolean isML, isMR;
    boolean rightPaddleTouched = false;
    boolean leftPaddleTouched = false;
//    int leftx1, lefty1, leftx2, lefty2, rightx1, righty1, rightx2, righty2;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        leftBorder = borderThickness;
        rightBorder = width-borderThickness;
        topBorder = borderThickness;
        bottomBorder = height-borderThickness;
        gameHeight = bottomBorder-topBorder;
        gameWidth = rightBorder-leftBorder;
        leftPaddle = new Sprite(offset-playerWidth/2, height/2-playerHeight/2, offset+playerWidth/2, height/2+playerHeight/2, dP);
        rightPaddle = new Sprite(width-offset-playerWidth/2, height/2-playerHeight/2, width-offset+playerWidth/2, height/2+playerHeight/2, dP);
        circle = new Sprite(width/2-ballLength/2, height/2-ballLength/2, width/2+ballLength/2, height/2+ballLength/2, balldX, balldY);
        isML = false;
        isMR = false;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(0xff000000);
        p.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, getWidth(), getHeight(), p);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(borderThickness);
        p.setColor(0xffffffff);
        canvas.drawRect(0, 0, getWidth(), getHeight(), p);

        // CHeck if any touch action and update respective paddle:
        leftPaddle.draw(canvas);
        rightPaddle.draw(canvas);
        circle.draw(canvas);
//        leftPaddle.update(up);

//        if (isML) {
//            leftPaddle.update(up);
//        }
//
//        if (isMR) {
//            rightPaddle.update(up);
//        }

        rightPaddle.update(up);
        leftPaddle.update(down);
        circle.update(0);

        if (RectF.intersects(circle, rightPaddle) | RectF.intersects(circle, leftPaddle)) {
            circle.setdX(-circle.getdX());
            circle.setdY(-circle.getdY());
        }

        invalidate();

//        p.setStyle(Paint.Style.FILL);
//        p.setColor(0xffffffff);
//        canvas.drawRect((offset-playerWidth/2), (height/2-playerHeight/2), (offset+playerWidth/2), (height/2+playerHeight/2), p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //did i touch the paddle
            int dir = 0;


            if (rightPaddle.contains(event.getX(), event.getY())) {
                System.out.println("In here");
                rightPaddleTouched = true;
                System.out.println(""+rightPaddleTouched);
//                isMR = true;
//               rightPaddle.offsetTo(rightPaddle.lef1t,event.getY()-rightPaddle.height()/2);
            }

            if (leftPaddle.contains(event.getX(), event.getY())) {
                System.out.println("In here2");
                leftPaddleTouched = true;
                System.out.println(""+leftPaddleTouched);
//                isML = true;
//               rightPaddle.offsetTo(rightPaddle.left,event.getY()-rightPaddle.height()/2);
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (leftPaddleTouched) {
                    isML=true;
                    System.out.println("Left moved" + isML);
                }
                if (rightPaddleTouched) {
                    isMR=true;
                    System.out.println("Left moved" + isMR);
                }

//                if (event.getY() < leftPaddle.getTop()+playerHeight) {
//                    leftPaddle.update(up);
//                }
//                else if (event.getY() > leftPaddle.getTop()+playerHeight) {
//                    leftPaddle.update(down);
//                }
            }

            if(event.getAction() == MotionEvent.ACTION_POINTER_UP) {
                isML = false;
                isMR = false;
            }
        }
       return true;
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int index = event.getActionIndex();
//        int pointerId = event.getPointerId(index);
//        int action = event.getActionMasked();
//        int rightPaddlePointerId = -1;
//        int leftPaddlePointerId = -1;
//
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_POINTER_DOWN:
//                // Check if either paddle is touched
//                if (rightPaddle.contains(event.getX(index), event.getY(index))) {
//                    // Assign this touch to the right paddle using pointerId
//                    rightPaddlePointerId = pointerId;
//                }
//
//                if (leftPaddle.contains(event.getX(index), event.getY(index))) {
//                    // Assign this touch to the left paddle using pointerId
//                    leftPaddlePointerId = pointerId;
//                }
////                break;
//
//            case MotionEvent.ACTION_MOVE:
//                // Move the right paddle if its corresponding touch moved
//                if (rightPaddlePointerId != -1) {
//                    int rightIndex = event.findPointerIndex(rightPaddlePointerId);
//                    if (rightIndex != -1) {
//                        rightPaddle.offsetTo(rightPaddle.left, event.getY(rightIndex) - rightPaddle.height() / 2);
//                    }
//                }
//
//                // Move the left paddle if its corresponding touch moved
//                if (leftPaddlePointerId != -1) {
//                    int leftIndex = event.findPointerIndex(leftPaddlePointerId);
//                    if (leftIndex != -1) {
//                        leftPaddle.offsetTo(leftPaddle.left, event.getY(leftIndex) - leftPaddle.height() / 2);
//                    }
//                }
//                invalidate(); // Redraw the view
//                break;
//
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_POINTER_UP:
//            case MotionEvent.ACTION_CANCEL:
//                // Unassign the touch from the paddle
//                if (pointerId == rightPaddlePointerId) {
//                    rightPaddlePointerId = -1;
//                }
//                if (pointerId == leftPaddlePointerId) {
//                    leftPaddlePointerId = -1;
//                }
//                break;
//        }
//        return true;
//    }
}
