package com.example.sollwar.randm

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}
interface Navigator {
    fun characterSelect()
}