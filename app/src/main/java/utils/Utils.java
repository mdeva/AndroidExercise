package utils;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
}
