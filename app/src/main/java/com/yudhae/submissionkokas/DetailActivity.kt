package com.yudhae.submissionkokas

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var photoMain: ImageView
    private lateinit var photoSplash: ImageView
    private lateinit var name: TextView
    private lateinit var nameDetail: TextView
    private lateinit var description: TextView

    companion object {
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_PHOTO_SPLASH = "extra_photo_splash"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_NAME_DETAIL = "extra_name_detail"
        const val EXTRA_DESCRIPTION = "extra_description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.title = intent.getStringExtra(EXTRA_NAME)

        photoMain = findViewById(R.id.img_character_main)
        photoSplash = findViewById(R.id.img_character_splash)
        name = findViewById(R.id.text_character_name)
        nameDetail = findViewById(R.id.text_weapon)
        description = findViewById(R.id.text_vision)

        Glide.with(this)
            .load(intent.getStringExtra(EXTRA_PHOTO))
            .into(photoMain)

        Glide.with(this)
            .load(intent.getStringExtra(EXTRA_PHOTO_SPLASH))
            .into(photoSplash)

        name.text = intent.getStringExtra(EXTRA_NAME)
        nameDetail.text = intent.getStringExtra(EXTRA_NAME_DETAIL)
        description.text = intent.getStringExtra(EXTRA_DESCRIPTION)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {

                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check this, the best coffee our recommendation:")
                shareIntent.putExtra(Intent.EXTRA_TITLE, "KOKAS (KOPILIKASI)")
                shareIntent.type = "text/plain"
                startActivity(Intent.createChooser(shareIntent, "Share To:"))

            }

            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}