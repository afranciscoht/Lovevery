package com.lovevery.exam.utils.listeners

import androidx.activity.result.ActivityResult

interface BaseResultLauncher {
    fun onResult(result: ActivityResult)
}