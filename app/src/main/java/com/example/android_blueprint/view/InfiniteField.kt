package com.example.android_blueprint.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.example.android_blueprint.R
import com.example.android_blueprint.model.BlockValue
import com.example.android_blueprint.model.BranchEntity
import com.example.android_blueprint.ui.theme.PrimaryColor
import com.example.android_blueprint.ui.theme.SecondaryColor
import com.example.android_blueprint.ui.theme.DefaultPadding
import com.example.android_blueprint.ui.theme.DeleteButtonSize
import com.example.android_blueprint.viewModel.InfiniteFieldViewModel
import com.example.android_blueprint.viewModel.start
import kotlinx.coroutines.launch


@Composable
fun InfiniteField(
    infiniteFieldViewModel: InfiniteFieldViewModel
) {

    val blocks = infiniteFieldViewModel.blocks
    val transform = infiniteFieldViewModel.transform
    val changeTransform = infiniteFieldViewModel::changeTransform
    val changeMode = infiniteFieldViewModel::changeMode
    val getDeleteButtonColor = infiniteFieldViewModel::getDeleteButtonColor
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        changeTransform(zoomChange, offsetChange)
    }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .transformable(state = state)
            .background(SecondaryColor)
            .fillMaxSize()
            .graphicsLayer(
                scaleX = transform.scale,
                scaleY = transform.scale,
                translationX = transform.offset.x,
                translationY = transform.offset.y
            )
            .drawBehind {
                for (value in BranchEntity.pathData.values) {
                    drawPath(
                        value.path,
                        if (value.isMainFlowBranch) Color.White else PrimaryColor,
                        style = Stroke(width = 10f)
                    )
                }
            }
    )
    {

        EndBlock(
            value = BlockValue.EndBlock, block = infiniteFieldViewModel.endBlock,
            viewModel = infiniteFieldViewModel.endViewModel
        )
        StartBlock(
            value = BlockValue.StartBlock, block = infiniteFieldViewModel.startBlock,
            viewModel = infiniteFieldViewModel.startViewModel
        )

        for (block in blocks) {
            SetMovableBlock(fieldBlock = block, infiniteFieldViewModel = infiniteFieldViewModel)
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            if (infiniteFieldViewModel.isDebug) {
                Box(
                    modifier = Modifier
                        .padding(DefaultPadding)
                        .size(DeleteButtonSize)
                        .clip(CircleShape)
                        .background(PrimaryColor)
                        .clickable {
                            coroutineScope.launch {
                                infiniteFieldViewModel.openBottomSheet()
                            }
                        }
                ) {
                    Icon(
                        (painterResource(R.drawable.bug_fill)), contentDescription = null,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(DefaultPadding)
                    .size(DeleteButtonSize)
                    .clip(CircleShape)
                    .background(PrimaryColor)
                    .clickable {
                        start(
                            startBlock = infiniteFieldViewModel.startBlock,
                            interpreter = infiniteFieldViewModel.interpret,
                            openDebugger = infiniteFieldViewModel::openDebugger,
                            closeDebugger = infiniteFieldViewModel::closeDebugger,
                        )
                    }
            ) {
                Icon(
                    painter = (painterResource(R.drawable.compile_icon)), contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .padding(DefaultPadding)
                    .size(DeleteButtonSize)
                    .clip(CircleShape)
                    .background(getDeleteButtonColor())
                    .clickable { changeMode() }
            ) {
                Icon(
                    painterResource(R.drawable.close_round_icon), contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


