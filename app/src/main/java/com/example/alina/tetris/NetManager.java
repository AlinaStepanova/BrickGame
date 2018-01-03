package com.example.alina.tetris;


import android.util.Log;

import com.example.alina.tetris.figures.Figure;

public class NetManager {

    private Figure figure;

    private boolean[][] net;

    public NetManager(Figure figure) {
        this.figure = figure;
        net = null;
    }

    public boolean[][] getNet() {
        return net;
    }

    public void setNet(boolean[][] net) {
        this.net = net;
    }

    public boolean[] getNetElement(int index){
        return net[index];
    }

    public void moveLeftInNet() {
        int width = figure.getWidthInSquare();
        int height = figure.getHeightInSquare();
        for (int i = figure.coordinateInPole.x; i < figure.coordinateInPole.x + width; i++) {
            swapFigureCoordinatesInNet(figure.coordinateInPole.y,
                    figure.coordinateInPole.y + height, i - 1, i);
        }
    }

    public void moveRightInNet() {
        int width = figure.getWidthInSquare();
        int height = figure.getHeightInSquare();
        for (int i = figure.coordinateInPole.x + width + 1; i > figure.coordinateInPole.x; i--) {
            swapFigureCoordinatesInNet(figure.coordinateInPole.y,
                    figure.coordinateInPole.y + height, i, i + 1);
        }
    }

    public void moveDownInNet() {
        int width = figure.getWidthInSquare();
        int height = figure.getHeightInSquare();
        for (int i = figure.coordinateInPole.y; i < figure.coordinateInPole.y + height; i++) {
            swapFigureCoordinatesInNet(i - 1, i, figure.coordinateInPole.x,
                    figure.coordinateInPole.x + width);
        }
    }

    private void swapFigureCoordinatesInNet(int from, int to, int top, int bottom) {
        for (int i = top; i < bottom; i++) {
            boolean tmp = net[from][i];
            net[from][i] = net[to][i];
            net[to][i] = tmp;
        }
    }

    private void setFalseNet() {
        for (int i = 0; i < net.length; i++) {
            for (int j = 0; j < net[0].length; j++) {
                net[i][j] = false;
            }
        }
    }

    public void printNet() {
        if (net == null) {
            return;
        }
        String str = "";
        for (int i = 0; i < net.length; i++) {
            for (int j = 0; j < net[0].length; j++) {
                str += net[i][j];
                str += " ";
            }
            str += '\n';
            Log.d("logNet", str);
        }
    }

    public void initNet(int horizontalSquareCount, int verticalSquareCount) {
        setNet(new boolean[horizontalSquareCount][verticalSquareCount]);
        setFalseNet();
    }
}
