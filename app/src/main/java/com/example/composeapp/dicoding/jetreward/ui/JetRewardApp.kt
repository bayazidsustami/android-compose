package com.example.composeapp.dicoding.jetreward.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeapp.R
import com.example.composeapp.dicoding.jetreward.ui.navigation.NavigationItem
import com.example.composeapp.dicoding.jetreward.ui.navigation.Screen
import com.example.composeapp.dicoding.jetreward.ui.screen.cart.CartScreen
import com.example.composeapp.dicoding.jetreward.ui.screen.detail.DetailScreen
import com.example.composeapp.dicoding.jetreward.ui.screen.home.HomeScreen
import com.example.composeapp.dicoding.jetreward.ui.screen.profile.ProfileScreen
import com.example.composeapp.dicoding.jetreward.ui.theme.JetRewardAppTheme


@Composable
fun JetRewardApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (currentRoute != Screen.DetailReward.route) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                HomeScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.DetailReward.createRoute(it))
                    }
                )
            }
            composable(Screen.Cart.route){
                CartScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
            composable(
                route = Screen.DetailReward.route,
                arguments = listOf(navArgument("rewardId"){ type = NavType.LongType})
            ){
                val id = it.arguments?.getLong("rewardId") ?: -1L
                DetailScreen(
                    rewardId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(modifier) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.menu_cart),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart,
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            )
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentState = navBackStackEntry?.destination?.route

        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = {
                         Text(item.title)
                    },
                    selected = currentState == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetRewardPreview() {
    JetRewardAppTheme {
        JetRewardApp()
    }
}