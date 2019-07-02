package jp.fluct.fluctsdk.exmaple.android.gmamediation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class InterstitialActivity : AppCompatActivity() {

    companion object {

        // region Required: AdMob / GAMで発行されるAdUnitIdを設定してください。下記はテスト枠です。
        private const val GMA_AD_UNIT_ID
            = "/62532913/a_fluct.test_1024x768_chocovayashitest.interstitial_11940"
        // endregion

        // reigon Optional: 必要に応じて設定してください
        private const val TAG_FOR_CHILD_DIRECTED_TREATMENT = false
        private const val GENDER = AdRequest.GENDER_UNKNOWN
        // endregion

    }

    private var ad: InterstitialAd? = null

    private val unitIdNotice by lazy { findViewById<TextView>(R.id.text) }
    private val loadButton by lazy { findViewById<Button>(R.id.load) }
    private val showButton by lazy { findViewById<Button>(R.id.show) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.interstitial_activity)

        loadButton.setOnClickListener {
            // region Required: 広告の初期化・読込
            ad = InterstitialAd(this@InterstitialActivity)
                .apply {
                    adUnitId = GMA_AD_UNIT_ID
                    adListener = listener
                }

            ad!!.loadAd(
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

    private val listener = object : AdListener() {

        // region Required: 広告読込失敗。適切な処理を行ってください
        override fun onAdFailedToLoad(p0: Int) {
            super.onAdFailedToLoad(p0)
            resetButtonAppearance()
        }
        // endregion

        // region Required: 広告読込完了
        override fun onAdLoaded() {
            super.onAdLoaded()
            resetButtonAppearance()
        }
        // endregion

        // region Required: 広告Close
        override fun onAdClosed() {
            super.onAdClosed()
            resetButtonAppearance()
        }
        // endregion

    }

    private fun resetButtonAppearance() {
        val isLoading = ad?.isLoading ?: false
        val isLoaded = ad?.isLoaded ?: false

        loadButton.isEnabled = !isLoading
        showButton.isEnabled = isLoaded
    }

}
