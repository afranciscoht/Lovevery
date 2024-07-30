package com.lovevery.exam.base.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.lovevery.exam.utils.activity_result.registerForResult
import com.lovevery.exam.utils.extensions.ExamActivityResultContract
import com.lovevery.exam.utils.extensions.MainResultLauncher
import com.lovevery.exam.utils.extensions.hideKeyboard

open class BaseFragmentActivity : AppCompatActivity() {

    private val activityResultLauncher: MainResultLauncher<Intent, ActivityResult> =
        registerForResult(
            ExamActivityResultContract(ActivityResultContracts.StartActivityForResult())
        )

    override fun onPause() {
        super.onPause()
        hideKeyboard(this)
    }

    fun launchGenericActivityForResult(intent: Intent, callback: (ActivityResult) -> Unit) {
        activityResultLauncher.launch(intent) {
            callback(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}