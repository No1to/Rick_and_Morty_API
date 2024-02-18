package com.example.rick_and_morty_api.utils

import com.example.rick_and_morty_api.R

enum class Status(val drawableResource: Int) {
    ALIVE(R.drawable.bg_green_circle),
    DEAD(R.drawable.bg_red_circle),
    UNKNOWN(R.drawable.bg_grey_circle)
}