package com.example.composeapp.dicoding.jetcoffe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.R
import com.example.composeapp.dicoding.jetcoffe.model.dummyCategory
import com.example.composeapp.dicoding.jetcoffe.ui.components.CategoryItem
import com.example.composeapp.dicoding.jetcoffe.ui.components.SearchBar
import com.example.composeapp.dicoding.jetcoffe.ui.theme.JetCoffeeTheme

class JetCoffeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    JetCoffeeApp()
                }
            }
        }
    }
}

@Composable
fun JetCoffeeApp() {
    Column {
        Banner()
        CategoryRow()
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentScale = ContentScale.Crop,
            contentDescription = "Banner Image",
            modifier = Modifier.height(160.dp)
        )
        SearchBar()
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(dummyCategory, key = { it.textCategory}){
            CategoryItem(it)
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
    JetCoffeeTheme {
        JetCoffeeApp()
    }
}