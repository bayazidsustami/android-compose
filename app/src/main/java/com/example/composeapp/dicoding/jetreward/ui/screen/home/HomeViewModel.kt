package com.example.composeapp.dicoding.jetreward.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.dicoding.jetreward.data.RewardRepository
import com.example.composeapp.dicoding.jetreward.model.OrderReward
import com.example.composeapp.dicoding.jetreward.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val repository: RewardRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<OrderReward>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderReward>>> get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllRewards()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }.collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}