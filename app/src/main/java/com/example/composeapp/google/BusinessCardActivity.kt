package com.example.composeapp.google

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.R
import com.example.composeapp.ui.theme.BirthdayCardTheme

class BusinessCardActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayCardTheme {

            }
        }
    }
}

@Composable
fun NameSection(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 128.dp, end = 128.dp)
        )
        Text(
            text = "Full Name",
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = "Title",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun PhoneItem(icon: ImageVector, title: String){
    Column {
        Divider(color = Color.Red, thickness = 1.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.padding(start = 24.dp))
            Text(text = title, modifier = Modifier.padding(start = 16.dp))
        }
    }
}

@Composable
fun BusinessCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        NameSection(Modifier.align(Alignment.Center))
        LazyColumn(
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            items(listOf(
                PhoneItemData(Icons.Rounded.Person, "@baya"),
                PhoneItemData(Icons.Rounded.Email, "@baya.com"),
                PhoneItemData(Icons.Rounded.Share, "@baya.com.com"),
            )){ PhoneItem(it.icon, it.title) }
        }
    }
}

data class PhoneItemData(val icon: ImageVector, val title: String)

@Preview(
    showBackground = true,
)
@Composable
fun BusinessCardPreview() {
    BirthdayCardTheme {
        BusinessCard()
    }
}
