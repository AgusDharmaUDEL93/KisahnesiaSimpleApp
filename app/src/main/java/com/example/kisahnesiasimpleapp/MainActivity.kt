package com.example.kisahnesiasimpleapp

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kisahnesiasimpleapp.adapter.ListStoryAdapter
import com.example.kisahnesiasimpleapp.model.Story

class MainActivity : AppCompatActivity() {

    private lateinit var storyListRecyclerView: RecyclerView
    private val storyList = ArrayList<Story>()

    private fun initComponent() {
        storyListRecyclerView = findViewById(R.id.storyListRecyclerView)
        storyListRecyclerView.hasFixedSize()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_page, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()

        storyList.addAll(getAllListStory())
        showRecyclerList()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                // Create your custom animation.
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 200L

                // Call SplashScreenView.remove at the end of your custom animation.
                slideUp.doOnEnd { splashScreenView.remove() }

                // Run your animation.
                slideUp.start()
            }
        }

    }

    private fun showRecyclerList() {
        storyListRecyclerView.layoutManager = LinearLayoutManager(this)
        storyListRecyclerView.adapter = ListStoryAdapter(storyList)
    }

    @SuppressLint("Recycle")
    private fun getAllListStory(): ArrayList<Story> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataSynopsis = resources.getStringArray(R.array.data_sinopsis)
        val dataImage = resources.obtainTypedArray(R.array.data_image)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataRegion = resources.getStringArray(R.array.data_region)
        val data = ArrayList<Story>()
        for (i in dataTitle.indices) {
            data.add(Story(dataTitle[i], dataSynopsis[i], dataImage.getResourceId(i, -1), dataRegion[i], dataGenre[i]))
        }
        return data
    }

}