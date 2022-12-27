package com.example.composeapp.dicoding.jetreward.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.composeapp.R
import com.example.composeapp.dicoding.jetreward.model.OrderReward
import com.example.composeapp.dicoding.jetreward.model.Reward
import com.example.composeapp.dicoding.jetreward.ui.theme.JetRewardAppTheme
import com.example.composeapp.dicoding.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailContentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderReward = OrderReward(
        reward = Reward(4,R.drawable.reward_4, "Jaket Hoodie Dicoding", 7500),
        count = 0
    )

    @Before
    fun setup() {
        composeTestRule.setContent { 
            JetRewardAppTheme {
                DetailContent(
                    fakeOrderReward.reward.image,
                    fakeOrderReward.reward.title,
                    fakeOrderReward.reward.requiredPoint,
                    fakeOrderReward.count,
                    onBackClick = {},
                    onAddToCart = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelIfExists")
    }

    @Test
    fun detailContent_isDisplayed() {
        with(composeTestRule){
            onNodeWithText(fakeOrderReward.reward.title).assertIsDisplayed()
            onNodeWithStringId(R.string.required_point, fakeOrderReward.reward.requiredPoint).assertIsDisplayed()
        }
    }

    @Test
    fun increaseProduct_buttonEnabled(){
        with(composeTestRule) {
            onNodeWithContentDescription("Order Button").assertIsNotEnabled()
            onNodeWithStringId(R.string.plus_symbol).performClick()
            onNodeWithContentDescription("Order Button").assertIsEnabled()
        }
    }

    @Test
    fun increaseProduct_correctCounter() {
        with(composeTestRule) {
            onNodeWithStringId(R.string.plus_symbol).performClick().performClick()
            onNodeWithTag("count").assert(hasText("2"))
        }
    }

    @Test
    fun decreaseProduct_stillZero() {
        with(composeTestRule) {
            onNodeWithStringId(R.string.minus_symbol).performClick()
            onNodeWithTag("count").assert(hasText("0"))
        }
    }


}