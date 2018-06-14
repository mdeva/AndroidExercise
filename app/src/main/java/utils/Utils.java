package utils;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import Constant.Constant;

/**
 * Created by deverajan on 13/2/18.
 */

public final class Utils {
    private Utils() {
    }

    /**
     * Checks if is online.
     *
     * @param connManager
     *            the Connectivity Manager
     * @return state, true if is online
     */
    public static boolean isOnline(final ConnectivityManager connManager) {
        boolean state = false;
        final NetworkInfo netInfo = connManager.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isAvailable() || netInfo.isConnectedOrConnecting()) {
                state = true;
            }
        }
        return state;
    }

    public static boolean isPortrait(Context context){
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static String getDeviceDensity(DisplayMetrics metrics) {
        String deviceDensity;

        if (metrics.densityDpi <= DisplayMetrics.DENSITY_LOW) {
            deviceDensity = Constant.DENSITY_LDPI;
        } else if (metrics.densityDpi <= DisplayMetrics.DENSITY_MEDIUM) {
            deviceDensity = Constant.DENSITY_MDPI;
        } else if (metrics.densityDpi <= DisplayMetrics.DENSITY_HIGH) {
            deviceDensity = Constant.DENSITY_HDPI;
        } else if (metrics.densityDpi <= DisplayMetrics.DENSITY_XHIGH) {
            deviceDensity = Constant.DENSITY_XHDPI;
        } else {
            deviceDensity = Constant.DENSITY_XXHDPI;
        }

        return deviceDensity;
    }
}
