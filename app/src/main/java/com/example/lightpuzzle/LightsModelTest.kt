package com.example.lightpuzzle

import org.junit.Test

class LightsModelTest {

    @Test

    fun main() {
         val model = LightsModel(5, false)

        println(model)
        model.flipLines(2,2)
        println(model)
        model.flipLines(1,2)
        println(model)
        model.flipLines(1,1)
        println(model)
    }
}