package com.wgw.medalprogress.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Author:Admin
 * Time:2019/5/15 18:30
 * 描述：
 */
public class MarkPic extends View{
    private int marks = 0;
    private int mWidth = 80;
    private int mHeight = 80;
    private Paint mPaint= new Paint();
    public MarkPic(Context context) {
        super(context);
    }

    public MarkPic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarkPic(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MarkPic(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);//计算宽高
        int height = measureHeight(heightMeasureSpec);
        mWidth = width;
        mHeight = height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0,mHeight,mWidth,0,mPaint);
        canvas.drawLine(0,0,mWidth,mHeight,mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mWidth/2,mHeight/2,mWidth/2-15,mPaint);
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
    /**
     * Determines the width of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    protected int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            result = dp2px(getContext(),80);//
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }

        return result;
    }
    /**
     * Determines the height of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    protected int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            //此处高度为走完的进度高度和未走完的机大以及文字的高度的最大值
//            int textHeight = (int) (mPaint.descent() - mPaint.ascent());//得到字的高度有二种方式,第一种是React,第二种这个
//            result = Math.max(textHeight, Math.max(HorizontalProgresReachHeight,HorizontalProgresUnReachHeight)) + getPaddingTop()
//                    + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(80, specSize);
            }
        }
        return result;
    }
}
