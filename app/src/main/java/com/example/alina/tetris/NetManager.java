package com.example.alina.tetris;


import android.util.Log;

import com.example.alina.tetris.enums.FigureState;
import com.example.alina.tetris.figures.Figure;
import com.example.alina.tetris.listeners.OnNetChangedListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.alina.tetris.values.Values.EXTRA_ROWS;
import static com.example.alina.tetris.values.Values.SQUARE_COUNT_HORIZONTAL;

public class NetManager {

    private int horizontalSquareCount;

    private int verticalSquareCount;

    private Figure figure;

    private boolean[][] net;

    private boolean[][] zeroNet;

    public static int combo;

    private final List<Figure> figureListInNet = new ArrayList<>();

    private OnNetChangedListener onNetChangedListener;

    public NetManager() {
        this.net = null;
        combo = 0;
    }

    private void setNet(boolean[][] net) {
        this.net = net;
    }

    private void setFalseNet(boolean[][] net) {
        for (int i = 0; i < net.length; i++) {
            for (int j = 0; j < net[0].length; j++) {
                net[i][j] = false;
            }
        }
    }

    private void copyMaskToNet() {
        copyArrays(figure.figureMask.length, figure.figureMask, net, figure.getStartX(),
                figure.figureMask[0].length);
    }

    private void resetNetAfterMoving(int destinationPosition) {
        for (int i = 0; i < zeroNet.length; i++) {
            System.arraycopy(zeroNet[i], 0,
                    net[figure.coordinatesInPlayingArea.y + i], destinationPosition, 1);
        }
    }

    private void moveFigure() {
        for (int i = 0; i < figure.figureMask.length; i++) {
            System.arraycopy(figure.figureMask[i], 0,
                    net[figure.coordinatesInPlayingArea.y + i], figure.coordinatesInPlayingArea.x,
                    figure.getWidthInSquare());
        }
    }

