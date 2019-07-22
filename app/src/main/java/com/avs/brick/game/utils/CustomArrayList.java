package com.avs.brick.game.utils;

import java.util.ArrayList;

public class CustomArrayList<T> extends ArrayList<T> {

    public T getLast() {
        return this.get(this.size() - 1);
    }
}
