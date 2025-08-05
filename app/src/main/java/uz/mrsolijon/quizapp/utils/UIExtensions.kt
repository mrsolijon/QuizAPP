package uz.mrsolijon.quizapp.utils

import android.view.View


object UIExtensions {

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.inVisible() {
        visibility = View.INVISIBLE
    }
}