    private void copyArrays(int size, boolean[][] sourceArray, boolean[][] destinationArray,
                            int destinationPosition, int length) {
        for (int i = 0; i < size; i++) {
            System.arraycopy(sourceArray[i], 0, destinationArray[figure.getStartY() + i],
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

    private boolean isFigureBelow() {
        boolean result = false;
        int coordinateX = figure.coordinatesInPlayingArea.x;
        for (int i = figure.figureMask.length; i > 0; i--) {
            int startHorizontalPos = getStartHorizontalPosition(figure.figureMask[i - 1]);
            int endHorizontalPos = getEndHorizontalPosition(figure.figureMask[i - 1]);
            for (int j = coordinateX + startHorizontalPos;
                 j < coordinateX + endHorizontalPos + startHorizontalPos; j++) {
                int startVerticalPos = getStartVerticalPosition(figure.figureMask, j - coordinateX);
                int endVerticalPos = getEndVerticalPosition(figure.figureMask, j - coordinateX);
                if (net[figure.coordinatesInPlayingArea.y + startVerticalPos + endVerticalPos][j]) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private boolean isFigureLeft() {
        boolean result = false;
        int coordinateY = figure.coordinatesInPlayingArea.y;
        int coordinateX = figure.coordinatesInPlayingArea.x;
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
        int coordinateY = figure.coordinatesInPlayingArea.y;
        int coordinateX = figure.coordinatesInPlayingArea.x;
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

    private void levelDownNet(int level) {
        boolean[][] tmpNet = new boolean[horizontalSquareCount + EXTRA_ROWS][verticalSquareCount];
        for (int i = 0; i < net.length; i++) {
            System.arraycopy(net[i], 0, tmpNet[i], 0, net[i].length);
        }
        setFalseNet(net);
        for (int i = 0; i < net.length - level; i++) {
            System.arraycopy(tmpNet[i], 0, net[i + level], 0, tmpNet[i].length);
        }
    }

    private boolean isHorizontalLineTrue(boolean[] booleans) {
        boolean result = false;
        int j = 0;
        for (int i = 0; i < verticalSquareCount; i++) {
            if (booleans[i]) {
                j++;
            }
        }
        if (j == SQUARE_COUNT_HORIZONTAL) {
            result = true;
        }
        return result;
    }

    private int getMaxCountOfTrue(int values[][]) {
        int max = values[1][0];
        for (int[] value : values) {
            if (value[0] > max) {
                max = value[0];
            }
        }
        return max;
    }

    public boolean isVerticalLineTrue() {
        boolean result = false;
        int [][] values = new int[verticalSquareCount][1];
        for (int i = 0; i < verticalSquareCount; i++) {
            for (int j = EXTRA_ROWS - 1; j < horizontalSquareCount + EXTRA_ROWS; j++) {
                if (net[j][i]) {
                    values[i][0] = horizontalSquareCount + EXTRA_ROWS - j;
                    break;
                }
            }
            if (getMaxCountOfTrue(values) >= horizontalSquareCount + 1) {
                result = true;
                onNetChangedListener.onTopLineHasTrue();
                break;
            }
        }
        return result;
    }

    public void setOnNetChangedListener(OnNetChangedListener onNetChangedListener) {
        this.onNetChangedListener = onNetChangedListener;
    }

    public void initFigure(Figure figure) {
        figureListInNet.add(figure);
        this.figure = figureListInNet.get(figureListInNet.size() - 1);
        this.zeroNet = new boolean[figure.getHeightInSquare()][1];
        copyMaskToNet();
    }

    public void changeFigureState() {
        if (!isNetFreeToMoveDown()) {
            figure.setState(FigureState.STOPPED);
            onNetChangedListener.onFigureStoppedMove();
        }
    }

    public int checkBottomLine() {
        int skippedRows = 0;
        for (int i = horizontalSquareCount + EXTRA_ROWS - 1; i > 0; i--) {
            if (isHorizontalLineTrue(net[i])
                    && isHorizontalLineTrue(net[horizontalSquareCount + EXTRA_ROWS - 1])) {
                skippedRows = horizontalSquareCount + EXTRA_ROWS - i;
            }
        }
        levelDownNet(skippedRows);
        combo = skippedRows;
        onNetChangedListener.onBottomLineIsTrue();
        return skippedRows;
    }

    public boolean isNetFreeToMoveDown() {
        boolean result = false;
        if (figure.coordinatesInPlayingArea.y + figure.getHeightInSquare()
                != horizontalSquareCount + EXTRA_ROWS && !isFigureBelow()) {
            result = true;
        }
        return result;
    }

    public boolean isNetFreeToMoveLeft() {
        boolean result = false;
        if (figure.coordinatesInPlayingArea.x != 0 && isNetFreeToMoveDown() && !isFigureLeft()) {
            result = true;
        }
        return result;
    }

    public boolean isNetFreeToMoveRight() {
        boolean result = false;
        if (figure.coordinatesInPlayingArea.x + figure.getWidthInSquare() < verticalSquareCount
                && isNetFreeToMoveDown() && !isFigureRight()) {
            result = true;
        }
        return result;
    }

    public void moveRightInNet() {
        setFalseNet(zeroNet);
        moveFigure();
        resetNetAfterMoving(figure.coordinatesInPlayingArea.x - 1);
    }

    public void moveLeftInNet() {
        setFalseNet(zeroNet);
        moveFigure();
        resetNetAfterMoving(figure.coordinatesInPlayingArea.x
                + figure.getWidthInSquare());
    }

    public void moveDownInNet() {
        boolean[][] zeroNet = new boolean[1][figure.getWidthInSquare()];
        int coordinateY = figure.coordinatesInPlayingArea.y;
        coordinateY--;
        for (int i = figure.figureMask.length; i > 0; i--) {
            int startPosition = getStartHorizontalPosition(figure.figureMask[i - 1]);
            int endPosition = getEndHorizontalPosition(figure.figureMask[i - 1]);
            System.arraycopy(figure.figureMask[i - 1], startPosition, net[coordinateY + i],
                    figure.coordinatesInPlayingArea.x + startPosition, endPosition);
            for (int j = 0; j < zeroNet.length; j++) {
                System.arraycopy(zeroNet[j], startPosition, net[coordinateY + i - 1],
                        figure.coordinatesInPlayingArea.x + startPosition, endPosition);
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

    public void initNet(int horizontalSquareCount, int verticalSquareCount) {
        setNet(new boolean[horizontalSquareCount + EXTRA_ROWS][verticalSquareCount]);
        setFalseNet(net);
        this.horizontalSquareCount = horizontalSquareCount; //20
        this.verticalSquareCount = verticalSquareCount; //10
    }
}
