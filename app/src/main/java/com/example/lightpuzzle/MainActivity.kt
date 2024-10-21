package com.example.lightpuzzle

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var lightsView: LightsView
    private lateinit var scoreTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind views from the XML layout
        lightsView = findViewById(R.id.lightsView)
        scoreTextView = findViewById(R.id.textView)

        // Update score initially
        updateScore()
    }

    fun resetModel(view: View) {
        lightsView.model?.reset()
        lightsView.postInvalidate()  // Redraw the view
        updateScore()  // Update the score after resetting
    }

    private fun updateScore() {
        val score = lightsView.model?.getScore()
        "Score = $score".also { scoreTextView.text = it }
    }
}