package jp.fluct.fluctsdk.exmaple.android.gmamediation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    private val bannerButton by lazy { findViewById<Button>(R.id.banner_ad) }
    private val rewardedButton by lazy { findViewById<Button>(R.id.rewarded_video_ad) }
    private val interstitialButton by lazy { findViewById<Button>(R.id.interstitial_video_ad) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        bannerButton.setOnClickListener {
            startActivity(Intent(this, BannerActivity::class.java))
        }

        rewardedButton.setOnClickListener {
            startActivity(Intent(this, RewardedVideoActivity::class.java))
        }

        interstitialButton.setOnClickListener {
            startActivity(Intent(this, InterstitialActivity::class.java))
        }

    }

}
