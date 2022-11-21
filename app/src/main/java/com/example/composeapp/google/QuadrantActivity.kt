package com.example.composeapp.google

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.ui.theme.BirthdayCardTheme

class QuadrantActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun TextField(textFieldData: TextFieldData, modifier: Modifier){
    Column(
        modifier = modifier
            .background(textFieldData.color)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = textFieldData.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = textFieldData.title,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun FirstQuadrantRow(items: List<TextFieldData>, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        TextField(items[0], Modifier.weight(1f))
        TextField(items[1], Modifier.weight(1f))
    }
}

@Composable
fun SecondQuadrantRow(items: List<TextFieldData>, modifier: Modifier) {
    Row(
        modifier = modifier
    ) {
        TextField(items[2], Modifier.weight(1f))
        TextField(items[3], Modifier.weight(1f))
    }
}

@Composable
fun TextQuadrant(items: List<TextFieldData>){
   Column(modifier = Modifier.fillMaxWidth()) {
       FirstQuadrantRow(items, Modifier.weight(1f))
       SecondQuadrantRow(items, Modifier.weight(1f))
   }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun QuadrantPreview() {
    BirthdayCardTheme {
        TextQuadrant(listOf(
            TextFieldData("Text composable", "Displays text and follows Material Design guidelines.", Color.Green),
            TextFieldData("Image composable", "Creates a composable that lays out and draws a given Painter class object.", Color.Yellow),
            TextFieldData("Row composable", "A layout composable that places its children in a horizontal sequence.", Color.Cyan),
            TextFieldData("Column composable", "A layout composable that places its children in a vertical sequence.", Color.LightGray)
        ))
    }
}

data class TextFieldData(
    val title: String,
    val detail: String,
    val color: Color,
)
