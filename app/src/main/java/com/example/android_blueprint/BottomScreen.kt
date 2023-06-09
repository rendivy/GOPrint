package com.example.android_blueprint

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.android_blueprint.model.BottomBarModel
import com.example.android_blueprint.ui.theme.ActionFontColor
import com.example.android_blueprint.ui.theme.ItemBottom
import com.example.android_blueprint.ui.theme.ItemEnd
import com.example.android_blueprint.ui.theme.ItemStart
import com.example.android_blueprint.ui.theme.ItemTop
import com.example.android_blueprint.ui.theme.PrimaryColor
import com.example.android_blueprint.ui.theme.SecondaryColor
import com.example.android_blueprint.ui.theme.ShadowRadius
import com.example.android_blueprint.ui.theme.UnSelectedColor
import com.example.android_blueprint.ui.theme.neuMedium
import com.example.android_blueprint.viewModel.BottomNavGraphViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(
    showSystemUi = true, device = "spec:parent=pixel_5"
)
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()
    val viewModel: BottomNavGraphViewModel = viewModel()
    Scaffold(
        bottomBar = {
            BottomBar(
                modifier = Modifier.background(Color.Black),
                navController = navController
            )
        },
    )
    {
        viewModel.BottomNav(navController = navController)
    }
}


@Composable
fun BottomBar(modifier: Modifier, navController: NavHostController) {
    val screens = listOf(
        BottomBarModel.Console,
        BottomBarModel.Field,
        BottomBarModel.BlockOfList,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SecondaryColor),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }

}


@Composable
fun AddItem(
    screen: BottomBarModel,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    val iconColor = if (selected) PrimaryColor else UnSelectedColor
    val textColor = if (selected) ActionFontColor else UnSelectedColor
    Box(
        modifier = Modifier
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            modifier = Modifier.padding(
                bottom = ItemBottom,
                top = ItemTop,
                start = ItemStart,
                end = ItemEnd
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = screen.icon), contentDescription = null,
                tint = iconColor, modifier = if (selected) Modifier
                    .shadow(
                        ShadowRadius,
                        spotColor = PrimaryColor,
                        ambientColor = PrimaryColor
                    ) else Modifier
            )
            Text(text = screen.title, color = textColor, fontFamily = neuMedium)
        }
    }
}
