package com.example.android_blueprint.viewModel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import block.BlockEntity
import block.EndBlock
import block.StartBlock
import com.example.android_blueprint.model.BlockValue
import com.example.android_blueprint.model.FieldBlock
import com.example.android_blueprint.model.PreviousBlocks
import com.example.android_blueprint.model.Transform
import com.example.android_blueprint.ui.theme.ActiveColor
import com.example.android_blueprint.ui.theme.InitialOffset
import com.example.android_blueprint.ui.theme.InitialScale
import com.example.android_blueprint.ui.theme.PrimaryColor
import interpretator.Interpret

class InfiniteFieldViewModel : ViewModel() {
    private var currentIndex = 0
    val interpret = Interpret()
    val startBlock = StartBlock()
    val endBlock = EndBlock()
    val startViewModel = BlockViewModel()
    val endViewModel = BlockViewModel()
    var blocks by mutableStateOf(listOf<FieldBlock>())
    var deleteMode by mutableStateOf(false)
    var transform by mutableStateOf(Transform(InitialScale, InitialOffset))
    var isDebug by mutableStateOf(false)

    @OptIn(ExperimentalMaterialApi::class)
    val sheetState = ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    companion object {
        var previousBlocks = PreviousBlocks()
    }

    @OptIn(ExperimentalMaterialApi::class)
    suspend fun closeBottomSheet() {
        sheetState.hide()
    }

    @OptIn(ExperimentalMaterialApi::class)
    suspend fun openBottomSheet() {
        sheetState.show()
    }

    fun openDebugger() {
        isDebug = true
    }

    fun closeDebugger() {
        isDebug = false
    }

    fun addBlock(blockValue: Any) {
        val pathViewModel = BlockViewModel()
        if (blockValue == BlockValue.IfBlock) {
            val pathVIewModelEndif = BlockViewModel()
            addVariable(
                FieldBlock(
                    value = blockValue,
                    index = currentIndex++,
                    block = BlockFactory.createObj(blockValue = blockValue) as BlockEntity,
                    pathViewModel = pathViewModel
                )
            )
            addVariable(
                FieldBlock(
                    value = BlockValue.EndifBlock,
                    index = currentIndex++,
                    block = BlockFactory.createObj(blockValue = BlockValue.EndifBlock) as BlockEntity,
                    pathViewModel = pathVIewModelEndif

                )
            )
        } else {
            addVariable(
                FieldBlock(
                    value = blockValue,
                    index = currentIndex++,
                    block = BlockFactory.createObj(blockValue = blockValue) as BlockEntity,
                    pathViewModel = pathViewModel
                )
            )
        }
    }

    fun deleteMovableBlock(index: Int, block: BlockEntity, blockViewModel: BlockViewModel) {
        valueChange(index = index, value = FieldBlock())
        BlockEntity.deleteBlock(block)
        deleteAllBranches(blockViewModel)
    }

    fun getDeleteButtonColor(): Color {
        return if (deleteMode) {
            ActiveColor
        } else {
            PrimaryColor
        }
    }

    fun changeMode() {
        deleteMode = !deleteMode
    }

    fun changeTransform(zoomChange: Float, offsetChange: Offset) {
        transform = transform.copy(
            scale = transform.scale * zoomChange,
            offset = transform.offset + offsetChange
        )
    }


    private fun addVariable(fieldBlock: FieldBlock) {
        blocks = blocks + listOf(fieldBlock)
    }

    private fun valueChange(index: Int, value: FieldBlock) {
        blocks = blocks.mapIndexed { i, old -> if (i == index) value else old }
    }

}