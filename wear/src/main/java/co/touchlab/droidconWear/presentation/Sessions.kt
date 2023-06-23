package co.touchlab.droidconWear.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.CurvedLayout
import androidx.wear.compose.foundation.CurvedModifier
import androidx.wear.compose.foundation.CurvedTextStyle
import androidx.wear.compose.foundation.background
import androidx.wear.compose.foundation.curvedColumn
import androidx.wear.compose.foundation.curvedRow
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyColumnDefaults
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.foundation.padding
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.TimeTextDefaults
import androidx.wear.compose.material.TitleCard
import androidx.wear.compose.material.curvedText
import androidx.wear.compose.material.placeholder
import androidx.wear.compose.material.placeholderShimmer
import androidx.wear.compose.material.rememberPlaceholderState
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.rotaryinput.rotaryWithFling
import com.google.android.horologist.compose.rotaryinput.rotaryWithScroll
import kotlinx.coroutines.delay

@Composable
fun CurvedSample() {
    CurvedLayout() {
        curvedColumn {
            curvedRow {
                curvedText(
                    "Left row text",
                    CurvedModifier.padding(angular = 8.dp).background(Color.Blue)
                )
                curvedText("Right row text", CurvedModifier.background(Color.Gray))
            }
            curvedText("Bottom column text", CurvedModifier.background(Color.Red))
        }
    }
}

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun SessionsList(state: ScalingLazyListState) {
    var loading: Boolean by remember { mutableStateOf(true) }
    // val state: ScalingLazyListState = rememberScalingLazyListState()
    val focusRequester: FocusRequester = remember{ FocusRequester() }
    ScalingLazyColumn(
        state = state,
        modifier = Modifier.rotaryWithFling(focusRequester, state)
        // flingBehavior = ScalingLazyColumnDefaults.snapFlingBehavior(state = state),
        // scalingParams = ScalingLazyColumnDefaults.scalingParams(
        //     edgeScale = 0.5f,
        //     edgeAlpha = 0.2f,
        //     minTransitionArea = 0.7f,
        //     maxTransitionArea = 0.8f)
    ) {
        item {
            ListHeader {
                Text("Notifications")
            }
        }
        item {
            Button(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                )
            }
        }
        item {
            SessionCard(
                if (loading) {
                    null
                } else
                    WearSession(
                        "Session title",
                        "Speaker name",
                        "at Lovelas",
                        "10:20"
                    ),
                loading = loading
            )
        }
        items(20) {
            SessionCard(
                WearSession(
                    "Session title",
                    "Speaker name",
                    "at Lovelas",
                    "10:20"
                )
            )
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        // delay(5000)
        // loading = false
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun SessionCard(
    session: WearSession?,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
) {
    val placeholderState = rememberPlaceholderState { !loading }

    TitleCard(
        modifier = modifier,
        onClick = { },
        title = {
            Text(
                text = session?.title ?: "",
                modifier = Modifier
                    .then(
                        if (placeholderState.isShowContent)
                            Modifier
                        else
                            Modifier.width(60.dp)
                    )
                    .placeholder(placeholderState)
            )
        },
        time = { Text(text = session?.startTime ?: "") }
    ) {
        Column {
            Text(session?.room ?: "")
            Spacer(modifier = Modifier.height(2.dp))
            Text(session?.speakers ?: "")
        }
    }
    if (!placeholderState.isShowContent) {
        LaunchedEffect(placeholderState) {
            placeholderState.startPlaceholderAnimation()
        }
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun LoadingCard(
    modifier: Modifier = Modifier,
) {

    val placeholderState = rememberPlaceholderState { false }

    TitleCard(
        modifier = modifier,
        onClick = { },
        title = {
            Text(
                text = "",
                modifier = Modifier
                    .width(90.dp)
                    .placeholderShimmer(placeholderState)
                    .placeholder(
                        placeholderState,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)
                    )
            )
        },
        time = {
            Text(
                text = "",
                modifier = Modifier
                    .width(30.dp)
                    .placeholderShimmer(placeholderState)
                    .placeholder(
                        placeholderState,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)
                    )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .placeholderShimmer(placeholderState)
                .placeholder(placeholderState)
        ) {
            Text(
                "", modifier = Modifier
                    .width(60.dp)
                    .placeholderShimmer(placeholderState)
                    .placeholder(
                        placeholderState,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)
                    )
            )
        }
    }

    if (!placeholderState.isShowContent) {
        LaunchedEffect(placeholderState) {
            placeholderState.startPlaceholderAnimation()
        }
    }
}

@Preview(device = Devices.WEAR_OS_LARGE_ROUND, showSystemUi = true)
@Composable
fun SessionCardPreview() {
    Box() {
        SessionCard(
            modifier = Modifier.align(Alignment.Center),
            session = WearSession(
                "Wear Compose",
                "by Mikhail Kulaha",
                "at Lovelas",
                "10:20am"
            )
        )
    }
}

@Preview(device = Devices.WEAR_OS_LARGE_ROUND, showSystemUi = true)
@Composable
fun TimeTextWithStatus() {
    val leadingTextStyle = TimeTextDefaults.timeTextStyle(color = MaterialTheme.colors.primary)

    TimeText(
        startLinearContent = {
            Text(
                "In 1 hour",
                style = leadingTextStyle
            )
        },
        startCurvedContent = {
            curvedText(
                "In 1 hour",
                style = CurvedTextStyle(leadingTextStyle)
            )
        },
    )
}

data class WearSession(
    val title: String,
    val speakers: String,
    val room: String,
    val startTime: String,
)