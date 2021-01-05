package com.project.segunfrancis.nasapicturesapp.util

import com.google.android.material.snackbar.Snackbar

/**
 * Data class that manages the state of the message tobe displayed
 * by the [Snackbar]
 */

data class Message(val message: String, val caution: Boolean)