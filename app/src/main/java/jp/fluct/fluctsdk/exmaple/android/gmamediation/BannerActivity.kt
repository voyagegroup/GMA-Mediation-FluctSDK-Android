package jp.fluct.fluctsdk.exmaple.android.gmamediation

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView

class BannerActivity : AppCompatActivity() {

    private val adView by lazy { findViewById<PublisherAdView>(R.id.banner_ad_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.banner_activity)

        adView.adListener = listener
        val request = PublisherAdRequest.Builder().build()
        adView.loadAd(request)
    }

    private val listener = object : AdListener() {

        override fun onAdImpression() {
            Toast.makeText(this@BannerActivity, "on ad impression.", Toast.LENGTH_LONG).show()
        }

        override fun onAdLeftApplication() {
            Toast.makeText(this@BannerActivity, "on ad left application.", Toast.LENGTH_LONG).show()
        }

        override fun onAdClicked() {
            Toast.makeText(this@BannerActivity, "on ad click.", Toast.LENGTH_LONG).show()
        }

        override fun onAdFailedToLoad(p0: Int) {
            Toast.makeText(this@BannerActivity, "on ad failed. error code [$p0]", Toast.LENGTH_LONG).show()
        }

        override fun onAdClosed() {
            Toast.makeText(this@BannerActivity, "on ad closed.", Toast.LENGTH_LONG).show()
        }

        override fun onAdOpened() {
            Toast.makeText(this@BannerActivity, "on ad opened.", Toast.LENGTH_LONG).show()
        }

        override fun onAdLoaded() {
            Toast.makeText(this@BannerActivity, "on ad loaded.", Toast.LENGTH_LONG).show()
        }
    }

}
