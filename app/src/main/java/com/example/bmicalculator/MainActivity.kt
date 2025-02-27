package com.example.bmicalculator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var heightSlider: Slider
    lateinit var heightTextView: TextView

    lateinit var weightAddButton: Button
    lateinit var weightEditText: EditText
    lateinit var weightMinusButton: Button

    lateinit var calculatorButton: Button
    lateinit var recalculateButton: Button
    lateinit var resultTextView: TextView

    lateinit var descriptionTextView: TextView

    lateinit var resultCardView: MaterialCardView

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

        weightEditText = findViewById(R.id.weightEditText)
        weightAddButton = findViewById(R.id.weightAddButton)
        weightMinusButton = findViewById(R.id.weightMinusButton)

        resultCardView = findViewById(R.id.resultCardView)

        calculatorButton = findViewById(R.id.calculateButton)
        recalculateButton = findViewById(R.id.recalculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        descriptionTextView = findViewById(R.id.descriptionTextView)


        heightSlider.addOnChangeListener { slider, value, fromUser ->
            heightTextView.text = "${value.toInt()}"
        }

        weightAddButton.setOnClickListener {
            weight++
            weightEditText.setText("${weight.toInt()}")
        }

        weightMinusButton.setOnClickListener {
            weight--
            if (weight < 1) {
                weight = 1f
            }
            weightEditText.setText("${weight.toInt()}")
        }

        weightEditText.addTextChangedListener {
            if (weightEditText.text.toString().isNotEmpty()) {
                weight = weightEditText.text.toString().toFloat()
            }
        }


        calculatorButton.setOnClickListener {

            resultCardView.visibility = View.VISIBLE
            calculatorButton.visibility = View.GONE
            recalculateButton.visibility = View.VISIBLE

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
                    ObesityDialogue()
                }

                in 35f..<40f -> {
                    colorId = R.color.imcObesity2weight
                    textId = R.string.imcObesity2weight
                    ObesityDialogue()
                }

                else -> {
                    colorId = R.color.imcObesity3weight
                    textId = R.string.imcObesity3weight
                    ObesityDialogue()
                }

            }

            resultTextView.setTextColor(getColor(colorId))
            descriptionTextView.text = getString(textId)


        }

        recalculateButton.setOnClickListener {


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
                    ObesityDialogue()
                }

                in 35f..<40f -> {
                    colorId = R.color.imcObesity2weight
                    textId = R.string.imcObesity2weight
                    ObesityDialogue()
                }

                else -> {
                    colorId = R.color.imcObesity3weight
                    textId = R.string.imcObesity3weight
                    ObesityDialogue()
                }
            }
        }
    }




    fun ObesityDialogue() {
        AlertDialog.Builder(this)
            .setTitle("Health Risk")
            .setMessage("Your BMI shows a posible risk to your health, would you like some recommendations on what to do?")
            .setPositiveButton("Show recommendations", { dialog, which ->
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.nhsinform.scot/illnesses-and-conditions/nutritional/obesity/")
                )
                startActivity(browserIntent)

            })
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
}