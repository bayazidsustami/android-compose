package com.example.composeapp.dicoding.calculator.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.composeapp.R
import com.example.composeapp.dicoding.calculator.ui.theme.CalculatorAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        composeTestRule.setContent {
            CalculatorAppTheme {
                CalculatorApp()
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun calculate_area_of_rectangle_correct() {
        with(composeTestRule){
            onNodeWithText(activity.getString(R.string.enter_length)).performTextInput("3")
            onNodeWithText(activity.getString(R.string.enter_width)).performTextInput("4")
            onNodeWithText(activity.getString(R.string.count)).performClick()
            onNodeWithText(activity.getString(R.string.count), useUnmergedTree = true).assertHasNoClickAction()
            onNodeWithText(activity.getString(R.string.result, 12.0)).assertExists()
        }
    }

    @Test
    fun wrong_input_not_calculated() {
        with(composeTestRule){
            onNodeWithText(activity.getString(R.string.enter_length)).performTextInput("..3")
            onNodeWithText(activity.getString(R.string.enter_width)).performTextInput("4")
            onNodeWithText(activity.getString(R.string.count)).performClick()
            onNodeWithText(activity.getString(R.string.result, 0.0)).assertExists()
        }
    }
}