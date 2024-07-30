package com.lovevery.exam.utils.activity_result

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import com.lovevery.exam.utils.extensions.ExamActivityResultContract
import com.lovevery.exam.utils.extensions.MainResultLauncher

fun <INPUT, OUTPUT> Fragment.registerForResult(
    contract: ExamActivityResultContract<INPUT, OUTPUT>,
    callback: (OUTPUT) -> Unit = {}
) = MainResultLauncher(
    registerForActivityResult(contract, callback),
    contract
)

fun <INPUT, OUTPUT> ComponentActivity.registerForResult(
    contract: ExamActivityResultContract<INPUT, OUTPUT>,
    callback: (OUTPUT) -> Unit = {}
) = MainResultLauncher(
    registerForActivityResult(contract, callback),
    contract
)