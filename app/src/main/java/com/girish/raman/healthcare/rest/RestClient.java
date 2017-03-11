package com.girish.raman.healthcare.rest;

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

    public void sendPostRequest(String input) {
        HttpAgent.post(Constants.END_POINT)
                .contentType(Constants.APPLICATION_JSON)
                .withBody(input)
                .goJson(new JsonCallback() {
                    @Override
                    protected void onDone(boolean success, JSONObject jsonResults) {
                        listener.onComplete(jsonResults);
                    }
                });
    }
}
