package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.ui.theme.BirthdayCardTheme

class TaskManagerActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayCardTheme {
                TaskCompletedPage()
            }
        }
    }
}

@Composable
fun TitleTask() {
    Text(
        text = stringResource(R.string.all_task_completed),
        fontSize = 24.sp,
        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun DetailTitleTask() {
    Text(
        text = stringResource(R.string.detail_task_completed),
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}


@Composable
fun ImageTaskComplete() {
    val image = painterResource(R.drawable.ic_task_completed)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun TaskCompletedPage() {
    Column(
        modifier = Modifier
            .wrapContentWidth(Alignment.CenterHorizontally )
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        ImageTaskComplete()
        TitleTask()
        DetailTitleTask()
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun TaskPreview() {
    BirthdayCardTheme {
        TaskCompletedPage()
    }
}
