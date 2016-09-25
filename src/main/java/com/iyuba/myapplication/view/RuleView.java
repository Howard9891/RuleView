package com.iyuba.myapplication.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
/**
 * Created by mingyu on 16-9-23.
 */
public class RuleView extends View{
    private float startIndex =30.0f;
    private float endIndex =200.0f;
    private float rangle = 0.0f;
    private int nums;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int length;
    private int deleta;
    private int lastX;
    private int lastY;
    public RuleView(Context context) {
        this(context,null);
    }
    public RuleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public RuleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rangle = endIndex-startIndex;
        nums = (int) rangle;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(50);
        mPaint.setStyle(Paint.Style.FILL);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth,mHeight);
        Log.e("RuleView","mWidth>>>>"+mWidth);
        Log.e("RuleView","mHeight>>>"+mHeight);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        length = mWidth;
        deleta = length/nums;
        for(int i=0;i<nums;i++){
            if(i%10==0){
                canvas.drawLine(deleta*i,0,deleta*i,100,mPaint);
                canvas.drawText(""+i/10,deleta*i,140,mPaint);

            }else{
                canvas.drawLine(deleta*i,0,deleta*i,50,mPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;
                layout(getLeft()+offsetX,getTop(),getRight()+offsetX,getBottom());
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;

    }
}
