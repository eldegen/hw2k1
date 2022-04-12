package com.example.hw2k1.extensions

import android.content.Context
import kotlin.random.Random

fun Context.random(size: Int): Int {
    return Random.nextInt(size)
}