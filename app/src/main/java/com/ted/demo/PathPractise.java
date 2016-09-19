package com.ted.demo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
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
import android.view.animation.LinearInterpolator;

import com.ted.iwatchringprogress.R;

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

    float mAnimatiorValue = 1f;

    private int mBGColor;
    private int mBGArc;
    private int mFGStartColor;
    private int mFGEndColor;
    private int mFGArc;

    public PathPractise(Context context) {
        this(context, null);
    }

    public PathPractise(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathPractise(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PathPractise);
        if (typedArray != null){
            mBGArc = typedArray.getInteger(R.styleable.PathPractise_bgArc,180);
            mBGColor = typedArray.getColor(R.styleable.PathPractise_bgColor,Color.WHITE);
            mFGStartColor = typedArray.getColor(R.styleable.PathPractise_fgStartColor, Color.WHITE);
            mFGEndColor = typedArray.getColor(R.styleable.PathPractise_fgEndColor, Color.WHITE);
            mFGArc = typedArray.getInteger(R.styleable.PathPractise_fgArc,90);

            typedArray.recycle();
        }
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
        paint.setColor(mBGColor);
        setPaintUnifyProper(paint);

        mPath.reset();
        mPath.addArc(mRect,-90,mBGArc);
        canvas.drawPath(mPath,paint);
    }

    private void drawFG(Canvas canvas, Paint paint) {

        paint.reset();
        //paint.setColor(Color.rgb(86, 171, 228));
        LinearGradient lg = new LinearGradient(
                mRect.left,mRect.top,mRect.right,mRect.top,
                new int[]{mFGStartColor, mFGEndColor},
                new float[]{0f,1f},
                Shader.TileMode.CLAMP);
        paint.setShader(lg);
        setPaintUnifyProper(paint);

        mPath.reset();
        mPath.addArc(mRect,-90,mFGArc * mAnimatiorValue);
        canvas.drawPath(mPath,paint);

        /*paint.reset();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(0,0,0));
        paint.setTextSize(20);
        canvas.drawTextOnPath("Test 1", mPath, 10, 7.5f, paint);
        canvas.drawTextOnPath("1000000000", mPath, 200, 7.5f, paint);*/
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

    public void startAnim(int i) {
        startViewAnima(0f, 1f, i);
    }

    private void startViewAnima(float start, float end, int duration){

        final ValueAnimator va = ValueAnimator.ofFloat(start, end);
        va.setDuration(duration);
        va.setRepeatCount(0);
        va.setInterpolator(new LinearInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatiorValue = (float)animation.getAnimatedValue();
                invalidate();
            }
        });

        if (!va.isRunning()){
            va.start();
        }
    }
}
