package com.girish.raman.healthcare.rest;

import android.util.Log;

import com.girish.raman.healthcare.APIListener;
import com.girish.raman.healthcare.utils.Constants;
import com.studioidan.httpagent.HttpAgent;
import com.studioidan.httpagent.JsonCallback;

import org.json.JSONObject;

public class RestClient {
    private APIListener listener;

    public RestClient(APIListener listener) {
        this.listener = listener;
    }

    public void getDiagnosis(String symptoms, String age, String sex) {
        HttpAgent.get(Constants.GETDIAGNOSIS_API_END_POINT)
                .queryParams("symptoms", symptoms, "sex", sex, "age", age)
                .goJson(new JsonCallback() {
                    @Override
                    protected void onDone(boolean success, JSONObject jsonResults) {
                        if (success) {
                            listener.onComplete("diagnosis", jsonResults.toString());
                        } else {
                            Log.e("getDiagnosis FAILURE", "in RestClient");
                        }
                    }
                });
    }

    public void getDoctors(String condition, String location) {
        HttpAgent.get(Constants.GETDOCTORS_API_END_POINT)
                .queryParams("condition", condition, "location", location)
                .goJson(new JsonCallback() {
                    @Override
                    protected void onDone(boolean success, JSONObject jsonResults) {
                        if (success) {
                            listener.onComplete("doctors", jsonResults.toString());
                        } else {
                            Log.e("getDoctors FAILURE", "in RestClient");
                        }
                    }
                });
    }
}
