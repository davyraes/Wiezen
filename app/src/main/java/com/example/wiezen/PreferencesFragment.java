package com.example.wiezen;

import android.os.Bundle;

import com.example.wiezen.R;

import androidx.preference.PreferenceFragmentCompat;

public class PreferencesFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferencefragment);
    }
}
