package com.example.composeapp.dicoding.jetreward.data

import com.example.composeapp.dicoding.jetreward.model.FakeRewardDataSource
import com.example.composeapp.dicoding.jetreward.model.OrderReward
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class RewardRepository {
    private val orderRewards = mutableListOf<OrderReward>()

    init {
        if (orderRewards.isEmpty()){
            FakeRewardDataSource.dummyRewards.forEach {
                orderRewards.add(OrderReward(it, 0))
            }
        }
    }

    fun getAllRewards(): Flow<List<OrderReward>> {
        return flowOf(orderRewards)
    }

    fun getOrderRewardById(rewardId: Long): OrderReward {
        return orderRewards.first {
            it.reward.id == rewardId
        }
    }

    fun updateOrderReward(rewardId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderRewards.indexOfFirst { it.reward.id == rewardId }
        val result = if (index >= 0) {
            val orderReward = orderRewards[index]
            orderRewards[index] = orderReward.copy(reward = orderReward.reward, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedRewards(): Flow<List<OrderReward>> {
        return getAllRewards()
            .map {
                it.filter { orderReward ->
                    orderReward.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var INSTANCE: RewardRepository? = null

        fun getInstance(): RewardRepository =
            INSTANCE ?: synchronized(this) {
                RewardRepository().apply {
                    INSTANCE = this
                }
            }
    }
}