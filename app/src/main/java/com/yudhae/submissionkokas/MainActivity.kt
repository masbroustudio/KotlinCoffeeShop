package com.yudhae.submissionkokas

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.yudhae.submissionkokas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener {
            val whatsappNumber =
                getString(R.string.contact_us_phone)

            val uri = Uri.parse("smsto:$whatsappNumber")
            val intent = Intent(Intent.ACTION_SENDTO, uri)

            intent.setPackage("com.whatsapp")

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "WhatsApp is not installed.", Toast.LENGTH_SHORT).show()
            }
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_minuman,
                R.id.nav_makanan,
                R.id.nav_login_register
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        // INFO Check Connection, First Run
        val pref: SharedPreferences = this.getSharedPreferences("PREF", 0)
        val editor = pref.edit()
        val firstRun = pref.getBoolean("firstRun", true)
        if (firstRun) {
            Log.i("onCreate: ", getString(R.string.info_check_connection))
            editor.putBoolean("firstRun", false)
            editor.apply()
            Toast.makeText(this, getString(R.string.info_check_internet), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menuProfile -> {
            startActivity(Intent(this, AboutPageActivity::class.java))
            true
        }

        R.id.menuInfo -> {
            showDialog()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    // Info App Dialog
    private fun showDialog() {
        val view = View.inflate(this@MainActivity, R.layout.layout_app_kokas, null)

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val btn = view.findViewById<Button>(R.id.btnOk)
        btn.setOnClickListener {
            dialog.dismiss()
        }
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val displayWidth = displayMetrics.widthPixels
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window!!.attributes)
        val dialogWindowWidth = (displayWidth * 0.96f).toInt()
        layoutParams.width = dialogWindowWidth
        dialog.window!!.attributes = layoutParams
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}