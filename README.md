# Krítíkin - Android

Android client for the Krítíkin web service.

## Configuration

By default, this app uses the deployed web service at [`https://hbv501g-t18-production.up.railway.app/api`](https://hbv501g-t18-production.up.railway.app/api). To replace it with a self-hosted instance of the [backend](https://github.com/sarathorhalls/HBV501G-T18), replace the API URL specified in `app/src/main/java/hi/hbv601g/kritikin/services/NetworkManagerService.java`.

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
