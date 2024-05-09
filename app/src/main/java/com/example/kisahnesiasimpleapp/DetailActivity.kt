package com.example.kisahnesiasimpleapp

import android.content.Intent
import android.graphics.Paint.Style
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.kisahnesiasimpleapp.model.Story

class DetailActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_STORY = "extra_story"
    }

    private lateinit var imageDetailImageView: ImageView
    private lateinit var titleDetailTextView: TextView
    private lateinit var synopsisDetailsTextView: TextView
    private lateinit var genreDetailTextView: TextView
    private lateinit var regionDetailTextView: TextView
    private lateinit var shareButton: Button

    private fun initComponents() {
        imageDetailImageView = findViewById(R.id.imageDetailImageView)
        titleDetailTextView = findViewById(R.id.titleDetailTextView)
        synopsisDetailsTextView = findViewById(R.id.synopsisDetailTextView)
        genreDetailTextView = findViewById(R.id.genreDetailTextView)
        regionDetailTextView = findViewById(R.id.regionDetailTextView)
        shareButton = findViewById(R.id.shareButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initComponents()

        val person = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Story>(EXTRA_STORY, Story::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Story>(EXTRA_STORY)
        }
        if (person != null) {
            imageDetailImageView.setImageResource(person.image)
            titleDetailTextView.text = person.title
            synopsisDetailsTextView.text = person.synopsis
            regionDetailTextView.text = person.region
            genreDetailTextView.text = spanningText(person.genre)
        }
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, titleDetailTextView.text)
                type = "text/plain"
            }
            startActivity(sendIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun spanningText(text: String): SpannableString {
        val spannableString = SpannableString("Posted in $text") //0,9
        spannableString.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            9,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }


}