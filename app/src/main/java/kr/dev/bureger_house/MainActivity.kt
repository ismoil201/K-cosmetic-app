package kr.dev.bureger_house

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    val PREF_NAME: String = "MyPrefs"

    val KEY_NAME: String = "burger"
    val Vottar_NAME: String = "Drink"

    val Drink_NAME: String = "vottar"

    override fun onDestroy() {
        super.onDestroy()

        val sharedPreferences2 = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor2 = sharedPreferences2.edit()
        editor2.remove(KEY_NAME)
        editor2.remove("ikkinchi")
        editor2.remove("uchinchi")
        editor2.apply()
        val sharedPreferences = getSharedPreferences(Vottar_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("vottar")
        editor.remove("ikkinchi_drink")
        editor.remove("uchinchi_drink")
        editor.apply()
    }
}