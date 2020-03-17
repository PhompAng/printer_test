package com.wongnai.android.printertest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wongnai.android.printertest.ui.home.HomeFragment
import com.wongnai.android.printertest.ui.printer.ConnectedPrinterFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.flContent, HomeFragment(), null).commit()
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeMenu -> HomeFragment()
                R.id.printerMenu -> ConnectedPrinterFragment()
                else -> null
            }?.let {
                supportFragmentManager.beginTransaction().replace(R.id.flContent, it, null).commit()
                true
            } ?: false
        }
    }
}
