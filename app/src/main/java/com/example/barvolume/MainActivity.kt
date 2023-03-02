package com.example.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // initiating variables that
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnResult: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // assigning value to variables
        // will find the 'view' object by 'id'
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnResult = findViewById(R.id.btn_result)
        tvResult = findViewById(R.id.tv_result)

        // this need to contract Interface 'View.OnClickListener'
        btnResult.setOnClickListener(this)

    }

    // overriding from 'View.OnClickListener'
    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_result) {
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            var isEmptyFields = false

            isEmptyFields = emptyFieldCheck(edtLength, inputLength)
            isEmptyFields = emptyFieldCheck(edtWidth, inputWidth)
            isEmptyFields = emptyFieldCheck(edtHeight, inputHeight)

            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toInt().toString()
            }
        }
    }
}

fun emptyFieldCheck(view: EditText,field: String): Boolean {
    if (field.isEmpty()) {
        view.error = "This field cannot be empty"
        return true
    } else {
        return false
    }
}