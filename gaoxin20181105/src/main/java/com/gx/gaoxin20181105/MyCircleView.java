package com.gx.gaoxin20181105;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class MyCircleView extends View implements View.OnClickListener {

    private Paint paint;
    private int screenheight,screenwidth;
    private int centerX;
    private int centerY;
    private Animation animation;
    private boolean flag;
    private MyCircleViewCilck myCircleViewCilck;
    //初始化文字
    String[] desc=new String[]{"参与奖","谢谢参与","一等奖","二等奖","三等奖","四等奖"};
    private RotateAnimation rotateAnimation;

    //重写三个方法
    public MyCircleView(Context context) {
        this(context,null);
    }

    public MyCircleView(Context context, AttributeSet attrs) {
        this(context,null,-1);
    }

    public MyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化画笔
        initPaint();
        //得到屏幕的信息 宽和高
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenheight = displayMetrics.heightPixels;
        screenwidth= displayMetrics.widthPixels;
        //分别除以二
        centerX = screenwidth/2;
        centerY = screenheight/2;
        //设置点击监听
        this.setOnClickListener(this);
    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置要显示view的大小
        setMeasuredDimension(screenwidth,screenheight);
    }
    //布局

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    //绘画
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动画布到我们的中心点
        canvas.translate(centerX,centerY);
        //之后画一个大圆
        canvas.drawCircle(0,0,300,paint);
        //分成6块，并对6块分区设置不同的颜色
        int[] colors=new int[]{Color.parseColor("#655442"),Color.MAGENTA,Color.BLUE,Color.CYAN,Color.LTGRAY,Color.YELLOW};
        //设置矩形
        RectF rectF=new RectF(-300,-300,300,300);
        int start=60;
        //绘制扇形
        for (int i=0;i<6;i++){
            //设置画笔的填充
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(1);
            paint.setColor(colors[i]);
            canvas.drawArc(rectF,i*start,start,true,paint);
        }
        //在中间画一个小圆
        paint.setColor(Color.RED);
        canvas.drawCircle(0,0,120,paint);
        //根据圆设置文字的路劲
        RectF rect=new RectF(-240,-240,240,240);
        for (int i=0;i<6;i++){
            paint.setColor(Color.BLACK);
            paint.setTextSize(30);
            Path path=new Path();
            path.addArc(rect,i*60+19,60);
            //绘制文字
            canvas.drawTextOnPath(desc[i],path,0,30,paint);
        }
        //对图所示的中心区域再绘制圆形内容，并赋予其点击事件，通过接口回调的形式提供给使用者进行使用；
        paint.setColor(Color.WHITE);
        canvas.drawText("Start",-30,10,paint);
        //设置点击事件 点击旋转 再次点击停止
        initRotate();
    }

    private void initPaint() {
        paint = new Paint();
        //设置画笔的颜色
        /*paint.setColor(Color.RED);*/
        //设置画笔的抗锯齿
        paint.setAntiAlias(true);
        //设置画笔的样式
        paint.setStyle(Paint.Style.STROKE);
        //设置画笔的粗细
        paint.setStrokeWidth(3);
    }
    private void initRotate() {
        rotateAnimation = new RotateAnimation(0,360,centerX,centerY);
        rotateAnimation.setDuration(8000);
    }
    //动画开始
    private void start(){
        flag=true;
        this.startAnimation(rotateAnimation);
    }
    //动画停止
    private void stop(){
        this.clearAnimation();
    }
    @Override
    public void onClick(View v) {
        myCircleViewCilck.circleViewCilckListener();
        if (flag){
            stop();
        }
        start();

    }

    public void SetCircleViewCilckListener(MyCircleViewCilck myCircleViewCilck1){
        this.myCircleViewCilck=myCircleViewCilck1;
    }
    //写一个接口
    public  interface  MyCircleViewCilck{
        void circleViewCilckListener();
    }
}
