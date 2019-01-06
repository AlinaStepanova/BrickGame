package com.example.alina.tetris.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.alina.tetris.figures.Figure;


public class PreviewArea extends View {

    private Paint paint;
    private Figure figure;

    public PreviewArea(Context context) {
        super(context);
        init();
    }

    public PreviewArea(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PreviewArea(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
    }

    public void drawNextFigure(Figure figure) {
        this.figure = figure;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (figure != null) {
            Path path = figure.getPath();
            paint.setColor(figure.getColor());
            canvas.drawPath(path, paint);
        }
    }
}
