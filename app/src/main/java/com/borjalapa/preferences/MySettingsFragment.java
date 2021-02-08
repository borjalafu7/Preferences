package com.borjalapa.preferences;

import android.os.Bundle;
import android.util.Log;

import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

public class MySettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);

        //EditTextPreference editTextPreference = findPreference();

        //Log.i("PREFERENCIA", editTextPreference.getText());

    }
}
