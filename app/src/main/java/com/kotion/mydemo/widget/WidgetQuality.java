package com.kotion.mydemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.kotion.mydemo.R;
import com.kotion.mydemo.api.MyApp;


/**
 * 空气质量View效果
 */
public class WidgetQuality extends View {

    private int min = 0,max = 500,current = 0;

    private int x_yanzhong = 350,x_zhongdu = 250,x_middle = 200,x_qingdu=150,x_liang=100,x_good = 50;  //对应的线条的起始刻度

    private int offx=0; //x轴方向的偏移量

    private int offy=10; //y轴方向的偏移量

    private int stageW,stageH; //屏幕的宽高
    private int linew; //线条的总宽度
    private int lineh = 10; //线条高度

    private Paint tipPaint; //画笔

    private Paint linePaint; //白色的线条

    private Paint lineTxtPaint; //线条的最大值最小值




    public WidgetQuality(Context context) {
        super(context);
        initPaints();
    }

    public WidgetQuality(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public WidgetQuality(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public WidgetQuality(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaints();
    }


    private void initPaints(){
        tipPaint = new Paint();
        tipPaint.setAntiAlias(true);
        tipPaint.setColor(Color.BLACK);
        tipPaint.setStyle(Paint.Style.FILL);

        lineTxtPaint = new Paint();
        lineTxtPaint.setAntiAlias(true);
        lineTxtPaint.setColor(Color.WHITE);
        lineTxtPaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.FILL);
    }

    public void initScreenWH(int w,int h){
        this.stageW = w;
        this.stageH = h;
        linew = stageW-150*2; //用屏幕的宽度减去两边的间距
    }

    /**
     * 设置当前的值
     * @param value
     */
    public void setCurrentValue(int value){
        this.current = value;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(stageW == 0 || stageH == 0) return;
        drawTips(canvas);
        drawLine(canvas);
        drawName(canvas);
    }

    /**
     * 绘制提示信息
     * @param canvas
     */
    private void drawTips(Canvas canvas){
        Bitmap bg = BitmapFactory.decodeResource(MyApp.Companion.getApp().getResources(), R.mipmap.ic_tips_bg);
        //计算提示框的坐标

        float x_1 = 130+Float.valueOf(current*linew)/Float.valueOf(max)-Float.valueOf(bg.getWidth());
        //画背景框
        canvas.drawBitmap(bg,x_1,offy,tipPaint);
        //画当前的文本
        tipPaint.setTextSize(20);
        Rect rect = getStringWidth(tipPaint,String.valueOf(current));
        float x_2 = x_1 + (float) ((bg.getWidth()-rect.width())*0.5);
        float y_2 = offy+ (float)(bg.getHeight()*0.5);
        //画提示文字
        canvas.drawText(String.valueOf(current),x_2,y_2,tipPaint);
    }

    /**
     * 绘制中间的线条
     * @param canvas
     */
    private void drawLine(Canvas canvas){
        //绘制最小值
        lineTxtPaint.setTextSize(20);
        lineTxtPaint.setColor(Color.BLACK);
        float x_1 = 100;
        offy += 70;
        canvas.drawText(String.valueOf(min),x_1,offy+10,lineTxtPaint);

        //绘制线条1
        linePaint.setColor(Color.parseColor("#4CAF50"));
        Rect rect1 = new Rect();
        rect1.left = (int) (x_1+30);
        rect1.top = offy;
        rect1.right = rect1.left+(int) (linew*((float)(x_good)/(float)(max)));
        rect1.bottom = offy+lineh;
        canvas.drawRect(rect1,linePaint);



        //绘制线条2
        linePaint.setColor(Color.parseColor("#CDDC39"));
        Rect rect2 = new Rect();
        rect2.left = rect1.right;
        rect2.top = offy;
        rect2.right = rect2.left+(int) (linew*((float)(x_good)/(float)(max)));
        rect2.bottom = offy+lineh;
        canvas.drawRect(rect2,linePaint);

        //绘制线条3
        linePaint.setColor(Color.parseColor("#009688"));
        Rect rect3 = new Rect();
        rect3.left = rect2.right;
        rect3.top = offy;
        rect3.right = rect3.left + (int) (linew*((float)(x_good)/(float)(max)));
        rect3.bottom = offy+lineh;
        canvas.drawRect(rect3,linePaint);

        //绘制线条4
        linePaint.setColor(Color.parseColor("#9C27B0"));
        Rect rect4 = new Rect();
        rect4.left = rect3.right;
        rect4.top = offy;
        rect4.right = rect4.left + (int) (linew*((float)(x_good)/(float)(max)));
        rect4.bottom = offy+lineh;
        canvas.drawRect(rect4,linePaint);

        //绘制线条5
        linePaint.setColor(Color.parseColor("#E91E63"));
        Rect rect5 = new Rect();
        rect5.left = rect4.right;
        rect5.top = offy;
        rect5.right = rect5.left + (int) (linew*((float)(x_liang)/(float)(max)));
        rect5.bottom = offy+lineh;
        canvas.drawRect(rect5,linePaint);

        //绘制线条6
        linePaint.setColor(Color.parseColor("#FFBB86FC"));
        Rect rect6 = new Rect();
        rect6.left = rect5.right;
        rect6.top = offy;
        rect6.right = rect6.left + (int) (linew*((float)(150)/(float)(max)));
        rect6.bottom = offy+lineh;
        canvas.drawRect(rect6,linePaint);


        //绘制最小值
        float x_2 = rect6.right+10;
        canvas.drawText(String.valueOf(max),x_2,offy+10,lineTxtPaint);

        //画圆


    }

    /**
     * 绘制名称
     * @param canvas
     */
    private void drawName(Canvas canvas){

    }


    /**
     * 计算String的宽高
     * @param paint
     * @param content
     * @return
     */
    private Rect getStringWidth(Paint paint,String content){
        Rect rect = new Rect();
        paint.getTextBounds(content,0,content.length(),rect);
        return rect;
    }



}
