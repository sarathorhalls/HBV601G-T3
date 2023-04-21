# Krítíkin - Android

Android client for the Krítíkin web service

## Building

To build the app for testing in Android Studio, use the Run function on the toolbar or select `Run` -> `Run 'app'` in the menu bar.

To build APKs (debug and release) for sideloading, either use the `Build` -> `Build Bundle(s) / APK(s)` -> `Build APK(s)` function in the Android Studio menu bar, or to build an APK without the use of Android Studio, run one of the following commands, depending on your operating system:

### macOS/Linux

```sh
./gradlew assemble
```

### Windows

```sh
gradlew assemble
```

The build output can be found at `app/build/outputs/apk/`.
