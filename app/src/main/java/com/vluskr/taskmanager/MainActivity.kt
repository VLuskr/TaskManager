package com.vluskr.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    val listTimer = mutableListOf<TimerItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val task1 = TimerItem("testTasktestTasktestTasktestTasktestTask", this)
            TaskItemScreen(task = task1)

        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItemScreen(task: TimerItem) {

    val totalTime = remember {
        mutableStateOf(task.infoTotal())
    }
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .border(width = 2.dp, Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .combinedClickable(
                onClick = {
                    totalTime.value = task.press()
                },
                onLongClick = {

                    totalTime.value = task.clear()
                }
            )

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = task.name(),
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(8.dp),
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = totalTime.value,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(8.dp),

                textAlign = TextAlign.Center

            )
        }
    }
}

//@Composable
//fun YesNo(str: String) {
//
//    Box(modifier = Modifier
//        .padding(16.dp)
//        .fillMaxWidth()
//        .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
//        .border(width = 2.dp, Color.DarkGray, shape = RoundedCornerShape(12.dp))
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//        ) {
//            Text(str)
//            Row {
//                Text("Yes")
//                Text("No")
//            }
//        }
//    }
//
//    return false
//}
//
//@Preview
//@Composable
//fun YesNoPreview() {
//    YesNo("Clear Timer this Task?")
//}