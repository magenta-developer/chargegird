package com.magenta.sdkresponse.interfaces;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Rahul on 4-12-2017.
 */

public interface StopChargingResponseInterface {

    void StopCharging(JSONObject object, int tag);

    void onError(VolleyError error, int requestTag);
}
