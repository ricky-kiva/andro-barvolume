package com.example.barvolume

// kotlin has 'code completion' that 'auto import'
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.barvolume.databinding.ActivityMainBinding

// appCompatActivity() is the superclass for 'activity'-es
class MainActivity : AppCompatActivity(), View.OnClickListener {

    // initiating variables that
    // 'lateinit' means the variable will be initialized later

    // this is not used anymore because we use 'ViewBinding'
    /* private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnResult: Button
    private lateinit var tvResult: TextView */

    private lateinit var binding: ActivityMainBinding

    // this is the 'main method' to create an 'activity' where an activity is 'created'
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 'activity layout' is from 'activity_main.xml'
        // this not used anymore because we use ViewBinding
        /* setContentView(R.layout.activity_main) */

        // It will inflates the layout of 'MainActivity' & 'returns' the 'binding class'
        // it takes 'layoutInflater' as an 'argument' as it will 'inflate' the 'layout XML file'
        // The 'binding class' has 'references' to 'all views' 'inside' the 'layout'
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // assigning value to variables
        // 'id' is created on 'R' file 'deep inside' 'app' folder
        // will find the 'view' (EditText) object by 'id'

        // this is not used anymore because we use 'ViewBinding'
        /* edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnResult = findViewById(R.id.btn_result)
        tvResult = findViewById(R.id.tv_result) */

        // need to contract for Interface 'View.OnClickListener'
        // 'this' referers the current 'activity' object
        binding.btnResult.setOnClickListener(this)

        /* It could also be written 'without adding Interface' like:

        btnResult.setOnClickListener(View.OnClickListener {
           // the action when the button is clicked
        })

        OR SIMPLY

        btnResult.setOnClickListener {
           // the action when the button is clicked
        }

        */

        // calling the 'saved state' if there is any
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            binding.tvResult.text = result
        }

    }

    // overriding from 'View.OnClickListener'
    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_result) {
            val inputLength = binding.edtLength.text.toString().trim()
            val inputWidth = binding.edtWidth.text.toString().trim()
            val inputHeight = binding.edtHeight.text.toString().trim()

            var isEmptyFields = false

            isEmptyFields = emptyFieldCheck(binding.edtLength, inputLength)
            isEmptyFields = emptyFieldCheck(binding.edtWidth, inputWidth)
            isEmptyFields = emptyFieldCheck(binding.edtHeight, inputHeight)

            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                binding.tvResult.text = volume.toInt().toString()
            }

        }
    }

    // adding 'companion object' for 'onSaveInstanceState()'
    companion object {
        private const val STATE_RESULT = "state_result"
    }

    // adding 'onSaveInstanceState()' from 'AppCompatActivity()' interface
    // to enable 'saving state' when 'rotated'
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 'putString()' has concept of 'key-value'
        outState.putString(STATE_RESULT, binding.tvResult.text.toString())
    }
}

// function to check if the 'input field' is 'empty'
fun emptyFieldCheck(view: EditText,field: String): Boolean {
    return if (field.isEmpty()) {
        view.error = "This field cannot be empty"
        true
    } else {
        false
    }
}