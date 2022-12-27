package com.example.composeapp.dicoding.jetreward.ui.screen.cart

import com.example.composeapp.dicoding.jetreward.model.OrderReward

data class CartState(
    val orderReward: List<OrderReward>,
    val totalRequiredPoint: Int
)