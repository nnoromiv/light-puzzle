package com.example.lightpuzzle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class LightsView: View {

    var model: LightsModel? = null

    constructor(context: Context): super(context) {
        setup(context, "Lights view with context")
    }

    constructor(context: Context, lightsModel: LightsModel): super(context){
        model = lightsModel
        setup(context, "Lights view with model")
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs){
        setup(context, "Lights view with attr")
    }

    private val TAG: String = "LightsView"

    private val bg: Int = Color.WHITE
    private val bgGrid: Int = Color.DKGRAY
    private val fgOff: Int = Color.WHITE
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
        size = gridSquareLen / n

        xOff = midX - gridSquareLen / 2
        yOff = midY - gridSquareLen / 2
    }

    override fun onDraw(canvas: Canvas){
        painter.isAntiAlias = true
        painter.style = Paint.Style.FILL
        painter.color = bg
        setGeometry()

        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), painter)

        painter.color = bgGrid
        rectFbg.set(xOff.toFloat(), yOff.toFloat(), (xOff + gridSquareLen).toFloat(),(yOff + gridSquareLen).toFloat())
        canvas.drawRoundRect(rectFbg, 5F, 5F, painter)

        for (i in 0 until n){
            for (j in 0 until n){
                val cx = xOff + size*i + size / 2
                val cy = yOff + size*j + size / 2

                if(model != null){
                    painter.color = cols[model!!.grid[i][j]]
                    drawTile(canvas, cx, cy, painter)
                }
            }
        }
    }

    private fun drawTile(canvas: Canvas, cx:Int, cy: Int, p: Paint){
        val length = (size*7)/8
        val rad = (size/6).toFloat()

        val x = cx - length /2
        val y = cy -length /2

        rectFtile.set(x.toFloat(), y.toFloat(), (x + length).toFloat(), (y + length).toFloat())
        canvas.drawRoundRect(rectFtile, rad, rad, p)
    }

    private fun setup(context: Context, cons: String){
        Log.i(TAG, cons)

        checkModel()

        setOnClickListener{
            Log.i(TAG, "Clicked")

            val i = ((curX - xOff.toFloat()) / size.toFloat()).toInt()
            val j = ((curY - yOff.toFloat()) / size.toFloat()).toInt()
            model?.tryFlip(i, j)
            postInvalidate()
        }

        setOnTouchListener{ v, m ->
            curX = m.x
            curY = m.y

            when(m.action){
                MotionEvent.ACTION_UP -> {
                    v.performClick()
                }
            }

            true
        }
    }

    private fun checkModel() = if(model != null){
        n = model!!.n
    } else {
    Log.i(TAG, "Creating LightModel")
        n = 5
        model = LightsModel(n, notStrict = true)
    }

}