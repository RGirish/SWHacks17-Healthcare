package com.girish.raman.healthcare;

import org.json.JSONObject;

public interface APIListener {
    void onComplete(String type, String result);
}
