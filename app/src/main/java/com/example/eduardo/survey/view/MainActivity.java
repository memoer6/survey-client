package com.example.eduardo.survey.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eduardo.survey.OcrCaptureActivity;
import com.example.eduardo.survey.R;
import com.example.eduardo.survey.api.ApiClient;
import com.example.eduardo.survey.fields.FieldsFactory;
import com.example.eduardo.survey.utility.Store;

import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements ApiClient.onSurveyResponse {


    private static final String TAG = "MainActivity";
    private ConstraintLayout constraintLayout;
    private ApiClient mApiClient;
    private SharedPreferences mSharedPref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mTextView = (TextView) findViewById(R.id.textView);
        ImageButton mWalmartButton = (ImageButton) findViewById(R.id.walmartButton);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        // Required for the snackbar
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        //Start OcrCaptureActivity
        mWalmartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startOcrCaptureActivity(Store.STORE_WALMART);

            }
        });

    }

    private void submitSurvey(Bundle capturedArgs) {

        //read the setting preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Bundle allArgs = new Bundle(capturedArgs);
        allArgs.putString(Store.KEY_FIELD_FIRST_NAME,
                sharedPref.getString(SettingsActivity.KEY_PREF_FIRST_NAME, ""));
        allArgs.putString(Store.KEY_FIELD_LAST_NAME,
                sharedPref.getString(SettingsActivity.KEY_PREF_LAST_NAME, ""));
        allArgs.putString(Store.KEY_FIELD_GENDER,
                sharedPref.getString(SettingsActivity.KEY_PREF_GENDER, ""));
        allArgs.putInt(Store.KEY_FIELD_BIRTH_YEAR,
                sharedPref.getInt(SettingsActivity.KEY_PREF_BIRTH_YEAR, 1990));
        allArgs.putString(Store.KEY_FIELD_STREET,
                sharedPref.getString(SettingsActivity.KEY_PREF_STREET, ""));
        allArgs.putString(Store.KEY_FIELD_CITY,
                sharedPref.getString(SettingsActivity.KEY_PREF_CITY, ""));
        allArgs.putString(Store.KEY_FIELD_POSTAL_CODE,
                sharedPref.getString(SettingsActivity.KEY_PREF_POSTAL_CODE, ""));
        allArgs.putString(Store.KEY_FIELD_EMAIL,
                sharedPref.getString(SettingsActivity.KEY_PREF_EMAIL, ""));
        allArgs.putString(Store.KEY_FIELD_PHONE,
                sharedPref.getString(SettingsActivity.KEY_PREF_PHONE, ""));

        //Send to the API the new SurveyFields instance built by FieldsFactory
        mApiClient.conductSurvey(FieldsFactory.getFields(allArgs));

    }

    private void startOcrCaptureActivity(int storeId) {

        Intent intent = new Intent(this, OcrCaptureActivity.class);
        intent.putExtra(Store.KEY_FIELD_STORE_ID, storeId);
        startActivityForResult(intent, storeId);

    }


    // inflate the data for the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }


    // selection of one item in the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // Callback returning the captured text from OcrCaptureActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check which request we're responding to
        if (requestCode == Store.STORE_WALMART) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                launchTextConfirmationDialog(Store.STORE_WALMART, data);

             }
             if (resultCode == RESULT_CANCELED) {

             }

        }

    }

    private void launchTextConfirmationDialog(final int storeId, Intent data) {

        // Get the data from intent
        final Bundle capturedArgs = data.getBundleExtra("data");

        //get the keys and values and build a message
        StringBuilder message = new StringBuilder();
        if (capturedArgs != null) {
            for (String key: capturedArgs.keySet()) {
                String value = capturedArgs.get(key).toString();
                message.append(key.toUpperCase() + ":   " + value + "\n");

            }
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are these values correct?")
                .setMessage(message)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Dialog", "Captured values are correct");
                        dialog.dismiss();
                        //Add store id to identify whose these fields are from
                        capturedArgs.putInt(Store.KEY_FIELD_STORE_ID, storeId);
                        submitSurvey(capturedArgs);

                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Dialog", "Captured values are NOT correct");
                        dialog.dismiss();
                        startOcrCaptureActivity(storeId);


                    }
                })
                .show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mApiClient = new ApiClient(this);


    }

    @Override
    protected void onPause() {
        super.onPause();
        //Remove the reference of this activity in ApiClient so it can be garbage collected
        mApiClient = null;

    }

    //Callbacks from ApiClient
    @Override
    public void onResponse(Call call, Response response) {

      if (response.isSuccessful()) {

          Snackbar snackbar = Snackbar.make(constraintLayout, R.string.success_submit, Snackbar.LENGTH_SHORT);
          snackbar.show();
      }
    }

    @Override
    public void onFailure(String message) {

        Snackbar snackbar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();

    }
}
