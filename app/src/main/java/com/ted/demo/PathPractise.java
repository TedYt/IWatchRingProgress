package com.ted.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Copyright (C) 2008 The Android Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Created by Ted.Yt on 9/14/16.
 */
public class PathPractise extends View {

    Paint mPaint;
    Path mPath;
    RectF mRect;

    public PathPractise(Context context) {
        this(context, null);
    }

    public PathPractise(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        mPaint = new Paint();
        mPath = new Path();
        mRect = new RectF(200,200,400,400);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBG(canvas,mPaint);
        drawFG(canvas,mPaint);
    }

    private void drawBG(Canvas canvas, Paint paint) {

        paint.reset();
        paint.setColor(Color.rgb(141,141,141));
        setPaintUnifyProper(paint);

        mPath.reset();
        mPath.addArc(mRect,-90,270);
        canvas.drawPath(mPath,paint);
    }

    private void drawFG(Canvas canvas, Paint paint) {

        paint.reset();
        //paint.setColor(Color.rgb(86, 171, 228));
        LinearGradient lg = new LinearGradient(
                mRect.left,mRect.top,mRect.right,mRect.top,
                new int[]{Color.rgb(86, 171, 228), Color.argb(50, 86, 171, 228)},
                new float[]{0f,1f},
                Shader.TileMode.CLAMP);
        paint.setShader(lg);
        setPaintUnifyProper(paint);

        mPath.reset();
        mPath.addArc(mRect,-90,180);
        canvas.drawPath(mPath,paint);
    }

    private void setPaintUnifyProper(Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(30);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
