package com.ahmedadel.robustandroid

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmedadel.robustandroid.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tmdb_icon_iv.animate()
            .setStartDelay(500)
            .setDuration(1500)
            .scaleX(20F)
            .scaleY(20F)
            .alpha(0F)
            .setListener(object : Animator.AnimatorListener {

                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    finish()
                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationStart(animation: Animator?) {

                }
            })

    }
}
