package com.example.lightpuzzle

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView

class AboutActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.setDisplayShowCustomEnabled(true)

        val web = WebView(this)
        web.loadUrl("file:///android_asset/html/About.html")
        setContentView(web)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when( item.itemId){
            android.R.id.home -> {
                run {
                    finish()
                }

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}