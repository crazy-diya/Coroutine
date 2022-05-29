package com.ktl1.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tvCount)
        textView2 = findViewById<TextView>(R.id.tvText1)
        val countButton = findViewById<Button>(R.id.btnCount)
        val downloadButton = findViewById<Button>(R.id.downloadBtn)

        countButton.setOnClickListener {
            textView.text = count++.toString()
        }

        downloadButton.setOnClickListener {

            // use coroutineScope for Sycronesly work
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()

            }
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..2000000) {  // 3..9
            Log.i("myTag", "Download user $i ${Thread.currentThread().name}")
            withContext(Dispatchers.Main){  // this is how switch coroutine between thread one to another
                textView2.text = i.toString()
            }
            delay(100) // use for late running log after the one by one
        }
    }
}