package com.girish.raman.healthcare;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.girish.raman.healthcare.model.Condition;
import com.girish.raman.healthcare.model.Output;
import com.girish.raman.healthcare.rest.RestClient;

import net.gotev.speech.GoogleVoiceTypingDisabledException;
import net.gotev.speech.Speech;
import net.gotev.speech.SpeechDelegate;
import net.gotev.speech.SpeechRecognitionNotAvailable;
import net.gotev.speech.ui.SpeechProgressView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements APIListener, LocationListener {

    final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 101;
    RestClient client;
    ProgressDialog dialog;
    LocationManager locationManager;
    private double longitude;
    private double latitude;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new RestClient(this);
        list = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
        } else {
            setup();
        }
    }

    private void setup() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        getUserInfo();
    }

    private void getUserInfo() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String age = prefs.getString("age", "NA");
        if ("NA".equals(age)) {
            setContentView(R.layout.activity_info);
        }
    }

    public void onClickSubmitInfo(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString("age", ((EditText) findViewById(R.id.age)).getText().toString()).apply();
        prefs.edit().putString("sex", ((Spinner) findViewById(R.id.sex)).getSelectedItem().toString()).apply();
        setContentView(R.layout.activity_main);
    }

    public void startListening(View view) {
        try {
            SpeechProgressView speechProgressView = (SpeechProgressView) findViewById(R.id.progress);
            int[] heights = {60, 76, 58, 80, 55};
            speechProgressView.setBarMaxHeightsInDp(heights);

            Speech.getInstance().startListening(speechProgressView, new SpeechDelegate() {
                @Override
                public void onStartOfSpeech() {
                    Log.i("speech", "speech recognition is now active");
                }

                @Override
                public void onSpeechRmsChanged(float value) {
                    Log.d("speech", "rms is now: " + value);
                }

                @Override
                public void onSpeechPartialResults(List<String> results) {
                    StringBuilder str = new StringBuilder();
                    for (String res : results) {
                        str.append(res).append(" ");
                    }
                    //Log.e("speech", "partial result: " + str.toString().trim());
                    list.add(str.toString().trim());
                }

                @Override
                public void onSpeechResult(String result) {
                    List<String> mylist = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        String s = list.get(i);
                        if (i == 0) {
                            mylist.add(s.trim());
                        } else {
                            String ss = s.replace(list.get(i - 1), "");
                            mylist.add(ss.trim());
                        }
                    }
                    StringBuilder builder = new StringBuilder();
                    for (String s : mylist) {
                        builder.append(s).append(",");
                        Log.e("mylist", s);
                    }

                    String symptoms = builder.toString().substring(1, builder.toString().length() - 1);

                    Log.e("speech", "result: " + symptoms);
                    Toast.makeText(MainActivity.this, symptoms, Toast.LENGTH_SHORT).show();
                    dialog = ProgressDialog.show(MainActivity.this, null, "Wait a minute.", true);
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    String age = prefs.getString("age", "23");
                    String sex = prefs.getString("sex", "male");
                    client.getDiagnosis(symptoms, age, sex);
                    list.clear();
                }
            });
        } catch (SpeechRecognitionNotAvailable exc) {
            Log.e("speech", "Speech recognition is not available on this device!");
        } catch (GoogleVoiceTypingDisabledException exc) {
            Log.e("speech", "Google voice typing must be enabled!");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Speech.getInstance().unregisterDelegate();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO: {
                if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Record Audio and Location Permission needed for the app to work!", Toast.LENGTH_SHORT).show();
                }
                setup();
            }
        }
    }

    @Override
    public void onComplete(String type, String result) {
        dialog.dismiss();
        ObjectMapper mapper = new ObjectMapper();
        if ("diagnosis".equals(type)) {
            try {
                Output output = mapper.readValue(result, Output.class);
                processOutput(output);
            } catch (IOException e) {
                Log.e("IOException", e.toString());
            }
        } else if ("doctors".equals(type)) {
            Log.e("DOCTORS", result);
        }
    }

    private void processOutput(Output output) {
        List<Condition> conditionList = output.getConditionList();
        conditionList.sort(new ConditionsComparator());
        String conditionName = conditionList.get(0).getName();
        Speech.getInstance().say("Your problem might be " + conditionName);
        //Log.e("latitude", String.valueOf(latitude));
        //Log.e("longitude", String.valueOf(longitude));
        // client.getDoctors(conditionName, latitude + "," + latitude);
    }

    @Override
    public void onError(String error) {
        dialog.dismiss();
    }

    public void onLocationChanged(Location location) {
        //Log.e("changed", "changed");
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
