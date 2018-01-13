package com.example.eduardo.survey.view;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.example.eduardo.survey.R;
import com.example.eduardo.survey.utility.Store;

public class SettingsActivity extends AppCompatActivity {



    //tags for setting preferences
    protected static final String KEY_PREF_FIRST_NAME = "first_name_preference";
    protected static final String KEY_PREF_LAST_NAME = "last_name_preference";
    protected static final String KEY_PREF_GENDER = "gender_preference";
    protected static final String KEY_PREF_BIRTH_YEAR = "birth_year_preference";
    protected static final String KEY_PREF_STREET = "street_preference";
    protected static final String KEY_PREF_CITY = "city_preference";
    protected static final String KEY_PREF_POSTAL_CODE = "postal_code_preference";
    protected static final String KEY_PREF_EMAIL = "email_preference";
    protected static final String KEY_PREF_PHONE = "phone_preference";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }


        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new SettingsFragment())
                .commit();

        //ensures that your application is properly initialized with default settings
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

    }


    //Our SettingsActivity hosts the SettingsFragment that displays our preference settings
    public static class SettingsFragment extends PreferenceFragment implements
            SharedPreferences.OnSharedPreferenceChangeListener {


        Preference mFirstNamePref;
        Preference mLastNamePref;
        Preference mGenderPref;
        Preference mBirthYearPref;
        Preference mStreetPref;
        Preference mCityPref;
        Preference mPostalCodePref;
        Preference mEmailPref;
        Preference mPhonePref;



        //There are several reasons you might want to be notified as soon as the user changes one
        // of the preferences. In order to receive a callback when a change happens to any one of
        // the preferences, implement the SharedPreference.OnSharedPreferenceChangeListener interface
        // and register the listener for the SharedPreferences object by calling
        // registerOnSharedPreferenceChangeListener().The interface has only one callback method,
        // onSharedPreferenceChanged(), and you might find it easiest to implement the interface
        // as a part of your activity

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);

            SharedPreferences sharedPref = getPreferenceScreen().getSharedPreferences();

            //Load summary
            mFirstNamePref = findPreference(KEY_PREF_FIRST_NAME);
            mFirstNamePref.setSummary(String.valueOf(sharedPref.getString(KEY_PREF_FIRST_NAME,
                    "")));

            mLastNamePref = findPreference(KEY_PREF_LAST_NAME);
            mLastNamePref.setSummary(String.valueOf(sharedPref.getString(KEY_PREF_LAST_NAME,
                        "")));

            mGenderPref = findPreference(KEY_PREF_GENDER);
            mGenderPref.setSummary(String.valueOf(sharedPref.getString(KEY_PREF_GENDER,
                    "")));

            mBirthYearPref = findPreference(KEY_PREF_BIRTH_YEAR);
            mBirthYearPref.setSummary(String.valueOf(sharedPref.getInt(KEY_PREF_BIRTH_YEAR,
                    Store.DEFAULT_BIRTH_YEAR)));

            mStreetPref = findPreference(KEY_PREF_STREET);
            mStreetPref.setSummary(String.valueOf(sharedPref.getString(KEY_PREF_STREET,
                    "")));

            mCityPref = findPreference(KEY_PREF_CITY);
            mCityPref.setSummary(String.valueOf(sharedPref.getString(KEY_PREF_CITY,
                    "")));

            mPostalCodePref = findPreference(KEY_PREF_POSTAL_CODE);
            mPostalCodePref.setSummary(String.valueOf(sharedPref.getString(KEY_PREF_POSTAL_CODE,
                    "")));

            mEmailPref = findPreference(KEY_PREF_EMAIL);
            mEmailPref.setSummary(String.valueOf(sharedPref.getString(KEY_PREF_EMAIL,
                    "")));

            mPhonePref = findPreference(KEY_PREF_PHONE);
            mPhonePref.setSummary(String.valueOf(sharedPref.getString(KEY_PREF_PHONE,
                    "")));


        }

        @Override
        public void onSharedPreferenceChanged(  SharedPreferences sharedPreferences, String key) {

            switch (key) {
                case KEY_PREF_FIRST_NAME:
                    mFirstNamePref.setSummary(String.valueOf(sharedPreferences.getString(key,
                            "")));
                    return;
                case KEY_PREF_LAST_NAME:
                    mLastNamePref.setSummary(String.valueOf(sharedPreferences.getString(key,
                            "")));
                    return;

                case KEY_PREF_GENDER:
                    mGenderPref.setSummary(String.valueOf(sharedPreferences.getString(key,
                            "")));
                    return;

                case KEY_PREF_BIRTH_YEAR:
                    mBirthYearPref.setSummary(String.valueOf(sharedPreferences.getInt(key,
                            Store.DEFAULT_BIRTH_YEAR)));
                    return;

                case KEY_PREF_STREET:
                    mStreetPref.setSummary(String.valueOf(sharedPreferences.getString(key,
                            "")));
                    return;

                case KEY_PREF_CITY:
                    mCityPref.setSummary(String.valueOf(sharedPreferences.getString(key,
                            "")));
                    return;

                case KEY_PREF_POSTAL_CODE:
                    mPostalCodePref.setSummary(String.valueOf(sharedPreferences.getString(key,
                            "")));
                    return;

                case KEY_PREF_EMAIL:
                    mEmailPref.setSummary(String.valueOf(sharedPreferences.getString(key,
                            "")));
                    return;

                case KEY_PREF_PHONE:
                    mPhonePref.setSummary(String.valueOf(sharedPreferences.getString(key,
                            "")));

            }

        }

        //For proper lifecycle management in the activity, we recommend that you register and
        // unregister your SharedPreferences.OnSharedPreferenceChangeListener during the onResume()
        // and onPause() callbacks, respectively:
        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }

    }


}
