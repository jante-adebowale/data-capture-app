package com.janteadebowale.datacapture.home.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCScreenPreview
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.Approved
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.Captured
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.DataCaptureTheme
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.Rejected
import com.janteadebowale.datacapture.home.presentation.HomeAction

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
fun FloatingActionButtonWithFabItems(
    fabExpanded: Boolean,
    onClick: (Int) -> Unit,
    onFabClick: (HomeAction) -> Unit = {},
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = fabExpanded,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it }) + expandVertically(),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it }) + shrinkVertically()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                FabItemView(fabItem = FabItem(
                    icon = Icons.Outlined.RemoveRedEye,
                    title = "View Approved Capture",
                    color = Approved
                ), modifier = Modifier
                    .clickable {
                        onFabClick(HomeAction.OnFabToggle(false))
                        onClick(0)
                    })

                Spacer(modifier = Modifier.width(10.dp))
                FabItemView(fabItem = FabItem(
                    icon = Icons.Outlined.Add,
                    title = "Capture",
                    color = Captured
                ), modifier = Modifier
                    .clickable {
                        onFabClick(HomeAction.OnFabToggle(false))
                        onClick(1)
                    })
                Spacer(modifier = Modifier.width(10.dp))
                FabItemView(fabItem = FabItem(
                    icon = Icons.Outlined.RemoveRedEye,
                    title = "View Declined Capture",
                    color = Rejected
                ), modifier = Modifier
                    .clickable {
                        onFabClick(HomeAction.OnFabToggle(false))
                        onClick(2)
                    })

            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                .clickable {
                    onFabClick(HomeAction.OnFabToggle(!fabExpanded))
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(  modifier = Modifier
                    ,imageVector = Icons.Filled.Home, contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun FabItemView(
    modifier: Modifier = Modifier,
    fabItem: FabItem,
    iconSize: Dp = 50.dp,
) {
    Box(
        modifier = modifier
            .size(65.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(fabItem.color.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(fabItem.color)
                .padding(7.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(iconSize),
                imageVector = fabItem.icon,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = fabItem.title,
            )
        }
    }
}

data class FabItem(
    val title: String,
    val icon: ImageVector,
    val color:Color
)

@DCScreenPreview
@Composable
private fun PreviewFloatingActionButtonWithFabItems() {
    DataCaptureTheme {
        FloatingActionButtonWithFabItems(false, onClick = {})
    }
}