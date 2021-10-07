package com.fury.cv.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.google.firebase.crash.FirebaseCrash;

public class CompressorDotsProgressBar extends View {
    private int heightSize;
    private int mDotCount;
    private Handler mHandler;
    private int mIndex;
    private Paint mPaint;
    private Paint mPaintFill;
    private float mRadius;
    private Runnable mRunnable;
    private int margin;
    private int step;
    private int widthSize;

    /* renamed from: com.video.compressop.view.CompressorDotsProgressBar.1 */
    class C10421 implements Runnable {
        C10421() {
        }

        public void run() {
            try {
                CompressorDotsProgressBar compressorDotsProgressBar = CompressorDotsProgressBar.this;
                compressorDotsProgressBar.mIndex = compressorDotsProgressBar.mIndex + CompressorDotsProgressBar.this.step;
                if (CompressorDotsProgressBar.this.mIndex < 0) {
                    CompressorDotsProgressBar.this.mIndex = 1;
                    CompressorDotsProgressBar.this.step = 1;
                } else if (CompressorDotsProgressBar.this.mIndex > CompressorDotsProgressBar.this.mDotCount - 1) {
                    if (CompressorDotsProgressBar.this.mDotCount - 2 >= 0) {
                        CompressorDotsProgressBar.this.mIndex = CompressorDotsProgressBar.this.mDotCount - 2;
                        CompressorDotsProgressBar.this.step = -1;
                    } else {
                        CompressorDotsProgressBar.this.mIndex = 0;
                        CompressorDotsProgressBar.this.step = 1;
                    }
                }
                CompressorDotsProgressBar.this.invalidate();
                CompressorDotsProgressBar.this.mHandler.postDelayed(CompressorDotsProgressBar.this.mRunnable, 300);
            } catch (Exception e) {
                FirebaseCrash.report(new Exception("c10421"));
            }
        }
    }

    public CompressorDotsProgressBar(Context context) {
        super(context);
        this.mPaintFill = new Paint(1);
        this.mPaint = new Paint(1);
        this.mHandler = new Handler();
        this.mIndex = 0;
        this.margin = 7;
        this.mDotCount = 4;
        this.step = 1;
        this.mRunnable = new C10421();
        init(context);
    }

    public CompressorDotsProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPaintFill = new Paint(1);
        this.mPaint = new Paint(1);
        this.mHandler = new Handler();
        this.mIndex = 0;
        this.margin = 7;
        this.mDotCount = 4;
        this.step = 1;
        this.mRunnable = new C10421();
        init(context);
    }

    public CompressorDotsProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mPaintFill = new Paint(1);
        this.mPaint = new Paint(1);
        this.mHandler = new Handler();
        this.mIndex = 0;
        this.margin = 7;
        this.mDotCount = 4;
        this.step = 1;
        this.mRunnable = new C10421();
        init(context);
    }

    private void init(Context context) {
        this.mRadius = 5.0f;
        this.mPaintFill.setStyle(Style.FILL);
        this.mPaintFill.setColor(-1);
        this.mPaint.setStyle(Style.FILL);
        this.mPaint.setColor(872415231);
        start();
    }

    public void setDotsCount(int count) {
        this.mDotCount = count;
    }

    public void start() {
        this.mIndex = -1;
        this.mHandler.removeCallbacks(this.mRunnable);
        this.mHandler.post(this.mRunnable);
    }

    public void stop() {
        this.mHandler.removeCallbacks(this.mRunnable);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.widthSize = MeasureSpec.getSize(widthMeasureSpec);
        this.heightSize = ((((int) this.mRadius) * 2) + getPaddingBottom()) + getPaddingTop();
        setMeasuredDimension(this.widthSize, this.heightSize);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            float dX = ((((float) this.widthSize) - ((((float) this.mDotCount) * this.mRadius) * 2.0f)) - ((float) ((this.mDotCount - 1) * this.margin))) / 2.0f;
            float dY = (float) (this.heightSize / 2);
            for (int i = 0; i < this.mDotCount; i++) {
                if (i == this.mIndex) {
                    canvas.drawCircle(dX, dY, this.mRadius, this.mPaintFill);
                } else {
                    canvas.drawCircle(dX, dY, this.mRadius, this.mPaint);
                }
                dX += (this.mRadius * 2.0f) + ((float) this.margin);
            }
        } catch (Exception e) {
            FirebaseCrash.report(new Exception("onDraw"));
        }
    }
}
