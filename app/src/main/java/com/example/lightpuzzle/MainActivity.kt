package com.example.lightpuzzle

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var lightsView: LightsView
    private lateinit var scoreTextView: TextView

    private var model: LightsModel? = null
    private val aboutItem = "About"

    companion object {
        private const val MODEL_KEY = "MODEL_KEY"
        private const val N_DEFAULT = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main){
            delay(1000L)
            startActivity(Intent(this@MainActivity, SplashScreen::class.java))
        }

        // Bind views from the XML layout
        lightsView = findViewById(R.id.lightsView)
        scoreTextView = findViewById(R.id.textView)

        model = savedInstanceState?.getSerializable(MODEL_KEY) as? LightsModel ?: getModel()

        lightsView.model = model
        // Update score initially
        updateScore()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(MODEL_KEY, getModel())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        model = savedInstanceState.getSerializable(MODEL_KEY) as LightsModel?
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(aboutItem)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title?.equals(aboutItem) == true){
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getModel(): LightsModel {
        if(model == null){
            model = LightsModel(N_DEFAULT, notStrict = true)
        }

        return model!!
    }

    fun resetModel(view: View) {
        lightsView.model?.reset()
        lightsView.invalidate()  // Redraw the view
        updateScore()  // Update the score after resetting
    }

    private fun updateScore() {
        val score = lightsView.model?.getScore() ?: 0
        "Score = $score".also { scoreTextView.text = it }
    }
}