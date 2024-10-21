package com.example.lightpuzzle

import android.util.Log

class LightsModel(gridSize: Int, private val notStrict: Boolean) {

    private val tag = "LightsModel"

    // Create 2D Array
    private var grid = Array(gridSize){
        IntArray(gridSize)
    }

    private var n = gridSize

    // Check if switch is on
    private fun isSwitchOn(i: Int, j:Int):Boolean {
        return if(i in 0 until n && j in 0 until n){
            grid[i][j] == 1
        } else {
            false // Out of Bounds -- Off
        }
    }


    fun flipLines(i: Int, j:Int){
        try {
            // Flip the selected switch
            grid[i][j] = 1 - grid[i][j]

            // Flip all elements in same row
            for(x in 0 until n){
                if(x != j){
                    grid[i][x] = 1 - grid[i][x]
                }
            }

            // Flip all elements in same column
            for(y in 0 until n){
                if(y != i){
                    grid[y][j] = 1 - grid[y][j]
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            Log.e(tag, e.message.toString() )
        }
    }

    fun isSolved(): Boolean {
        var onCount = 0

        // Count all switches that is ON
        for(i in 0 until n){
            for(j in 0 until n){
                if(grid[i][j] == 1){
                    onCount++
                }
            }
        }

        // Puzzle is solved
        return onCount == (n*n)-1
    }

    // toString Method for test purposes
    override fun toString(): String {
        val sb = StringBuilder()

        for (i in 0 until n){
            sb.append(grid[i].contentToString() + "\n")
        }

        return sb.toString()
    }


    fun tryFlip(i: Int, j: Int){
        try {
            if(isSwitchOn(i,j) || notStrict){
                flipLines(i, j)
            }
        } catch (e: Exception){
            Log.e(tag, e.message.toString())
        }
    }
}