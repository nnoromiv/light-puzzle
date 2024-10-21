package com.example.lightpuzzle

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

class LightsView(context: Context, private var model: LightsModel): View(context) {

    private val TAG: String = "LightsView"

    private val bg: Int = Color.BLACK
    private val bgGrid: Int = Color.BLUE
    private val fgOff: Int = Color.DKGRAY
    private val fgOn: Int = Color.YELLOW

    private val cols = intArrayOf(fgOff, fgOn)

    private var n = 5

    private var size = 0
    private var minLen = 0
    private var gridSquareLen = 0
    private var xOff = 0
    private var yOff = 0

    private var curX = 0F
    private var curY = 0F

    private var painter:Paint = Paint()
    private var rectFbg = RectF()
    private var rectFtile = RectF()

    private fun setGeometry() {
        val midX = width / 2
        val midY = height / 2

        minLen = Math.min(width, height)

        gridSquareLen = (minLen / n) * n
        size = gridSquareLen / 2
    }
}