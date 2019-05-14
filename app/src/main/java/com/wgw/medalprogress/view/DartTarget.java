package com.wgw.medalprogress.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author:Admin
 * Time:2019/5/9 10:17
 * 描述：
 */
public class DartTarget extends View {
    public DartTarget(Context context) {
        super(context);
    }

    public DartTarget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DartTarget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public DartTarget(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
