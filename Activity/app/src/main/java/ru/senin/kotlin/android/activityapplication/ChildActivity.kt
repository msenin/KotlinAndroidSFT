package ru.senin.kotlin.android.activityapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_child.*


class ChildActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)

        childCloseOkButton.setOnClickListener {
            finishWithResult("Ok", Activity.RESULT_OK)
        }

        childCloseCancelButton.setOnClickListener {
            finishWithResult("Cancel", Activity.RESULT_CANCELED)
        }
    }

    private fun finishWithResult(resultString: String, resultCode: Int) {
        val intent = Intent()
        intent.putExtra("result", resultString)
        setResult(resultCode, intent)
        finish()
    }

}
