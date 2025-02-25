package com.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var heightSlider: EditText
    lateinit var heightTextView: TextView
    lateinit var weightAddButton: Button
    lateinit var  weightTextView: TextView
    lateinit var weightMinusButton: Button
    lateinit var calculatorButton: Button
    lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        heightSlider = findViewById(R.id.heightSlider)
        heightTextView = findViewById((R.id.heightSlider))

        weightTextView = findViewById(R.id.weightTextView)
        weightAddButton = findViewById(R.id.weightAddButton)
        weightMinusButton = findViewById(R.id.weightMinusButton)


        calculatorButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)


        heightSlider.addOnChangeListener {slider, value, fromUser ->
            heightTextView.text = "${value.toInt()}"
        }

        //heightEditText.setText("180")
        //weightEditText.setText("70")

        calculatorButton.setOnClickListener {
            val height = heightEditText.text.toString().toFloat()
            val weight = weightEditText.text.toString().toFloat()

            val result = weight / (height / 100).pow(2)


            resultTextView.text = "$result"
        }
    }
}