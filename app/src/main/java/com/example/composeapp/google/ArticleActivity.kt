package com.example.composeapp.google

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.R
import com.example.composeapp.ui.theme.BirthdayCardTheme

class ArticleActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayCardTheme {
                DetailArticle()
            }
        }
    }
}

@Composable
fun TitleArticle() {
    Text(
        text = stringResource(R.string.title_section),
        fontSize = 24.sp,
        modifier = Modifier
            .padding(16.dp)
    )
}

@Composable
fun FirstSectionArticle() {
    Text(
        text = stringResource(R.string.first_section),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        textAlign = TextAlign.Justify
    )
}

@Composable
fun SecondSectionArticle() {
    Text(
        text = stringResource(R.string.second_section),
        modifier = Modifier.padding(16.dp),
        textAlign = TextAlign.Justify
    )
}

@Composable
fun ImageHeader(){
    val image = painterResource(R.drawable.bg_compose_background)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DetailArticle() {
    Column {
        ImageHeader()
        TitleArticle()
        FirstSectionArticle()
        SecondSectionArticle()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ArticlePagePreview() {
    BirthdayCardTheme {
        DetailArticle()
    }
}
