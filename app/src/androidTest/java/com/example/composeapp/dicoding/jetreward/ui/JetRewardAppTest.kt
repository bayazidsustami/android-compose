package com.example.composeapp.dicoding.jetreward.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.composeapp.R
import com.example.composeapp.dicoding.assertCurrentRouteName
import com.example.composeapp.dicoding.jetreward.model.FakeRewardDataSource
import com.example.composeapp.dicoding.jetreward.ui.navigation.Screen
import com.example.composeapp.dicoding.jetreward.ui.theme.JetRewardAppTheme
import com.example.composeapp.dicoding.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class JetRewardAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            JetRewardAppTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                JetRewardApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        with(composeTestRule) {
            onNodeWithTag("RewardList").performScrollToIndex(10)
            onNodeWithText(FakeRewardDataSource.dummyRewards[10].title).performClick()
            navController.assertCurrentRouteName(Screen.DetailReward.route)
            onNodeWithText(FakeRewardDataSource.dummyRewards[10].title).assertIsDisplayed()
        }
    }

    @Test
    fun navHost_bottomNavigation_working() {
        with(composeTestRule) {
            onNodeWithStringId(R.string.menu_cart).performClick()
            navController.assertCurrentRouteName(Screen.Cart.route)
            onNodeWithStringId(R.string.menu_profile).performClick()
            navController.assertCurrentRouteName(Screen.Profile.route)
            onNodeWithStringId(R.string.menu_home).performClick()
            navController.assertCurrentRouteName(Screen.Home.route)
        }
    }

    @Test
    fun navHost_clickItem_navigatesBack() {
        with(composeTestRule) {
            onNodeWithTag("RewardList").performScrollToIndex(10)
            onNodeWithText(FakeRewardDataSource.dummyRewards[10].title).performClick()
            navController.assertCurrentRouteName(Screen.DetailReward.route)
            onNodeWithContentDescription(activity.getString(R.string.back)).performClick()
            navController.assertCurrentRouteName(Screen.Home.route)
        }
    }

    @Test
    fun navHost_checkout_rightBackStack() {
        with(composeTestRule) {
            onNodeWithText(FakeRewardDataSource.dummyRewards[4].title).performClick()
            navController.assertCurrentRouteName(Screen.DetailReward.route)
            onNodeWithStringId(R.string.plus_symbol).performClick()
            onNodeWithContentDescription("Order Button").performClick()
            navController.assertCurrentRouteName(Screen.Cart.route)
            onNodeWithStringId(R.string.menu_home).performClick()
            navController.assertCurrentRouteName(Screen.Home.route)
        }
    }
}