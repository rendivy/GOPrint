package com.example.android_blueprint.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.android_blueprint.R
import com.example.android_blueprint.model.BlockValue
import com.example.android_blueprint.ui.theme.BlockHeight
import com.example.android_blueprint.ui.theme.HeightOfSmallBlocks
import com.example.android_blueprint.ui.theme.NotSingleTextSize
import com.example.android_blueprint.ui.theme.OperatorsTextColor

@Composable
fun BinaryFixedOperatorBlock(
    value: BlockValue.BinaryOperator,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .height(BlockHeight)
    ) {
        if (value == BlockValue.BinaryOperator.POW || value == BlockValue.BinaryOperator.LOG) {
            BinaryOperatorText(
                text = value.text,
                fontSize = NotSingleTextSize,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            BinaryOperatorText(modifier = Modifier.align(Alignment.Center), text = value.text)
        }
        SupportingFlow()
        SupportingFlow(modifier = Modifier.align(Alignment.BottomStart))
        SupportingFlow(modifier = Modifier.align(Alignment.CenterEnd))
    }
}

@Composable
fun UnaryFixedOperatorBlock(
    value: BlockValue.UnaryOperator,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .height(BlockHeight)
    ) {
        UnaryOperatorText(modifier = Modifier.align(Alignment.Center), text = value.text)
        SupportingFlow(modifier = Modifier.align(Alignment.CenterStart))
        SupportingFlow(modifier = Modifier.align(Alignment.CenterEnd))
    }
}

@Composable
fun FixedPrintBlock(
    value: BlockValue.PrintBlock,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = BlockHeight)
    ) {
        ComplexBlockText(text = value.text)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            MainFlow()
            MainFlow()
        }
        SupportingFlow(modifier = Modifier.align(Alignment.Start))
    }
}


@Composable
fun FixedBranchBlock(
    value: BlockValue.IfBlock,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = BlockHeight)
    ) {
        ComplexBlockText(text = value.text)
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MainFlow(modifier = Modifier.align(Alignment.TopStart))
            Row(modifier = Modifier.align(Alignment.TopEnd)) {
                TextForFlow(text = stringResource(id = R.string.ifTrueValue))
                MainFlow()
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier.align(Alignment.TopStart)) {
                SupportingFlow()
                TextForFlow(text = stringResource(id = R.string.conditionalValue))
            }
            Row(modifier = Modifier.align(Alignment.TopEnd)) {
                TextForFlow(text = stringResource(id = R.string.ifFalseValue))
                MainFlow()
            }
        }

    }
}


@Composable
fun FixedInitializationBlock(
    value: BlockValue.InitializationBlock,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = BlockHeight)
    ) {
        ComplexBlockText(text = value.text)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MainFlow()
            MainFlow()
        }
        placeholderText(
            text = stringResource(id = R.string.initBlockValue), modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun FixedSetBlock(
    value: BlockValue.SetBlock,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = BlockHeight)
    ) {
        ComplexBlockText(text = value.text)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MainFlow()
            MainFlow()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            placeholderText(
                text = stringResource(id = R.string.nameEqualValue), modifier = Modifier
                    .weight(1f)
            )
            SupportingFlow(modifier = Modifier.align(Alignment.CenterVertically))
        }
    }
}


@Composable
fun FixedLoopBlock(
    value: BlockValue,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = BlockHeight)
    ) {
        ComplexBlockText(text = value.text)
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MainFlow()
            Row(modifier = Modifier.align(Alignment.TopEnd)) {
                TextForFlow(text = stringResource(id = R.string.loop))
                MainFlow()
            }
        }
        placeholderText(
            text = stringResource(id = R.string.conditional),
            modifier = Modifier.fillMaxWidth()
        )
        Row(modifier = Modifier.align(Alignment.End)) {
            TextForFlow(text = stringResource(id = R.string.endLoop))
            MainFlow()
        }
    }
}

@Composable
fun FixedGetValueBlock(
    value: BlockValue.GetValueBlock,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = HeightOfSmallBlocks)
    ) {
        ComplexBlockText(text = value.text)
        Row {
            placeholderText(
                text = stringResource(id = R.string.expression),
                modifier = Modifier.weight(1f)
            )
            SupportingFlow()
        }
    }
}

@Composable
fun FixedFunctionBlock(
    value: BlockValue.FunctionBlock,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = HeightOfSmallBlocks)
    ) {
        ComplexBlockText(text = value.text)
        Row {
            placeholderText(
                text = stringResource(id = R.string.arrayName),
                modifier = Modifier.weight(1f)
            )
            MainFlow()
        }
    }
}

@Composable
fun FixedReturnBlock(
    value: BlockValue.ReturnBlock,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = BlockHeight)
    ) {
        MainFlow()
        ComplexBlockText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = value.text,
            color = OperatorsTextColor
        )
        Row {
            SupportingFlow()
            TextForFlow(text = stringResource(id = R.string.value))
        }
    }
}

@Composable
fun FixedContinueOrBreakBlock(
    value: BlockValue,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = BlockHeight)
    ) {
        MainFlow()
        ComplexBlockText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = value.text,
            color = OperatorsTextColor
        )
    }
}

@Composable
fun FixedCallFunctionBlock(
    value: BlockValue.CallFunctionBlock,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = BlockHeight)
    ) {
        ComplexBlockText(text = value.text)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            MainFlow()
            MainFlow()
        }
        placeholderText(
            text = stringResource(id = R.string.arrayName), modifier = Modifier
                .fillMaxWidth()
        )
    }
}