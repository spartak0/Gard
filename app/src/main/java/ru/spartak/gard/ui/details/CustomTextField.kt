package ru.spartak.gard.ui.details

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.edit_screen.OutlinedTextField
import ru.spartak.gard.ui.theme.Text50
import ru.spartak.gard.ui.theme.Text500

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    placeholder: String = ""
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholderText = placeholder,
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Text50,
            disabledTextColor = Text50,
            placeholderColor = Text500,
            disabledPlaceholderColor = Text500
        ),
        cursorColor = SolidColor(Text50)
    )
}