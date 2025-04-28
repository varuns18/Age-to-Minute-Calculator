package com.ramphal.agetominutecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var date: TextView? = null
    private var minute: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        date = findViewById(R.id.date)
        minute = findViewById(R.id.finalValue)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }


    }

    @SuppressLint("RestrictedApi")
    private fun clickDatePicker(){

        val calenderConstraint = CalendarConstraints.Builder()
            .setValidator(
                DateValidatorPointBackward.before(System.currentTimeMillis() - 86400000)
            )
            .build()

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .setCalendarConstraints(calenderConstraint)
            .build()

        datePicker.show(supportFragmentManager, "MATERIAL_DATE_PICKER")

        datePicker.addOnPositiveButtonClickListener { selection: Long? ->
            selection?.let { epochMillis ->
                val pickedDate = Date(epochMillis)
                val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                val formatted = formatter.format(pickedDate)
                date?.text = formatted
                val currentDate = formatter.parse(formatter.format(System.currentTimeMillis()))
                currentDate?.let {
                    val difference = (currentDate.time - pickedDate.time) / 60000
                    minute?.text = difference.toString()
                }

            }
        }


    }


























}