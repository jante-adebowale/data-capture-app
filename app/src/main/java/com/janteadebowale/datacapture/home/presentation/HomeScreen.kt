package com.janteadebowale.datacapture.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.janteadebowale.datacapture.R
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.Approved
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.Captured
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.Poppins
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.Rejected
import com.janteadebowale.datacapture.home.presentation.component.FloatingActionButtonWithFabItems
import com.janteadebowale.datacapture.home.presentation.component.HomeTopBar
import org.koin.androidx.compose.koinViewModel

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/

@Composable
fun HomeRoute(
    onNavigateToSettings: () -> Unit,
    homeViewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
) {
    HomeScreen(homeViewModel.uiState) {
        when (it) {
            HomeAction.Setting -> {
                onNavigateToSettings()
            }

            else -> {
                homeViewModel.onAction(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    isLightTheme: Boolean = LocalContentColor.current.luminance() < 0.5f,
    onAction: (HomeAction) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButtonWithFabItems(
                fabExpanded = uiState.isFabExpanded,
                onClick = { index ->
                    when (index) {
                        0 -> {

                        }

                        1 -> {

                        }

                        2 -> {

                        }
                    }

                },
                onFabClick = {
                    onAction(it)
                })
        },
        topBar = {
            HomeTopBar(
                user = "Data Capture",
                pendingCount = 8,
                startContent = {
                    Image(
                        painter = painterResource(id = if (isLightTheme) R.drawable.dark_logo else R.drawable.light_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                onSettingClicked = {
                    onAction(HomeAction.Setting)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

            Spacer(modifier = Modifier.height(10.dp))

            Analytic(
                data = AnalyticData(
                    title = "Captured", value = "${23}", color = Captured
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Analytic(
                data = AnalyticData(
                    title = "Approved", value = "${10}", color = Approved
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Analytic(
                data = AnalyticData(
                    title = "Rejected", value = "${13}", color = Rejected
                )
            )

            Spacer(modifier = Modifier.height(10.dp))


            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                item {
                    Text(
                        text = stringResource(R.string.recent_capture), fontFamily = Poppins,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                items(recentCaptureList, key = { it.name }) { it ->
                    val dismissBoxState = rememberSwipeToDismissBoxState()
                    SwipeToDismissBox(
                        state = dismissBoxState,
                        enableDismissFromStartToEnd = false,
                        backgroundContent = {
                            Box(modifier = Modifier.fillMaxSize().padding(end = 10.dp)) {
                                Icon(modifier = Modifier.align(alignment = Alignment.CenterEnd), imageVector = Icons.Default.Delete,contentDescription = stringResource(R.string.swipeToDelete))
                            }
                        }) {
                        RecentListItem(recentData = it)
                        when (dismissBoxState.currentValue) {
                            SwipeToDismissBoxValue.StartToEnd -> {

                            }

                            SwipeToDismissBoxValue.EndToStart -> {
                               recentCaptureList.remove(it)
                            }

                            SwipeToDismissBoxValue.Settled -> {

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Analytic(data: AnalyticData, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(60.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = data.color),
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = data.title,
                fontFamily = Poppins,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )

            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(data.color.copy(alpha = 0.2f)), contentAlignment = Alignment.Center
            ) {

                Text(
                    text = data.value,
                    fontFamily = Poppins,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

data class AnalyticData(
    val title: String, val value: String, val color: Color,
)

data class RecentData(
    val name: String,
    val dateTime: String,
    val uploaded: Boolean = false
)

@Composable
fun RecentListItem(recentData: RecentData, modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(vertical = 5.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = modifier.weight(1f)
        ) {
            Text(
                text = recentData.name,
                fontFamily = Poppins,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp
            )

            Text(
                text = recentData.dateTime,
                fontFamily = Poppins,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            )


        }

        BadgedBox(badge = {
            Badge(
                containerColor = if (recentData.uploaded) Color.Green else Color.Red,
            )
        }) {
            Icon(
                modifier = Modifier.size(15.dp),
                imageVector = Icons.Outlined.CloudUpload,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

val recentCaptureList = mutableStateListOf(
    RecentData("Jante Adebowale", "29/09/24 08:23:12"),
    RecentData("Adetutu Adebowale", "29/09/24 08:25:12", uploaded = true),
    RecentData("Tiara Adebowale", "29/09/24 08:35:12", uploaded = true),
    RecentData("De-sire Adebowale", "29/09/24 09:11:12", uploaded = true),
)