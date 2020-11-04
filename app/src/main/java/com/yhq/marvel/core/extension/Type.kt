package com.yhq.marvel.core.extension

fun String?.orDefault(defaultValue: String = ""): String = this?.let { this } ?: defaultValue

fun Int?.orDefault(defaultValue: Int = 0): Int = this?.let { this } ?: defaultValue




