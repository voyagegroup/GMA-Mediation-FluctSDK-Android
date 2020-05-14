package jp.fluct.fluctsdk.exmaple.android.gmamediation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

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

    private var ad: RewardedAd? = null

    private val unitIdNotice by lazy { findViewById<TextView>(R.id.text) }
    private val loadButton by lazy { findViewById<Button>(R.id.load) }
    private val showButton by lazy { findViewById<Button>(R.id.show) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rewarded_video_activity)

        loadButton.setOnClickListener {
            // region Required: 広告の初期化・読込
            ad = RewardedAd(this, GMA_AD_UNIT_ID)

            val adRequest = AdRequest.Builder()
                .tagForChildDirectedTreatment(TAG_FOR_CHILD_DIRECTED_TREATMENT)
                .build()

            ad!!.loadAd(adRequest, adLoadCallback)
            // endregion

            resetButtonAppearance()
        }

        showButton.setOnClickListener {
            // region Required: 広告表示
            ad!!.show(this, adCallback)

            // endregion
        }

        resetButtonAppearance()
        unitIdNotice.text = getString(R.string.unit_id_notice,
            GMA_AD_UNIT_ID
        )
    }

    private val adLoadCallback = object: RewardedAdLoadCallback() {

        // region: 広告読込完了
        override fun onRewardedAdLoaded() {
            resetButtonAppearance()
        }
        // endregion

        // region: 広告読込失敗。適切な処理を行ってください
        override fun onRewardedAdFailedToLoad(errorCode: Int) {
            ad = null
            resetButtonAppearance()
        }
        // endregion
    }

    private val adCallback = object: RewardedAdCallback() {

        // region: 広告opened
        override fun onRewardedAdOpened() {
        }
        // endregion

        // region: 広告Close
        override fun onRewardedAdClosed() {
            ad = null
            resetButtonAppearance()
        }
        // endregion

        // region: リワード付与。適切な処理を行ってください
        override fun onUserEarnedReward(rewardItem: RewardItem) {
            Toast.makeText(this@RewardedVideoActivity, "Rewarded!", Toast.LENGTH_LONG)
                .show()
        }
        // endregion

        // region: 広告の表示失敗
        override fun onRewardedAdFailedToShow(errorCode: Int) {
        }
        // endregion
    }

    private fun resetButtonAppearance() {
        val isLoaded = ad?.isLoaded ?: false
        val isLoading = ad != null && !isLoaded

        loadButton.isEnabled = !isLoading
        showButton.isEnabled = isLoaded
    }

}
