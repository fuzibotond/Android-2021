package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inputTextArea = findViewById<EditText>(R.id.editTextTextPersonName)
        val buttonGetStarted = findViewById<Button>(R.id.buttonGetStarted)
        buttonGetStarted.setOnClickListener {
            Log.i(TAG, "Button Clicked!")
//            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            val snackbar = Snackbar.make(
                it,
                "Button clicked! Thanks: ${inputTextArea.text.toString()}!",
                Snackbar.LENGTH_SHORT
            )
            snackbar.show()
        }
    }



}