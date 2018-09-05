package com.das.das_p1;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class PreferenciasActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeChanger.getChanger().changeDefaultTheme(this);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
