package com.ktl1.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tvCount)
        val countButton = findViewById<Button>(R.id.btnCount)
        val downloadButton = findViewById<Button>(R.id.downloadBtn)

        countButton.setOnClickListener{
            textView.text = count++.toString()
        }

        downloadButton.setOnClickListener{

            // use coroutineScope for Sycronesly work
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()

            }
        }
    }

    private suspend fun downloadUserData() {
        for(i in 1..2000000){  // 3..9
            Log.i("myTag","Download user $i ${Thread.currentThread().name}")
            delay(100) // use for late running log after the one by one
        }
    }
}