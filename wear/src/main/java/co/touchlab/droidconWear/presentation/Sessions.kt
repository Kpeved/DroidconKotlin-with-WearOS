package co.touchlab.droidconWear.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TitleCard

@Composable
fun SessionsList(state: ScalingLazyListState) {
    ScalingLazyColumn(state = state) {
        item{
            ListHeader {
                Text("Sessions")
            }
        }
        items(20){
            SessionCard(
                WearSession(
                    "Wear Compose",
 "Mikhail Kulaha",
                    "at Lovelas",
                    "10:20 am"
                )
            )
        }
    }
}

@Composable
fun SessionCard(session: WearSession) {
    TitleCard(
        onClick = { },
        title = { Text(text = session.title) },
        time = {Text(text = session.startTime)}
    ) {
        Column {
            Text(session.room)
            Spacer(modifier = Modifier.height(8.dp))
            Text(session.speakers)
        }
    }
}

@Preview
@Composable
fun SessionCardPreview() {
    SessionCard(
        WearSession(
            "Wear Compose",
            "Mikhail Kulaha",
            "at Lovelas",
            "10:20 am"
        )
    )
}

data class WearSession(
    val title: String,
    val speakers: String,
    val room: String,
    val startTime: String
)