package com.example.alina.tetris.figures;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Alina on 02.04.2017.
 */

public class LFigure extends Figure {

    private int widthOfSquareSide;

    private Paint paint = new Paint();

    private final int SQARE_COUNT = 10;

    public LFigure(Context context) {
        super(context);
        init();
    }

    private void init() {
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthOfSquareSide = MeasureSpec.getSize(widthMeasureSpec) / SQARE_COUNT;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2f);
        canvas.drawLine(20, 20, 100, 100, paint);
    }
}
