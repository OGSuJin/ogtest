package com.example.taegyung.navmyapps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by TaeGyung on 2021-12-27.
 */

public class PaintBoard extends View {

    Canvas mCanvas;
    Bitmap mBitmap;
    Paint mPaint;

    // 색깔 등 속성 저장
    // 초기 속성 저장
    int iColor = Color.BLACK;
    boolean bAntiAlias = false;
    static int iStrokeWidth = 1;
    Paint.Style psStyle = Paint.Style.FILL;

    // 지난 좌표
    int lastX;
    int lastY;

    public PaintBoard(Context context) {

        super(context);

        init();
    }

    public PaintBoard(Context context, AttributeSet attrs) {

        super(context, attrs);

        init();
    }

    private void init() {

        this.mPaint = new Paint();
        this.mPaint.setColor(iColor);
        this.mPaint.setAntiAlias(bAntiAlias);
        this.mPaint.setStrokeWidth(iStrokeWidth);
        this.mPaint.setStyle(psStyle);

        this.lastX = -1;
        this.lastY = -1;
    }

    public int getiColor() {
        return iColor;
    }

    public boolean isbAntiAlias() {
        return bAntiAlias;
    }

    public Paint.Style getPsStyle() {
        return psStyle;
    }

    // 캔버스 모두 지우기
    public void clearCanvas() {

        if ( mCanvas != null ) {
            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        }
    }

    // 속성 설정 화면
    public void setPaintAttr(int color, boolean antialias, int strokewidth, Paint.Style style) {

        iColor = color;
        bAntiAlias = antialias;
        iStrokeWidth = strokewidth;
        psStyle = style;

        this.mPaint.setColor(iColor);
        this.mPaint.setAntiAlias(bAntiAlias);
        this.mPaint.setStrokeWidth(iStrokeWidth);
        this.mPaint.setStyle(psStyle);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        Bitmap img = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);
        canvas.drawColor(Color.WHITE);

        mBitmap = img;
        mCanvas = canvas;
    }

//    0. for문으로 drawLine 을 여러번 출력하는 것보다 아래 코딩이 성능이 우수하다.
//    1. 빈 Bitmap을 만든다. (Bitmap의 종류 참조)
//    2. Bitmap을 인자로 Canvas를 생성한다.
//    3. 생성된 Canvas를 이용하여 Bitmap에 그림을 그리고 저장
//    4. Bitmap을 화면에 출력한다.
    protected void onDraw(Canvas canvas) {

        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        int X = (int)event.getX();
        int Y = (int)event.getY();

        switch (action) {

            case MotionEvent.ACTION_UP:
                lastX = -1;
                lastY = -1;

                break;

            case MotionEvent.ACTION_DOWN:
                if (lastX != -1) {

                    if(X != lastX || Y != lastY) {
                        mCanvas.drawLine(lastX, lastY, X, Y, mPaint);

                    }
                }

                lastX = X;
                lastY = Y;

                break;

            case MotionEvent.ACTION_MOVE:
                if (lastX != -1) {
                    mCanvas.drawLine(lastX, lastY, X, Y, mPaint);
                }

                lastX = X;
                lastY = Y;

                break;
        }

        // onDraw 메소드 호출
        invalidate();

        return true;
    }
}
