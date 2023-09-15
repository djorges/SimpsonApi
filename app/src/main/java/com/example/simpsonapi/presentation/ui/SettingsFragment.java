package com.example.simpsonapi.presentation.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.simpsonapi.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}