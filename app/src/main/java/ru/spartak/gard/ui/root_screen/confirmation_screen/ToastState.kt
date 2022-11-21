package ru.spartak.gard.ui.root_screen.confirmation_screen

import androidx.compose.ui.graphics.Color
import ru.spartak.gard.R
import ru.spartak.gard.ui.theme.Error600
import ru.spartak.gard.ui.theme.Primary600
import ru.spartak.gard.ui.theme.Success500

sealed class ToastState(val color: Color,val iconId: Int){
    object Success:ToastState(color = Success500, iconId = R.drawable.ic_check_mark)
    object Error:ToastState(color = Error600, iconId = R.drawable.ic_warning)
    object Info:ToastState(color = Primary600, iconId = R.drawable.ic_info)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ToastState

        if (color != other.color) return false
        if (iconId != other.iconId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = color.hashCode()
        result = 31 * result + iconId
        return result
    }


}
