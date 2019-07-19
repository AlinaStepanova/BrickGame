package com.example.alina.tetris.ui.main;


import android.graphics.Path;
import android.util.Log;

import com.example.alina.tetris.enums.FigureState;
import com.example.alina.tetris.figures.Figure;
import com.example.alina.tetris.figures.factory.FigureCreator;
import com.example.alina.tetris.ui.main.listeners.OnNetChangedListener;

import java.util.ArrayList;

import static com.example.alina.tetris.Values.EXTRA_ROWS;

public class NetManager {

    private int verticalSquaresCount, horizontalSquaresCount;

    private Figure figure;

    private boolean[][] net, zeroNet;

    public static int combo;

    private OnNetChangedListener onNetChangedListener;

    private int squareWidth, scale;

    public NetManager(OnNetChangedListener onNetChangedListener) {
        this.onNetChangedListener = onNetChangedListener;
        this.net = null;
        combo = 0;
    }

    public void initNet(int verticalSquaresCount, int horizontalSquaresCount, int widthOfSquareSide, int scale) {
        this.net = new boolean[verticalSquaresCount + EXTRA_ROWS][horizontalSquaresCount];
        setFalseNet(net);
        this.squareWidth = widthOfSquareSide;
        this.scale = scale;
        this.verticalSquaresCount = verticalSquaresCount;
        this.horizontalSquaresCount = horizontalSquaresCount;
    }

    public void initRotatedFigure(Figure figure) {
        this.figure.initMaskWithFalse();
        copyMaskToNet();
        initFigure(figure);
    }

    public void initFigure(Figure figure) {
        this.figure = figure;
        this.zeroNet = new boolean[figure.getHeightInSquare()][1];
        copyMaskToNet();
    }

    public boolean canRotate(Figure rotatedFigure) {
        boolean result = false;
        if (rotatedFigure.pointInNet.x + rotatedFigure.getWidthInSquare() <= horizontalSquaresCount
                && rotatedFigure.pointInNet.y + rotatedFigure.getHeightInSquare() < verticalSquaresCount
                && isNetFreeToMoveDown()) {
            result = true;
        }
        return result;
    }

    public void resetMaskBeforeMoveWithFalse() {
        boolean[][] falseFigureMast = new boolean[figure.getHeightInSquare()][figure.getWidthInSquare()];
        for (int i = 0; i < figure.figureMask.length; i++) {
            int startHorizontalPos = getStartHorizontalPosition(figure.figureMask[i]);
            int endPosition = getEndHorizontalPosition(figure.figureMask[i]);
            System.arraycopy(falseFigureMast[i], startHorizontalPos, net[figure.pointInNet.y + i],
                    figure.pointInNet.x + startHorizontalPos, endPosition);
        }
    }

    private void setFalseNet(boolean[][] net) {
        for (int i = 0; i < net.length; i++) {
            for (int j = 0; j < net[0].length; j++) {
                net[i][j] = false;
            }
        }
    }

    private void copyMaskToNet() {
        copyArrays(figure.figureMask.length, figure.figureMask, net, figure.getCurrentX(),
                figure.figureMask[0].length);
    }

    private void resetNetAfterMoving(int destinationPosition) {
        for (int i = 0; i < zeroNet.length; i++) {
            System.arraycopy(zeroNet[i], 0,
                    net[figure.pointInNet.y + i], destinationPosition, 1);
        }
    }

    private void moveFigure() {
        for (int i = 0; i < figure.figureMask.length; i++) {
            int startHorizontalPos = getStartHorizontalPosition(figure.figureMask[i]);
            int endPosition = getEndHorizontalPosition(figure.figureMask[i]);
            System.arraycopy(figure.figureMask[i], startHorizontalPos,
                    net[figure.pointInNet.y + i],
                    figure.pointInNet.x + startHorizontalPos, endPosition);
        }
    }

    private void copyArrays(int size, boolean[][] sourceArray, boolean[][] destinationArray,
                            int destinationPosition, int length) {
        for (int i = 0; i < size; i++) {
            System.arraycopy(sourceArray[i], 0, destinationArray[figure.getCurrentY() + i],
                    destinationPosition, length);
        }
    }

