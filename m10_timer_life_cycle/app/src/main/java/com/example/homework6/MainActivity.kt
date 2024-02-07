package com.example.homework6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.homework6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intentData = result.data
                val name =
                    intentData?.let { IntentCompat.getParcelableExtra(it,FillingActivity.FIO,Name::class.java) }
                if (name != null) {
                    Log.d("MyLog", "My name is ${name.firstName} ${name.secondName} ${name.patronymic}")
                    val text = name.firstName + name.secondName + name.patronymic
                    Log.d("MyLog", "Text is $text")
                    binding.text.text = text
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var havePatronymic = ""
        binding.checkBoxPatronymic.setOnCheckedChangeListener { _, isChecked ->
            havePatronymic = if (isChecked) {
                "1"
            } else {
                "0"
            }
        }

        binding.introduceButton.setOnClickListener {
            launcher.launch(Intent(this, FillingActivity::class.java))
//            putExtra("text", havePatronymic)  пока убрал, не знаю как передать с лаунчером
        }
    }
}