package com.avs.brick.game.ui.main.listeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnViewTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    private final OnPlayingAreaTouch onPlayingAreaTouch;

    private int halfScreenWidth = 0;

    public OnViewTouchListener(Context context, OnPlayingAreaTouch onPlayingAreaTouch) {
        gestureDetector = new GestureDetector(context, new GestureListener());
        this.onPlayingAreaTouch = onPlayingAreaTouch;
    }

    public void setScreenWidth(int screenWidth) {
        this.halfScreenWidth = screenWidth / 2;
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            if (halfScreenWidth != 0 && onPlayingAreaTouch != null) {
                if (e.getX() <= halfScreenWidth) {
                    onPlayingAreaTouch.onLongLeftClick();
                } else if (e.getX() > halfScreenWidth) {
                    onPlayingAreaTouch.onLongRightClick();
                }
            }
            super.onLongPress(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (halfScreenWidth != 0 && onPlayingAreaTouch != null) {
                if (e.getX() <= halfScreenWidth) {
                    onPlayingAreaTouch.onLeftMove();
                } else if (e.getX() > halfScreenWidth) {
                    onPlayingAreaTouch.onRightMove();
                }
            }
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0) {
                    if (onPlayingAreaTouch != null) onPlayingAreaTouch.onRightMove();
                } else {
                    if (onPlayingAreaTouch != null) onPlayingAreaTouch.onLeftMove();
                }
                return true;
            }
            return false;
        }
    }
}
