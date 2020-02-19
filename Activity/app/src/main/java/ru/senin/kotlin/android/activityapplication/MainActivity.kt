package ru.senin.kotlin.android.activityapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var logRecordNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toastButton.setOnClickListener {
            Toast.makeText(applicationContext, "Это всплывающее сообщение!", Toast.LENGTH_LONG).show()
        }

        writeLogButton.setOnClickListener {
            Log.i("writeLogButton", "Запись в лог № ${logRecordNumber++}")
            Toast.makeText(applicationContext, "Сделана запись в лог!", Toast.LENGTH_SHORT).show()
        }

        newActivityButton.setOnClickListener {
            // TODO: стартовать новую активность используя startActivityForResult(...)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // TODO: получить результат запущенной активности
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        // TODO: сохранить счётчик записей в лог
    }

    override fun onResume() {
        super.onResume()
        message("onResume - на переднем плане")
    }

    override fun onDestroy() {
        super.onDestroy()
        message("onDestroy - уничтожение")
    }

    override fun onPause() {
        super.onPause()
        message("onPause - больше не на переднем плане")
    }

    override fun onStart() {
        super.onStart()
        message("onStart - скоро будет на переднем плане")
    }

    override fun onStop() {
        super.onStop()
        message("onStop - больше не на переднем плане")
    }

    private fun message(message: String) {
        Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_SHORT
        ).show()
        Log.d("LifeCycle", message)
    }
}
