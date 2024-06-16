package com.chandnadhruv.lab8a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DrawView extends View {

    Paint p = new Paint();
    int x=0, dx=5, dy=-5, count=80, y;
    double ddy=9.81;

    int w,h;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        h = getHeight();
        w = getWidth();
        y=(int)(getHeight()*.85f-50);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(0xFF3e3117);
        canvas.drawRect(0, 0.85f*h, w, h, p);
        p.setColor(0xffccff00);
//        y = -w/(2*(0.85f*h-50))*(x^2-x*w+50*(w-50));
//        canvas.drawCircle(50+x, 0.85f*h-50, 50, p);
        canvas.drawCircle(50+x, y, 50, p);
        if (count--==0) {
            dy+=ddy;
            count = 80;
        }

        if(y>=(int)(0.85f*h-50) && x!=0) {
            dy = 0;
            dx = 0;
        }
        else {
            y+=dy;
            x+=dx;
            invalidate();
        }

    }
}
