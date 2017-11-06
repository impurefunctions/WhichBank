package bw.co.whichbank.util;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefManager {

    // Shared preferences file name
    private static final String PREF_NAME = "WhichBank-frag_welcome";
    private static final String IS_REGISTRATION_COMPLETE = "IsRegistrationComplete";
    private SharedPreferences pref;

    public PrefManager(Context context) {
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

    }

    public boolean isRegComplete() {
        return pref.getBoolean(IS_REGISTRATION_COMPLETE, false);
    }

    public void setFirstTimeLaunch(boolean regComplete) {

        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(IS_REGISTRATION_COMPLETE, regComplete);

        editor.apply();
    }

}
