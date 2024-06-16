package com.chandnadhruv.afinal;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Sprite extends RectF {
    private int color;
    private float dX, dY;
    public Sprite(float left, float top, float right, float bottom, float dX, float dY, int color) {
        super(left, top, right, bottom);
        this.dX = dX;
        this.dY = dY;
        this.color = color;
    }
    public Sprite(float left, float top, float right, float bottom, float dY) {
        this(left, top, right, bottom, 0, dY, Color.WHITE);
    }

    public Sprite(float left, float top, float right, float bottom, float dX, float dY) {
        this(left, top, right, bottom, dX, dY, Color.WHITE);
    }

    public void update() {
        offset(dX, dY);
    }
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(this, paint);
    }

    public float getCenterY() {
        return (getTop()+getBottom())/2.0f;
    }
    public float getCenterX() {
        return (getRight()+getLeft())/2.0f;
    }
    public void setdX(float dX) {
        this.dX = dX;
    }
    public void setdY(float dY) {
        this.dY = dY;
    }
    public float getdX() {
        return dX;
    }
    public float getdY() {
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
