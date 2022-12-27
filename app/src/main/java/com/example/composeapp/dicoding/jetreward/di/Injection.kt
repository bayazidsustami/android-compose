package com.example.composeapp.dicoding.jetreward.di

import com.example.composeapp.dicoding.jetreward.data.RewardRepository

object Injection {
    fun provideRepository(): RewardRepository {
        return RewardRepository.getInstance()
    }
}