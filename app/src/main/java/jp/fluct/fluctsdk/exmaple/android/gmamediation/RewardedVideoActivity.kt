package jp.fluct.fluctsdk.exmaple.android.gmamediation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener

class RewardedVideoActivity : AppCompatActivity() {

    companion object {

        // region Required: AdMob / GAMで発行されるAdUnitIdを設定してください。下記はテスト枠です。
        private const val GMA_AD_UNIT_ID
            = "ca-app-pub-2222899768110117/1421293380"
        // endregion

        // reigon Optional: 必要に応じて設定してください
        private const val TAG_FOR_CHILD_DIRECTED_TREATMENT = false
        private const val GENDER = AdRequest.GENDER_UNKNOWN
        // endregion

    }

    private var ad: RewardedVideoAd? = null

    private val unitIdNotice by lazy { findViewById<TextView>(R.id.text) }
    private val loadButton by lazy { findViewById<Button>(R.id.load) }
    private val showButton by lazy { findViewById<Button>(R.id.show) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rewarded_video_activity)

        loadButton.setOnClickListener {
            // region Required: 広告の初期化・読込
            ad = MobileAds.getRewardedVideoAdInstance(this@RewardedVideoActivity)
                .apply {
                    rewardedVideoAdListener = listener
                }


            ad!!.loadAd(
                GMA_AD_UNIT_ID,
                AdRequest.Builder()
                    .setGender(GENDER)
                    .tagForChildDirectedTreatment(TAG_FOR_CHILD_DIRECTED_TREATMENT)
                    .build()
            )
            // endregion

            resetButtonAppearance()
        }

        showButton.setOnClickListener {
            // region Required: 広告表示
            ad!!.show()
            // endregion
        }

        resetButtonAppearance()
        unitIdNotice.text = getString(R.string.unit_id_notice,
            GMA_AD_UNIT_ID
        )
    }

    private val listener = object : RewardedVideoAdListener {

        // region Required: 広告Close
        override fun onRewardedVideoAdClosed() {
            ad = null
            resetButtonAppearance()
        }
        // endregion

        // region Required: 広告読込完了
        override fun onRewardedVideoAdLoaded() {
            resetButtonAppearance()
        }
        // endregion

        // Required: リワード付与。適切な処理を行ってください
        override fun onRewarded(p0: RewardItem?) {
            Toast.makeText(this@RewardedVideoActivity, "Rewarded!", Toast.LENGTH_LONG)
                .show()
        }
        // endregion

        // region Required: 広告読込失敗。適切な処理を行ってください
        override fun onRewardedVideoAdFailedToLoad(p0: Int) {
            ad = null
            resetButtonAppearance()
        }
        // endregion

        override fun onRewardedVideoAdLeftApplication() {
            // Optional: 広告LeftApplication
        }

        override fun onRewardedVideoAdOpened() {
            // Optional: 広告Opened
        }

        override fun onRewardedVideoCompleted() {
            // Optional: 広告Completed
            ad = null
            resetButtonAppearance()
        }

        override fun onRewardedVideoStarted() {
            // Optional: 広告Start
        }

    }

    private fun resetButtonAppearance() {
        val isLoaded = ad?.isLoaded ?: false
        val isLoading = ad != null && !isLoaded

        loadButton.isEnabled = !isLoading
        showButton.isEnabled = isLoaded
    }

}
