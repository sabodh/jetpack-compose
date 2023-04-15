package com.virgin.jetpack_compose.use_case

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class StringUiText{
    data class DynamicString(val value: String): StringUiText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ): StringUiText()

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
        }
    }

    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}
