package com.chandnadhruv.lab09;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Sprite extends RectF {
    private int dX, dY, color;
    public Sprite(float left, float top, float right, float bottom, int dX, int dY, int color) {
        super(left, top, right, bottom);
        this.dX = dX;
        this.dY = dY;
        this.color = color;
    }
    public Sprite(float left, float top, float right, float bottom, int dY) {
        this(left, top, right, bottom, 0, dY, Color.WHITE);
    }

    public Sprite(float left, float top, float right, float bottom, int dX, int dY) {
        this(left, top, right, bottom, dX, dY, Color.WHITE);
    }

    public void update(int dir) {
        if (dir<0) {
            offset(dX, -dY);
        }
        else if (dir>0) {
            offset(dX, dY);
        }
        else {
            offset(dX, dY);
        }
    }
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(this, paint);
    }
    public void setdX(int dX) {
        this.dX = dX;
    }
    public void setdY(int dY) {
        this.dY = dY;
    }
    public int getdX() {
        return dX;
    }
    public int getdY() {
        return dY;
    }

    public float getTop() {
        return top;
    }
    public float getBottom() {
        return bottom;
    }
    public float getLeft() { return left; }
    public float getRight() { return right; }
}
