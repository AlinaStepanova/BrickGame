package com.example.alina.tetris.figures;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Alina on 02.04.2017.
 */

public class LFigure extends Figure {

    private int widthOfSquareSide;

    private Paint paint = new Paint();

    private final int SQUARE_COUNT = 10;

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
        widthOfSquareSide = MeasureSpec.getSize(widthMeasureSpec) / SQUARE_COUNT;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2f);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawLine(0, 0, 0, 120, paint);
        canvas.drawLine(0, 120, 80, 120, paint);
        canvas.drawLine(80, 120, 80, 80, paint);
        canvas.drawLine(80, 80, 40, 80, paint);
        canvas.drawLine(40, 80, 40, 0, paint);
        canvas.drawLine(40, 0, 0, 0, paint);
    }
}
