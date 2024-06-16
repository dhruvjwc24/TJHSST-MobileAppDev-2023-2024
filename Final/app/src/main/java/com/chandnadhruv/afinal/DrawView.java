package com.chandnadhruv.afinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DrawView extends View {
    Sprite leftPaddle, rightPaddle, circle, deadCircle;
    Paint p = new Paint();
    int borderThickness = 25;
    int playerWidth = 50;
    int playerHeight = 200;
    int offset = 100;
    int leftBorder, rightBorder, topBorder, bottomBorder, width, height, gameHeight, gameWidth;
//    int dP = -5;
    float dP = 0;
    int up = 1;
    int down = -1;
    int ballLength = 50;
    float balldY = 0;
    float balldX = 0;
    boolean isML, isMR;
    boolean rightPaddleTouched = false;
    boolean leftPaddleTouched = false;
    int lDir = 0;
    int rDir = 0;
    int lScore = 0;
    int rScore = 0;
    boolean gameStarted = false;
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
        leftPaddle.draw(canvas);
        rightPaddle.draw(canvas);
        circle.draw(canvas);
        float[] dashPattern = {10, 50};
        p.setPathEffect(new android.graphics.DashPathEffect(dashPattern, 0));
        canvas.drawLine(offset+playerWidth/2-borderThickness/2, topBorder, offset+playerWidth/2-borderThickness/2, bottomBorder, p);
        canvas.drawLine(width-offset-playerWidth/2+borderThickness/2, topBorder, width-offset-playerWidth/2+borderThickness/2, bottomBorder, p);
        p.setPathEffect(null);
        // CHeck if any touch action and update respective paddle:
//        leftPaddle.update(up);

//        if (isML) {
//            leftPaddle.update(up);
//        }
//
//        if (isMR) {
//            rightPaddle.update(up);
//        }

//        rightPaddle.update(up);
//        leftPaddle.update(down);
//        deadCircle.update();
        circle.update();
        leftPaddle.update();
        rightPaddle.update();
        if (RectF.intersects(circle, rightPaddle) | RectF.intersects(circle, leftPaddle)) {
            circle.setdX(-circle.getdX());
//            circle.setdY(-circle.getdY());
        }
        else if (circle.getBottom() >= bottomBorder || circle.getTop() <= topBorder) {
            circle.setdY(-circle.getdY());
        }
        else if (circle.getLeft() < offset+playerWidth/2) {
            rScore++;
            System.out.println(rScore);
            gameStarted = false;
//            circle = new Sprite(width / 2 - ballLength / 2, height / 2 - ballLength / 2, width / 2 + ballLength / 2, height / 2 + ballLength / 2, balldX, balldY);
//            p.setColor(0xffff0000);
//            deadCircle = new Sprite(circle.getLeft(), circle.getTop(), circle.getLeft(), circle.getBottom(), balldX, balldY, Color.RED);
            if (circle.getRight() <= 0) {
                balldX = 0;
                balldY = 0;
                circle = new Sprite(width / 2 - ballLength / 2, height / 2 - ballLength / 2, width / 2 + ballLength / 2, height / 2 + ballLength / 2, balldX, balldY);
                leftPaddle = new Sprite(offset-playerWidth/2, height/2-playerHeight/2, offset+playerWidth/2, height/2+playerHeight/2, dP);
                rightPaddle = new Sprite(width-offset-playerWidth/2, height/2-playerHeight/2, width-offset+playerWidth/2, height/2+playerHeight/2, dP);
            }
        }
        else if (circle.getRight() > width-offset-playerWidth/2) {
            lScore++;
            System.out.println(lScore);
            gameStarted = false;
//            deadCircle = new Sprite(circle.getLeft(), circle.getTop(), circle.getLeft(), circle.getBottom(), balldX, balldY, Color.RED);
//            circle = new Sprite(width / 2 - ballLength / 2, height / 2 - ballLength / 2, width / 2 + ballLength / 2, height / 2 + ballLength / 2, balldX, balldY);
            if (circle.getLeft() >= getWidth()) {
                balldX = 0;
                balldY = 0;
                circle = new Sprite(width / 2 - ballLength / 2, height / 2 - ballLength / 2, width / 2 + ballLength / 2, height / 2 + ballLength / 2, balldX, balldY);
                leftPaddle = new Sprite(offset-playerWidth/2, height/2-playerHeight/2, offset+playerWidth/2, height/2+playerHeight/2, dP);
                rightPaddle = new Sprite(width-offset-playerWidth/2, height/2-playerHeight/2, width-offset+playerWidth/2, height/2+playerHeight/2, dP);
            }
        }

        invalidate();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        System.out.println(event);
//        int numPointers = event.getPointerCount();
//        for (int pointerID = 0; pointerID < numPointers; pointerID++) {
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                if (rightPaddle.contains(event.getX(pointerID), event.getY(pointerID))) {
//                    System.out.println("In here");
//                    rightPaddleTouched = true;
//                    System.out.println("" + rightPaddleTouched);
//                    //                isMR = true;
//                    //               rightPaddle.offsetTo(rightPaddle.lef1t,event.getY()-rightPaddle.height()/2);
//                }
//
//                if (leftPaddle.contains(event.getX(pointerID), event.getY(pointerID))) {
//                    System.out.println("In here2");
//                    leftPaddleTouched = true;
//                    System.out.println("" + leftPaddleTouched);
//                    //                isML = true;
//                    //               rightPaddle.offsetTo(rightPaddle.left,event.getY()-rightPaddle.height()/2);
//                }
//
////                if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                if(!gameStarted) {
//                    balldX = 5;
//                    balldY = -5;
//                    circle.setdX(balldX);
//                    circle.setdY(balldY);
//                    gameStarted = true;
//                }
//                float dist;
//                if (leftPaddleTouched) {
//                    isML=true;
//                    dist = event.getY()-(leftPaddle.getCenterY());
//                    if (dist < 0) {lDir=-1;}
//                    else {lDir=1;}
//                    leftPaddle.setdY(dist/10.0f);
////                    System.out.println("Left moved" + isMR);
//                }
//
//                if (rightPaddleTouched) {
//                    isMR=true;
//                    dist = event.getY()-(rightPaddle.getCenterY());
//                    if (dist < 0) {rDir=-1;}
//                    else {rDir=1;}
//                    rightPaddle.setdY(dist/10.0f);
////                    System.out.println("Left moved" + isMR);
//                }
////                }
//                if (leftPaddleTouched && event.getY(pointerID) < leftPaddle.getCenterY() && lDir > 0 || event.getY(pointerID) < leftPaddle.getCenterY() && lDir < 0) {
//                    leftPaddle.setdY(0);
//                }
//                if (rightPaddleTouched && event.getY(pointerID) < rightPaddle.getCenterY() && rDir > 0 || event.getY(pointerID) > rightPaddle.getCenterY() && rDir < 0) {
//                    rightPaddle.setdY(0);
//                }
//            }
//        }
//
//
////        if (event.getAction() == MotionEvent.ACTION_DOWN) {
////            //did i touch the paddle
////
////
////        }
//            if (event.getAction() == MotionEvent.ACTION_MOVE) {
//
//                if(!gameStarted) {
//                    balldX = 5;
//                    balldY = -5;
//                    circle.setdX(balldX);
//                    circle.setdY(balldY);
//                    gameStarted = true;
//                }
//
//                float dist, newDY;
//
////                if (leftPaddleTouched) {
////                    isML=true;
////                    dist = event.getY()-(leftPaddle.getCenterY());
////                    if (lDir == 0) {
////                        if (dist < 0) {
////                            lDir = -1;
////                        } else {
////                            lDir = 1;
////                        }
////                    }
////                    else{leftPaddle.setdY(dist/10.0f);}
//////                    else if((dist<0 && lDir<0) || (dist>0 && lDir>0)) {
//////                            leftPaddle.setdY(dist/10.0f);
//////                    }
//////                    else {
//////                        leftPaddle.setdY(0);
//////                        isML = false;
//////                    }
//////                    System.out.println("Left moved" + isML);
////                }
//                if (leftPaddleTouched) {
//                    isML=true;
//                    dist = event.getY()-(leftPaddle.getCenterY());
//                    if (dist < 0) {lDir=-1;}
//                    else {lDir=1;}
//                    leftPaddle.setdY(dist/10.0f);
////                    System.out.println("Left moved" + isMR);
//                }
//
//                if (rightPaddleTouched) {
//                    isMR=true;
//                    dist = event.getY()-(rightPaddle.getCenterY());
//                    if (dist < 0) {rDir=-1;}
//                    else {rDir=1;}
//                    rightPaddle.setdY(dist/10.0f);
////                    System.out.println("Left moved" + isMR);
//                }
//            }
//
//            if (leftPaddleTouched && event.getY(pointerID) < leftPaddle.getCenterY() && lDir > 0 || event.getY(pointerID) < leftPaddle.getCenterY() && lDir < 0) {
//                leftPaddle.setdY(0);
//            }
//            if (rightPaddleTouched && event.getY(pointerID) < rightPaddle.getCenterY() && rDir > 0 || event.getY(pointerID) > rightPaddle.getCenterY() && rDir < 0) {
//                rightPaddle.setdY(0);
//            }
//
//            if(event.getAction() == MotionEvent.ACTION_UP) {
//                isML = false;
//                isMR = false;
//                leftPaddleTouched = false;
//                rightPaddleTouched = false;
//                leftPaddle.setdY(0);
//                rightPaddle.setdY(0);
//            }
//
//        return true;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //did i touch the paddle

            if (rightPaddle.contains(event.getX(), event.getY())) {
                System.out.println("In here");
                rightPaddleTouched = true;
                System.out.println("" + rightPaddleTouched);
//                isMR = true;
//               rightPaddle.offsetTo(rightPaddle.lef1t,event.getY()-rightPaddle.height()/2);
            }

            if (leftPaddle.contains(event.getX(), event.getY())) {
                System.out.println("In here2");
                leftPaddleTouched = true;
                System.out.println("" + leftPaddleTouched);
//                isML = true;
//               rightPaddle.offsetTo(rightPaddle.left,event.getY()-rightPaddle.height()/2);
            }
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {

            if(!gameStarted) {
                balldX = 5;
                balldY = -5;
                circle.setdX(balldX);
                circle.setdY(balldY);
                gameStarted = true;
            }

            float dist, newDY;

//                if (leftPaddleTouched) {
//                    isML=true;
//                    dist = event.getY()-(leftPaddle.getCenterY());
//                    if (lDir == 0) {
//                        if (dist < 0) {
//                            lDir = -1;
//                        } else {
//                            lDir = 1;
//                        }
//                    }
//                    else{leftPaddle.setdY(dist/10.0f);}
////                    else if((dist<0 && lDir<0) || (dist>0 && lDir>0)) {
////                            leftPaddle.setdY(dist/10.0f);
////                    }
////                    else {
////                        leftPaddle.setdY(0);
////                        isML = false;
////                    }
////                    System.out.println("Left moved" + isML);
//                }
            if (leftPaddleTouched) {
                isML=true;
                dist = event.getY()-(leftPaddle.getCenterY());
                if (dist < 0) {lDir=-1;}
                else {lDir=1;}
                leftPaddle.setdY(dist/10.0f);
//                    System.out.println("Left moved" + isMR);
            }

            if (rightPaddleTouched) {
                isMR=true;
                dist = event.getY()-(rightPaddle.getCenterY());
                if (dist < 0) {rDir=-1;}
                else {rDir=1;}
                rightPaddle.setdY(dist/10.0f);
//                    System.out.println("Left moved" + isMR);
            }
        }

        if (event.getY() < leftPaddle.getCenterY() && lDir > 0) {
            leftPaddle.setdY(0);
        }
        if (event.getY() < rightPaddle.getCenterY() && rDir > 0) {
            rightPaddle.setdY(0);
        }

        if(event.getAction() == MotionEvent.ACTION_UP) {
            isML = false;
            isMR = false;
            leftPaddleTouched = false;
            rightPaddleTouched = false;
            leftPaddle.setdY(0);
            rightPaddle.setdY(0);
        }

        return true;
    }
}
