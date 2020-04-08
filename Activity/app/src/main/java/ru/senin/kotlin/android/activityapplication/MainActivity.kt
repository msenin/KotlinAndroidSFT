package ru.senin.kotlin.android.activityapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*

const val CHILD_REQUEST_CODE = 0

class MainActivity : AppCompatActivity() {

    private var logRecordNumber = 0


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
            startChildActivity()
        }
    }

    private fun startChildActivity() {
        val intent = Intent(this, ChildActivity::class.java)
        startActivityForResult(intent, CHILD_REQUEST_CODE)
    }

    private fun showSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CHILD_REQUEST_CODE -> {
                val result = data?.extras?.getString("result")
                message("Возврат из дочерней Activity с результатом $result")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("logRecordNumber", logRecordNumber)
        message("onSaveInstanceState - save logRecordNumber: $logRecordNumber")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.getInt("logRecordNumber")?.let {
            message("onRestoreInstanceState - logRecordNumber: $logRecordNumber --> $it")
            logRecordNumber = it
        }
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
        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val showToasts = sharedPref.getBoolean("show_toasts", true)
        if (showToasts) {
            Toast.makeText(
                applicationContext,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
        Log.d("LifeCycle", message)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbarChildActivity -> {
                startChildActivity()
                return true
            }
            R.id.toolbarSettings -> {
                showSettings()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
