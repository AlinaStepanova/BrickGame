package com.example.alina.tetris;


import android.util.Log;

import com.example.alina.tetris.figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class NetManager {

    private int horizontalSquareCount;

    private int verticalSquareCount;

    private Figure figure;

    private boolean[][] net;

    private boolean[][] zeroNet;

    private final List<Figure> figureListInNet = new ArrayList<>();

    public static final int EXTRA_ROWS = 4;

    public NetManager() {
        this.net = null;
    }

    private void setNet(boolean[][] net) {
        this.net = net;
    }

    private void copyMaskToNet() {
        copyArrays(figure.figureMask.length, figure.figureMask, 0, net,
                figure.getStartX(), figure.figureMask[0].length);
    }

    private void resetNetAfterMoving(int destinationPosition) {
        for (int i = 0; i < zeroNet.length; i++) {
            System.arraycopy(zeroNet[i], 0,
                    net[figure.coordinatesInPlayingArea.y + i], destinationPosition, 1);
        }
    }

    private void setFalseNet(boolean[][] net) {
        for (int i = 0; i < net.length; i++) {
            for (int j = 0; j < net[0].length; j++) {
                net[i][j] = false;
            }
        }
    }

    private void moveFigure() {
        for (int i = 0; i < figure.figureMask.length; i++) {
            System.arraycopy(figure.figureMask[i], 0,
                    net[figure.coordinatesInPlayingArea.y + i],
                    figure.coordinatesInPlayingArea.x, figure.getWidthInSquare());
        }
    }

    private void copyArrays(int size, boolean[][] sourceArray, int sourcePosition,
                            boolean[][] destinationArray, int destinationPosition, int length) {
        for (int i = 0; i < size; i++) {
            System.arraycopy(sourceArray[i], sourcePosition, destinationArray[figure.getStartY() + i],
                    destinationPosition, length);
        }
    }

    public void initFigure(Figure figure) {
        figureListInNet.add(figure);
        this.figure = figureListInNet.get(figureListInNet.size() - 1);
        this.zeroNet = new boolean[figure.getHeightInSquare()][1];
        copyMaskToNet();
    }

    public void moveLeftInNet() {
        setFalseNet(zeroNet);
        moveFigure();
        resetNetAfterMoving(figure.coordinatesInPlayingArea.x
                + figure.getWidthInSquare());
    }

    public void moveRightInNet() {
        setFalseNet(zeroNet);
        moveFigure();
        resetNetAfterMoving(figure.coordinatesInPlayingArea.x - 1);
    }

    private boolean isFigureBelow() {
        boolean result = false;
        int coordinateX = figure.coordinatesInPlayingArea.x;
        if (figure.coordinatesInPlayingArea.y + figure.getHeightInSquare()
                < horizontalSquareCount + EXTRA_ROWS - 1) {
            for (int i = coordinateX; i < coordinateX + figure.figureMask[0].length; i++) {
                if (net[figure.coordinatesInPlayingArea.y + figure.getHeightInSquare()][i]) {
                    result = true;
                    break;
                }
            }

            //attempt to improve loop above

            /*for (int i = 0; i < figure.figureMask.length; i++) {
                int startPosition = getStartPosition(figure.figureMask[i]);
                int endPosition = getEndPosition(figure.figureMask[i]);
                Log.d("ooo", " s " + startPosition + " e " + endPosition);
                for (int j = coordinateX + startPosition; j < coordinateX + endPosition; j++) {
                    if (net[figure.coordinatesInPlayingArea.y + figure.getHeightInSquare()][j]) {
                        result = true;
                        break;
                    }
                }
            }*/
        }
        return result;
    }

    public boolean isNetFreeToMoveDown() {
        boolean result = false;
        if (figure.coordinatesInPlayingArea.y + figure.getHeightInSquare()
                != horizontalSquareCount + EXTRA_ROWS && !isFigureBelow()) {
            result = true;
        }
        return result;
    }

    private int getStartPosition(boolean [] mask) {
        int position = 0;
        for (int i = 0; i < mask.length; i++) {
            if (mask[i]) {
                position = i;
                break;
            }
        }
        return position;
    }

    private int getEndPosition(boolean [] mask) {
        int trueCount = 0;
        for (int i = 0; i < mask.length; i++) {
            if (mask[i]) {
                trueCount += 1;
            }
        }
        return trueCount;
    }

    public boolean isNetFreeToMoveLeft() {
        boolean result = false;
        if (figure.coordinatesInPlayingArea.x != 0 && isNetFreeToMoveDown()) {
            result = true;
        }
        return result;
    }

    public boolean isNetFreeToMoveRight() {
        boolean result = false;
        if (figure.coordinatesInPlayingArea.x + figure.getWidthInSquare() < verticalSquareCount
                && isNetFreeToMoveDown()) {
            result = true;
        }
        return result;
    }

    public void moveDownInNet() {
        boolean[][]zeroNet = new boolean[1][figure.getWidthInSquare()];
        int coordinateY = figure.coordinatesInPlayingArea.y;
        coordinateY--;
        for (int i = figure.figureMask.length; i > 0; i--) {
            int startPosition = getStartPosition(figure.figureMask[i - 1]);
            int endPosition = getEndPosition(figure.figureMask[i - 1]);
            System.arraycopy(figure.figureMask[i - 1], startPosition, net[coordinateY + i],
                    figure.coordinatesInPlayingArea.x + startPosition, endPosition);
            for (int j = 0; j < zeroNet.length; j++) {
                System.arraycopy(zeroNet[j], 0, net[coordinateY + i - 1],
                        figure.coordinatesInPlayingArea.x, figure.figureMask[j].length);
            }
        }
    }

    public void printNet() {
        if (net == null) {
            return;
        }
        StringBuilder str = new StringBuilder();
        int i = 0;
        for (boolean[] aNet : net) {
            str.append(i).append("  ");
            for (int j = 0; j < net[0].length; j++) {
                str.append(aNet[j] ? 1 : 0);
                str.append(" ");
            }
            str.append('\n');
            i++;
        }
        Log.d("logNet", str.toString());
    }

    public void initNet(int horizontalSquareCount, int verticalSquareCount) {
        setNet(new boolean[horizontalSquareCount + EXTRA_ROWS][verticalSquareCount]);
        setFalseNet(net);
        this.horizontalSquareCount = horizontalSquareCount;
        this.verticalSquareCount = verticalSquareCount;
    }
}
