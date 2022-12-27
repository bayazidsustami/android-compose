package com.example.composeapp.dicoding.jetreward.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.dicoding.jetreward.data.RewardRepository
import com.example.composeapp.dicoding.jetreward.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel constructor(
    private val repository: RewardRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>> get() = _uiState

    fun getAddedReward() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedRewards()
                .collect { orderReward ->
                    val totalRequiredPoint = orderReward.sumOf { it.reward.requiredPoint * it.count }
                    _uiState.value = UiState.Success(CartState(orderReward, totalRequiredPoint))
                }
        }
    }

    fun updateOrderDetail(rewardId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderReward(rewardId, count)
                .collect { isUpdate ->
                    if (isUpdate) {
                        getAddedReward()
                    }
                }
        }
    }
}