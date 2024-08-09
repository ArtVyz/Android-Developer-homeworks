package com.example.homewrk16.domain

import com.example.homewrk16.data.UsefulActivitiesRepository
import com.example.homewrk16.entity.UsefulActivity
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor (
    private val repository: UsefulActivitiesRepository
) {
    suspend fun execute(): UsefulActivity {
        return repository.getUsefulActivity()
    }
}