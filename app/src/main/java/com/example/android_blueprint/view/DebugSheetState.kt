package com.example.android_blueprint.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.android_blueprint.R
import com.example.android_blueprint.ui.theme.PrimaryColor
import com.example.android_blueprint.ui.theme.SecondaryColor
import com.example.android_blueprint.ui.theme.BlockShape
import com.example.android_blueprint.ui.theme.BottomBarPadding
import com.example.android_blueprint.ui.theme.ButtonSize
import com.example.android_blueprint.ui.theme.DebugPadding
import com.example.android_blueprint.ui.theme.neuMedium
import com.example.android_blueprint.viewModel.ConsoleViewModel
import interpretator.Interpret
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction0

@Composable
fun DebugSheetState(interpret: Interpret, closeBottomSheet: KSuspendFunction0<Unit>) {
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SecondaryColor)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = BottomBarPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = ConsoleViewModel.debugText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = neuMedium,
                color = ConsoleViewModel.defaultTextColor
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = DebugPadding)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(ButtonSize)
                    .clip(BlockShape)
                    .background(PrimaryColor)
                    .clickable {
                        if (interpret.isRunning()) {
                            interpret.switchStepTo()
                        } else {
                            coroutineScope.launch {
                                closeBottomSheet()
                            }
                        }
                    }
            ) {
                Text(
                    text = stringResource(id = R.string.StepTo),
                    fontFamily = neuMedium,
                    color = SecondaryColor,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Box(
                modifier = Modifier
                    .size(ButtonSize)
                    .clip(BlockShape)
                    .background(PrimaryColor)
                    .clickable {
                        if (interpret.isRunning()) {
                            interpret.switchStepInto()
                        } else {
                            coroutineScope.launch {
                                closeBottomSheet()
                            }
                        }
                    }
            ) {
                Text(
                    text = stringResource(id = R.string.StepInto),
                    fontFamily = neuMedium,
                    color = SecondaryColor,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}