package com.avs.brick.game.ui.main

import android.content.Context
import com.avs.brick.game.Values
import com.avs.brick.game.figures.Figure
import com.avs.brick.game.figures.figure_j.JFigure
import com.avs.brick.game.ui.main.listeners.OnNetChangedListener
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class NetManagerTest {

    private lateinit var netManager: NetManager
    private lateinit var jFigure: Figure
    private var squareWidth = 50
    private var scale = 10
    private var widthOfSquareSide = 50
    private var horizontalSquareCount = 10
    private var verticalSquareCount = 22

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val listener = Mockito.mock(OnNetChangedListener::class.java)
        val context = Mockito.mock(Context::class.java)
        jFigure = JFigure(squareWidth, scale, horizontalSquareCount, context)
        netManager = NetManager(listener, verticalSquareCount, horizontalSquareCount,
                widthOfSquareSide, scale)
    }

    @Test
    fun initNetTest() {
        assertEquals(NetManager.combo, 0)
        assertEquals(netManager.netColumnCount, horizontalSquareCount)
        assertEquals(netManager.netRowCount, verticalSquareCount + Values.EXTRA_ROWS)
    }

    @Test
    fun initJFigureTest() {
        netManager.initFigure(jFigure)
        assertEquals(jFigure.scale, scale + 2 * squareWidth)
    }
}