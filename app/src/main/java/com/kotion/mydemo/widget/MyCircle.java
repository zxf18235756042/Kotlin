package com.kotion.mydemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyCircle extends View {

    private Paint bgPaint;
    private Paint paint;
    private Paint cirPaint;

    private int _x = 50,_y=400;

    public MyCircle(Context context) {
        super(context);
        initPaint();
    }

    public MyCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    private void initPaint(){

        bgPaint = new Paint();
        bgPaint.setColor(Color.BLUE);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);

        cirPaint = new Paint();
        cirPaint.setColor(Color.WHITE);
        cirPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 测量 组件的宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 布局 计算组件的位置 坐标
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 绘制
     * @param canvas  纸
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        rect.left = 0;
        rect.top = 0;
        rect.right = 1000;
        rect.bottom = 2000;

        canvas.drawRect(rect,bgPaint);
        canvas.drawCircle(50,50,50,paint);

        RectF rectF = new RectF();
        rectF.left = 0;
        rectF.top = 100;
        rectF.right = 200;
        rectF.bottom = 200;
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,0,200,false,paint);


        //分段  绘制
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        Rect rect1 = new Rect();
        rect1.left = _x;
        rect1.top = _y;
        rect1.right = _x+150;
        rect1.bottom = _y+50;
        canvas.drawRect(rect1,paint);


        paint.setColor(Color.GRAY);
        Rect rect2= new Rect();
        rect2.left = rect1.right;
        rect2.top = _y;
        rect2.bottom = _y+50;
        rect2.right = rect1.right+200;
        canvas.drawRect(rect2,paint);

        paint.setColor(Color.GREEN);
        Rect rect3= new Rect();
        rect3.left = rect2.right;
        rect3.top = _y;
        rect3.right = rect2.right+350;
        rect3.bottom = _y+50;
        canvas.drawRect(rect3,paint);

        canvas.drawCircle(rect1.left,_y+25,25,cirPaint);
        canvas.drawCircle(rect2.left,_y+25,25,cirPaint);
        canvas.drawCircle(rect3.left,_y+25,25,cirPaint);
        canvas.drawCircle(rect3.right,_y+25,25,cirPaint);
    }
}