    private int getStartVerticalPosition(boolean[][] mask, int column) {
        int position = 0;
        for (int i = 0; i < mask.length; i++) {
            if (mask[i][column]) {
                position = i;
                break;
            }
        }
        return position;
    }

    private int getEndVerticalPosition(boolean[][] mask, int column) {
        int trueCount = 0;
        for (boolean[] aMask : mask) {
            if (aMask[column]) {
                trueCount += 1;
            }
        }
        return trueCount;
    }

    private int getStartHorizontalPosition(boolean[] mask) {
        int position = 0;
        for (int i = 0; i < mask.length; i++) {
            if (mask[i]) {
                position = i;
                break;
            }
        }
        return position;
    }

    private int getEndHorizontalPosition(boolean[] mask) {
        int trueCount = 0;
        for (boolean aMask : mask) {
            if (aMask) {
                trueCount += 1;
            }
        }
        return trueCount;
    }

    //todo think of rotated figure
    private boolean isFigureBelow() {
        boolean result = false;
        int coordinateX = figure.pointInNet.x;
        for (int i = figure.figureMask.length; i > 0; i--) {
            int startHorizontalPos = getStartHorizontalPosition(figure.figureMask[i - 1]);
            int endHorizontalPos = getEndHorizontalPosition(figure.figureMask[i - 1]);
            for (int j = coordinateX + startHorizontalPos;
                 j < coordinateX + endHorizontalPos + startHorizontalPos; j++) {
                int startVerticalPos = getStartVerticalPosition(figure.figureMask, j - coordinateX);
                int endVerticalPos = getEndVerticalPosition(figure.figureMask, j - coordinateX);
                if (net[figure.pointInNet.y + startVerticalPos + endVerticalPos][j]) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private boolean isFigureLeft() {
        boolean result = false;
        int coordinateY = figure.pointInNet.y;
        int coordinateX = figure.pointInNet.x;
        for (int i = 0; i < figure.getHeightInSquare(); i++) {
            int startHorizontalPos = getStartHorizontalPosition(figure.figureMask[i]);
            if (net[coordinateY + i][coordinateX + startHorizontalPos - 1]) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean isFigureRight() {
        boolean result = false;
        int coordinateY = figure.pointInNet.y;
        int coordinateX = figure.pointInNet.x;
        for (int i = 0; i < figure.getHeightInSquare(); i++) {
            int startHorizontalPos = getStartHorizontalPosition(figure.figureMask[i]);
            int endHorizontalPos = getEndHorizontalPosition(figure.figureMask[i]);
            if (net[coordinateY + i][coordinateX + startHorizontalPos + endHorizontalPos]) {
                result = true;
                break;
            }
        }
        return result;
    }

    private void levelDownNet(int level, int rowsCount) {
        boolean[][] tmpNet = new boolean[verticalSquaresCount + EXTRA_ROWS][horizontalSquaresCount];
        for (int i = 0; i < net.length; i++) {
            System.arraycopy(net[i], 0, tmpNet[i], 0, net[i].length);
        }
        for (int i = 0; i <= net.length - level; i++) {
            for (int j = 0; j < net[0].length; j++) {
                net[i][j] = false;
            }
        }
        for (int i = 0; i < net.length - level; i++) {
            System.arraycopy(tmpNet[i], 0, net[i + rowsCount], 0, tmpNet[i].length);
        }
    }

    private boolean isHorizontalLineTrue(boolean[] booleans) {
        boolean result = false;
        int j = 0;
        for (int i = 0; i < horizontalSquaresCount; i++) {
            if (booleans[i]) {
                j++;
            }
        }
        if (j == horizontalSquaresCount) {
            result = true;
        }
        return result;
    }

    private int getMaxCountOfTrue(int[][] values) {
        int max = values[1][0];
        for (int[] value : values) {
            if (value[0] > max) {
                max = value[0];
            }
        }
        return max;
    }

    public ArrayList<Path> getStoppedFiguresPaths() {
        ArrayList<Path> paths = new ArrayList<>();
        for (int i = horizontalSquaresCount - 1; i >= 0; i--) {
            for (int j = verticalSquaresCount + EXTRA_ROWS - 1; j >= 0; j--) {
                if (net[j][i]) {
                    Path path = FigureCreator.createSmallSquareFigure(i, j, squareWidth, scale);
                    paths.add(path);
                }
            }
        }
        return paths;
    }

    public boolean isVerticalLineComplete() {
        boolean result = false;
        int[][] values = new int[horizontalSquaresCount][1];
        for (int i = 0; i < horizontalSquaresCount; i++) {
            for (int j = EXTRA_ROWS - 1; j < verticalSquaresCount + EXTRA_ROWS; j++) {
                if (net[j][i]) {
                    values[i][0] = verticalSquaresCount + EXTRA_ROWS - j;
                    break;
                }
            }
            if (getMaxCountOfTrue(values) >= verticalSquaresCount + 1) {
                result = true;
                figure.setState(FigureState.STOPPED);
                onNetChangedListener.onTopLineHasTrue();
                break;
            }
        }
        return !result;
    }

    public void changeFigureState() {
        if (!isNetFreeToMoveDown()) {
            figure.setState(FigureState.STOPPED);
            onNetChangedListener.onFigureStoppedMove();
        }
    }

    public void checkBottomLine() {
        int skippedRows = 0;
        int rowsCount = 0;
        for (int i = verticalSquaresCount + EXTRA_ROWS - 1; i > 0; i--) {
            if (isHorizontalLineTrue(net[i])) {
                rowsCount++;
                skippedRows = verticalSquaresCount + EXTRA_ROWS - i;
            }
        }
        if (skippedRows != 0) {
            levelDownNet(skippedRows, rowsCount);
            combo = rowsCount;
            onNetChangedListener.onBottomLineIsTrue();
        }
    }

    public boolean isNetFreeToMoveDown() {
        boolean result = false;
        if (figure.pointInNet.y + figure.getHeightInSquare()
                != verticalSquaresCount + EXTRA_ROWS && !isFigureBelow()) {
            result = true;
        }
        return result;
    }

    public boolean isNetFreeToMoveLeft() {
        boolean result = false;
        if (figure.pointInNet.x != 0 && isNetFreeToMoveDown() && !isFigureLeft()) {
            result = true;
        }
        return result;
    }

    public boolean isNetFreeToMoveRight() {
        boolean result = false;
        if (figure.pointInNet.x + figure.getWidthInSquare() < horizontalSquaresCount
                && isNetFreeToMoveDown() && !isFigureRight()) {
            result = true;
        }
        return result;
    }

    public void moveRightInNet() {
        setFalseNet(zeroNet);
        moveFigure();
        resetNetAfterMoving(figure.pointInNet.x - 1);
    }

    public void moveLeftInNet() {
        setFalseNet(zeroNet);
        moveFigure();
        resetNetAfterMoving(figure.pointInNet.x
                + figure.getWidthInSquare());
    }

    public void moveDownInNet() {
        boolean[][] zeroNet = new boolean[1][figure.getWidthInSquare()];
        int coordinateY = figure.pointInNet.y;
        coordinateY--;
        for (int i = figure.figureMask.length; i > 0; i--) {
            int startPosition = getStartHorizontalPosition(figure.figureMask[i - 1]);
            int endPosition = getEndHorizontalPosition(figure.figureMask[i - 1]);
            System.arraycopy(figure.figureMask[i - 1], startPosition, net[coordinateY + i],
                    figure.pointInNet.x + startPosition, endPosition);
            for (boolean[] zero : zeroNet) {
                System.arraycopy(zero, startPosition, net[coordinateY + i - 1],
                        figure.pointInNet.x + startPosition, endPosition);
            }
        }
    }

    public void printNet() {
        if (net == null) {
            return;
        }
        StringBuilder str = new StringBuilder();
        for (boolean[] aNet : net) {
            for (int j = 0; j < net[0].length; j++) {
                str.append(aNet[j] ? 1 : 0);
                str.append(" ");
            }
            str.append('\n');
        }
        Log.d("logNet", str.toString());
    }
}
