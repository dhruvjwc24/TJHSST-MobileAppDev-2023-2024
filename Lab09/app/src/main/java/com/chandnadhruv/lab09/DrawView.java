package com.chandnadhruv.lab09;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DrawView extends View {

    Paint p = new Paint();
    int borderThickness = 25;
    int leftBorder, rightBorder, topBorder, bottomBorder

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        leftBorder = borderThickness;
        rightBorder = getWidth()-borderThickness;
        topBorder = borderThickness;
        bottomBorder = getHeight()-borderThickness;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(0xff000000);
        canvas.drawRect(0, 0, getWidth(), getHeight(), p);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(borderThickness);
        p.setColor(0xffffffff);
        canvas.drawRect(0, 0, getWidth(), getHeight(), p);


    }
}
