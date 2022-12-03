package com.example.composeapp.dicoding.jetreward.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeapp.dicoding.jetreward.di.Injection
import com.example.composeapp.dicoding.jetreward.model.OrderReward
import com.example.composeapp.dicoding.jetreward.ui.common.UiState
import com.example.composeapp.dicoding.jetreward.ui.common.ViewModelFactory
import com.example.composeapp.dicoding.jetreward.ui.components.RewardItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository()))
) {

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState) {
            is UiState.Loading -> {
                viewModel.getAllRewards()
            }
            is UiState.Success -> {
                HomeContent(
                    orderReward = uiState.data,
                    modifier = modifier
                )
            }
            is UiState.Error -> {}
        }
    }

}

@Composable
fun HomeContent(
    orderReward: List<OrderReward>,
    modifier: Modifier = Modifier
) {
    
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ){
        items(orderReward){
            RewardItem(
                image = it.reward.image,
                title = it.reward.title,
                requiredPoint = it.reward.requiredPoint,
            )
        }
    }
    
}