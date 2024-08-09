package com.example.homewrk16.data

import com.example.homewrk16.entity.Quote
import com.example.homewrk16.entity.UsefulActivity
import javax.inject.Inject

data class UsefulActivityDto @Inject constructor(
    override val quote: Quote
) : UsefulActivity