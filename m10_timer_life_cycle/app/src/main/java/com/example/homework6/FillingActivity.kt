package com.example.homework6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework6.databinding.ActivityFillingBinding

class FillingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFillingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val launchIntent: Intent = intent
        val patronymicVisibility: String? = launchIntent.getStringExtra("text")

        if (patronymicVisibility == "1") {
            binding.patronymicInput.visibility = View.VISIBLE
        } else {
            binding.patronymicInput.visibility = View.INVISIBLE
        }

        binding.saveButton.setOnClickListener{
            val fullName: Name = if ( binding.patronymicInput.visibility == View.VISIBLE) {
                Name (
                    secondName = binding.secondName.text.toString(),
                    firstName = binding.firstName.text.toString(),
                    patronymic = binding.patronymic.text.toString()
                )
            } else {
                Name (
                    secondName = binding.secondName.text.toString(),
                    firstName = binding.firstName.text.toString(),
                )
            }
            val data = Intent()
            data.putExtra (FIO,fullName)
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    companion object {
        const val FIO = "FIO"
    }
}