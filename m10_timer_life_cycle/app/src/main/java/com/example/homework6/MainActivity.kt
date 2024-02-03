package com.example.homework6

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.IntentCompat
import com.example.homework6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val authLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    val intentData: Intent? = result.data
                    val name: Name = intentData?.let {
                        IntentCompat.getParcelableExtra(it, FillingActivity.FIO, Name::class.java)
                    } ?: return@registerForActivityResult
                    binding.resultFIO.text = getString(R.string.full_name,name.firstName,name.secondName,name.patronymic)
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
            val intent = (Intent(this, FillingActivity::class.java))
            intent.putExtra("text", havePatronymic)
            startActivity(intent)
        }
    }
}