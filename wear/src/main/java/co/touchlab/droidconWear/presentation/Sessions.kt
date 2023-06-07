package co.touchlab.droidconWear.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
                Session(
                    "Wear Compose",
                    "Creating your first wear app"
                )
            )
        }
    }
}

@Composable
fun SessionCard(session: Session) {
    TitleCard(
        onClick = { },
        title = { Text(text = session.title) }) {
        Text(session.description)
    }
}

@Preview
@Composable
fun SessionCardPreview() {
    SessionCard(
        Session(
            "Wear Compose",
            "Creating your first wear app"
        )
    )
}

data class Session(
    val title: String,
    val description: String,
)