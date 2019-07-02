# android-fluct-gma-interstitial-exmaple

Android版FluctSDKを用いたGoogle Mobile Adsインタースティシャルメディエーションのサンプル実装です。

## 動作検証環境

Android向けFluctSDKおよび当サンプルプロジェクトは下記環境にて開発・検証しております

- macOS High Sierra 10.13.6
- Android Studio 3.4

SDKバージョン

- Google Mobile Ads: 16.0.0
- FluctSDK: 5.4.0
  - 動画リワードメディエーションアダプター: v2.4.0
  - Google Mobile Ads向けカスタムイベント: v1.0.0

## プロジェクトの利用方法

一般的なAndroidアプリプロジェクトの構成で作成しております。

### 1. プロジェクトのご用意

### Zipファイルにてご案内させて頂いた場合

当該ファイルを展開してください。

```sh
unzip android-fluct-gma-interstitial-example-master.zip
# Archive:  android-fluct-gma-interstitial-example-master.zip
# ...
#  extracting: android-fluct-gma-interstitial-example-master/settings.gradle 
cd android-fluct-gma-interstitial-example-master
```

#### GitHubリポジトリをご案内させて頂いた場合

ローカルへ当リポジトリをcloneしてください。

```sh
git clone https://github.com/S64/android-fluct-gma-interstitial-example.git
# Cloning into 'android-fluct-gma-interstitial-example'...
# ...
# Resolving deltas: 100% (42/42), done.
cd android-fluct-gma-interstitial-example
```

### 2. Android Studioで開く

お手元にご用意いただいたディレクトリのルート (このREADME.mdファイルが配置されているディレクトリ) がプロジェクトルートです。

## 実装上の諸注意

Google Mobile AdsによるFluctSDK動画インタースティシャルを実装頂く際は、以下の点にご注意ください:

- 必ずAdUnitIdを変更ください
  - `MainActivity.kt`内で設定されているAdUnitIdはテスト用のものです
- Manifest記述, Proguard記述, 依存モジュールの追加を忘れずに行ってください
  - 本番環境にて予期せぬ問題が発生する場合がございます

なお、上記はサンプル内の下記ファイルにて実装しております:

- `/app/build.gradle`
- `/app/proguard-rules.pro`
- `/app/src/main/AndroidManifest.xml`
- `/app/src/main/java/jp/s64/android/exmaple/gmafluctinterstitial/MainActivity.kt`

## 動作検証上の諸注意

Google Mobile AdsによるFluctSDK動画インタースティシャルの動作検証をしていただく際は、以下の点にご注意ください:

- 原則実機での動作確認を行ってください
  - Google Mobile Adsの仕様上、エミュレータやテストデバイスではFluctSDKの広告枠が約定しない場合がございます
