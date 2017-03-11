package com.girish.raman.healthcare;

import org.json.JSONObject;

public interface APIListener {
    void onComplete(JSONObject result);
    void onError(String error);
}
