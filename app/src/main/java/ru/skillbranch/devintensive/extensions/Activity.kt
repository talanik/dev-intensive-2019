package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(){
    val view = this.currentFocus
    view?.let { v ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
}

fun Activity.isKeyboardShown(): Boolean {
    val SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128

    val rootView = findViewById<ViewGroup>(android.R.id.content)
    val r = Rect()

    rootView.getWindowVisibleDisplayFrame(r)
    val dm = rootView.resources.displayMetrics

    val heightDiff = rootView.bottom - r.bottom

    val isKeyboardShown = heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density;
    return isKeyboardShown
}


fun Activity.isKeyboardOpen() = isKeyboardShown()
fun Activity.isKeyboardClosed() = !isKeyboardShown()