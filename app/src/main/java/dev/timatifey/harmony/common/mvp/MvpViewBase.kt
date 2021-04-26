package dev.timatifey.harmony.common.mvp

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

abstract class MvpViewBase : MvpView {

    protected fun <T : View> findViewById(id: Int): T {
        return rootView.findViewById(id)
    }

    protected val context: Context
        get() = rootView.context

    protected fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }

    protected fun getString(@StringRes id: Int, vararg formatArgs: Any?): String {
        return context.getString(id, *formatArgs)
    }

    protected fun getQuantityString(
        @PluralsRes id: Int,
        quantity: Int,
        vararg formatArgs: Any?
    ): String {
        return context.resources.getQuantityString(id, quantity, *formatArgs)
    }

    protected fun getDrawable(@DrawableRes id: Int): Drawable? {
        return ContextCompat.getDrawable(context, id)
    }

    protected fun showToast(@StringRes id: Int) {
        Toast.makeText(context, getString(id), Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}
