package co.touchlab.droidconWear.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.HorizontalPageIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PageIndicatorStyle.Companion.Curved
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition.Companion.TopAndBottom
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import androidx.wear.compose.navigation.rememberSwipeDismissableNavHostState
import co.touchlab.droidconWear.R
import co.touchlab.droidconWear.presentation.theme.DroidconTheme
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.navscaffold.WearNavScaffold
import com.google.android.horologist.compose.navscaffold.scrollable

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun WearApp() {
    DroidconTheme {
        // val navController = rememberSwipeDismissableNavController()
        // Scaffold(
        //     vignette = { Vignette(TopAndBottom) },
        //     positionIndicator = { PositionIndicator(scrollState) },
        //     timeText = { TimeText() },
        //     pageIndicator = { HorizontalPageIndicator(pageIndicatorState)}
        // ) {
            // Main content
        // }
        val swipeToDismissBoxState = rememberSwipeToDismissBoxState()
        val navController = rememberSwipeDismissableNavController()
        // SwipeDismissableNavHost(
        //     navController = navController,
        //     state = rememberSwipeDismissableNavHostState(swipeToDismissBoxState),
        //     startDestination = "Start"
        // ){
        //     composable("Start"){
        //
        //     }
        // }
        WearNavScaffold(
            navController = navController,
            timeText = {  },
            startDestination = "start"
        ) {
            scrollable("start") {
                SessionsList(it.scrollableState)
                // CurvedSample()
            }
        }
    }
}

@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.hello_world, greetingName)
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}

@Preview(device = Devices.WEAR_OS_RECT, showSystemUi = true)
@Composable
fun DefaultPreviewRect() {
    WearApp()
}