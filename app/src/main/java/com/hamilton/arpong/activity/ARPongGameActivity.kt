package com.hamilton.arpong.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hamilton.arpong.R
import com.hamilton.arpong.databinding.ActivityArpongGameBinding
import com.hamilton.arpong.fragments.GameFragment
import com.hamilton.arpong.services.GameBackgroundMusic
import com.hamilton.arpong.utils.DifficultyLevel

class ARPongGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArpongGameBinding
    private lateinit var difficultyLevel: DifficultyLevel
    private val activityTag = "ARPongGameActivity"
    private var bundle = Bundle()
    private var gameIntent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArpongGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameIntent = startMusic()

        difficultyLevel = intent.extras!!.get("DIFFICULTY") as DifficultyLevel

        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = GameFragment()

            bundle.putSerializable("DIFFICULTY", difficultyLevel)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        gameIntent = startMusic()
    }

    override fun onPause() {
        super.onPause()
        stopService(gameIntent)
    }

    private fun startMusic(): Intent {
        val musicService = Intent(this, GameBackgroundMusic::class.java)
        startService(musicService)
        return musicService
    }
}
