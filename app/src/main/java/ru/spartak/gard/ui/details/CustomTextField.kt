package ru.spartak.gard.ui.details

import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import ru.spartak.gard.ui.root_screen.main_screen.home_tab.edit_screen.OutlinedTextField
import ru.spartak.gard.ui.theme.Text50
import ru.spartak.gard.ui.theme.Text500

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    placeholder: String = "",
    placeholderColor: Color = Text500,
    borderColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    cursorColor: Color = Text50
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholderText = placeholder,
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Text50,
            disabledTextColor = Text50,
            placeholderColor = placeholderColor,
            disabledPlaceholderColor = placeholderColor,
            unfocusedBorderColor = borderColor,
            disabledBorderColor = borderColor,
            errorBorderColor = MaterialTheme.colors.error
        ),
        cursorColor = SolidColor(cursorColor)
    )
}