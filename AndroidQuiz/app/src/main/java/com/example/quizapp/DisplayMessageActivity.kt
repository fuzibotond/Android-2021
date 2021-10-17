package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

lateinit var textViewForName: TextView
lateinit var textViewForGreating: TextView

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        intializeView()
    }
    private fun intializeView(){
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        textViewForName = findViewById<TextView>(R.id.textViewForName).apply { text = message }
    }
//    val message =

    // Capture the layout's TextView and set the string as its text

}