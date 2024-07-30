package com.lovevery.exam.utils.extensions

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract

class ExamActivityResultContract<INPUT, OUTPUT>(
    private val contract: ActivityResultContract<INPUT, OUTPUT>
) : ActivityResultContract<INPUT, OUTPUT>() {
    var callback: ((OUTPUT) -> Unit)? = null

    override fun createIntent(context: Context, input: INPUT) =
        contract.createIntent(context, input)

    override fun parseResult(resultCode: Int, intent: Intent?): OUTPUT =
        contract.parseResult(resultCode, intent).also {
            callback?.invoke(it)
        }

    override fun getSynchronousResult(context: Context, input: INPUT) =
        contract.getSynchronousResult(context, input)
}

class MainResultLauncher<INPUT, OUTPUT>(
    private val launcher: ActivityResultLauncher<INPUT>,
    private val contract: ExamActivityResultContract<INPUT, OUTPUT>
) {
    fun launch(input: INPUT, callback: (OUTPUT) -> Unit) {
        contract.callback = callback
        launcher.launch(input)
    }
}