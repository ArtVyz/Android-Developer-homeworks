package com.example.homework6

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Name (
    val secondName: String,
    val firstName: String,
    val patronymic: String = ""
) : Parcelable