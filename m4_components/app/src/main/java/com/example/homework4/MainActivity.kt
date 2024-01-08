package com.example.homework4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.homework4.databinding.ActivityMainBinding
import com.google.android.material.checkbox.MaterialCheckBox
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val count = Random.nextInt(101)                 // Выставление числового прогресса и полосы прогресса
        binding.progressBar.progress = count
        binding.progressNumbers.text = "$count/100"

        binding.buttonSave.isEnabled = false

        val mapOfEnable =
            mutableMapOf<String, Boolean>(    // Мапка для проверки условий для кнопки "сохранить"
                "Имя" to false,
                "Номер" to false,
                "Пол" to false,
                "Уведомления" to false
            )

        var checkBoxCounter = 0                            // Счетчик для доступности "сохранить" от чекбоксов

        binding.switchForNotice.setOnCheckedChangeListener { _, isChecked ->    // Выставление переключателя уведомлений
            if (isChecked) {
                binding.checkBox1.isEnabled = true
                binding.checkBox1.setTextColor(getColor(R.color.black))
                binding.checkBox1.checkedState = MaterialCheckBox.STATE_CHECKED
                binding.checkBox2.isEnabled = true
                binding.checkBox2.setTextColor(getColor(R.color.black))
                binding.checkBox2.checkedState = MaterialCheckBox.STATE_CHECKED
            } else {
                binding.checkBox1.isEnabled = false
                binding.checkBox1.checkedState = MaterialCheckBox.STATE_UNCHECKED
                binding.checkBox1.setTextColor(getColor(R.color.light_black))
                binding.checkBox2.isEnabled = false
                binding.checkBox2.setTextColor(getColor(R.color.light_black))
                binding.checkBox2.checkedState = MaterialCheckBox.STATE_UNCHECKED
                mapOfEnable["Уведомления"] = true
            }
            binding.buttonSave.isEnabled = saveButtonListener(mapOfEnable)
        }

        binding.checkBox1.setOnCheckedChangeListener { _, isChecked ->    // Листнер первого чекбокса
            if (isChecked && binding.switchForNotice.isChecked) {
                checkBoxCounter++
                mapOfEnable["Уведомления"] = true
            } else {
                checkBoxCounter--
                if (checkBoxCounter == 0) {
                    mapOfEnable["Уведомления"] = false
                }
            }
            binding.buttonSave.isEnabled = saveButtonListener(mapOfEnable)
        }

        binding.checkBox2.setOnCheckedChangeListener { _, isChecked ->    // Листнер второго чекбокса
            if (isChecked && binding.switchForNotice.isChecked) {
                checkBoxCounter++
                mapOfEnable["Уведомления"] = true
            } else {
                checkBoxCounter--
                if (checkBoxCounter == 0) {
                    mapOfEnable["Уведомления"] = false
                }
            }
            binding.buttonSave.isEnabled = saveButtonListener(mapOfEnable)
        }

        binding.inputName.doOnTextChanged { text, _, _, _ ->              // Листнер ввода имени
            if (text.isNullOrEmpty() || text.length > binding.nameInputField.counterMaxLength) {
                mapOfEnable["Имя"] = false
            } else {
                mapOfEnable["Имя"] = true
            }
            binding.buttonSave.isEnabled = saveButtonListener(mapOfEnable)
        }

        binding.inputNumber.doOnTextChanged { text, _, _, _ ->           // Листнер ввода номера
            if (text!!.length != 10) {
                mapOfEnable["Номер"] = false
            } else {
                mapOfEnable["Номер"] = true
            }
            binding.buttonSave.isEnabled = saveButtonListener(mapOfEnable)
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->  // Листнер радиогруппы
            when (checkedId) {
                R.id.radioButton1 -> mapOfEnable["Пол"] = true
                R.id.radioButton2 -> mapOfEnable["Пол"] = true
                else -> mapOfEnable["Пол"] = false
            }
            binding.buttonSave.isEnabled = saveButtonListener(mapOfEnable)
        }

        binding.buttonSave.setOnClickListener {                   // Листнер для кнопки "сохранить" и отправка тоста
            Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_SHORT).show()
        }

    }

    fun saveButtonListener(mapOfBoolean: Map<String, Boolean>): Boolean {  // Функция для сверки условий доступности кнопки "сохранить"
        var isTrue = true
        for (boolean in mapOfBoolean.values) {
            if (boolean == false) {
                isTrue = false
                break
            }
        }
        return isTrue
    }

}