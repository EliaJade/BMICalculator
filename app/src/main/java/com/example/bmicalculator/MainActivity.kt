package com.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var heightSlider: Slider
    lateinit var heightTextView: TextView

    lateinit var weightAddButton: Button
    lateinit var  weightTextView: TextView
    lateinit var weightMinusButton: Button

    lateinit var calculatorButton: Button
    lateinit var resultTextView: TextView

    lateinit var descriptionTextView: TextView

    var weight = 75.0f
    var height = 170.0f

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
        heightTextView = findViewById(R.id.heightTextView)

        weightTextView = findViewById(R.id.weightTextView)
        weightAddButton = findViewById(R.id.weightAddButton)
        weightMinusButton = findViewById(R.id.weightMinusButton)


        calculatorButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        descriptionTextView = findViewById(R.id.descriptionTextView)


        heightSlider.addOnChangeListener {slider, value, fromUser ->
            heightTextView.text = "${value.toInt()} cm"
        }

        weightAddButton.setOnClickListener {
            weight ++
            weightTextView.text = "${weight.toInt()} kg"
        }

        weightMinusButton.setOnClickListener {
            weight --
            weightTextView.text = "${weight.toInt()} kg"
        }

        //heightEditText.setText("180")
        //weightEditText.setText("70")

        calculatorButton.setOnClickListener {
            //val height = 0f//heightEditText.text.toString().toFloat()
            //val weight = 0f//weightEditText.text.toString().toFloat()

            val result = weight / (height / 100).pow(2)


            //resultTextView.text = "$result"
            resultTextView.text = String.format(Locale.getDefault(), "%.2f", result)

            var colorId = 0
            var textId = 0
            when (result) {
                in 0f..18.5f -> {
                    colorId = R.color.imcUnderweight
                    textId = R.string.imcUnderweight
                }
                in 18.5f..<25f -> {
                    colorId = R.color.imcNormalweight
                    textId = R.string.imcNormalweight
                }
                in 25f..<30f -> {
                    colorId = R.color.imcOverweight
                    textId = R.string.imcOverweight
                }
                in 30f..<35f -> {
                    colorId = R.color.imcObesity1weight
                    textId = R.string.imcObesity1weight
                }
                in 35f..<40f -> {
                    colorId = R.color.imcObesity2weight
                    textId = R.string.imcObesity2weight
                }
                else -> {
                    colorId = R.color.imcObesity3weight
                    textId = R.string.imcObesity3weight
                }
            }
            resultTextView.setTextColor(getColor(colorId))
            descriptionTextView.text = getString(textId)
        }
    }
}