package com.example.android_blueprint.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.android_blueprint.ui.theme.ActionColor

class ConsoleViewModel: ViewModel() {
    companion object {
        var defaultTextColor = ActionColor
        var consoleText by mutableStateOf("")
        var debugText by mutableStateOf("")
    }
}