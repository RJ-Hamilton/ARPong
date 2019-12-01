package com.chuahamilton.arpong

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.chuahamilton.arpong.arpong.checkIsSupportedDeviceOrFinish
import com.chuahamilton.arpong.fragments.GameFragment
import com.chuahamilton.arpong.services.GameBackgroundMusic

class ARPongGameActivity : AppCompatActivity() {

    private var gameIntent = Intent()
    private val activityTag = "ARPongGameActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arpong_game)

        gameIntent = startMusic()

        if (!checkIsSupportedDeviceOrFinish(activityTag, this)) {
            return
        }

        val fm: FragmentManager = supportFragmentManager
        var frag: Fragment? = fm.findFragmentById(R.id.fragment_container)
        if (frag == null) {
            frag = GameFragment()
            fm.beginTransaction().add(R.id.fragment_container, frag).commit()
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

    private fun startMusic(): Intent{
        val musicService = Intent(this, GameBackgroundMusic::class.java)
        startService(musicService)
        return musicService
    }
}
