package com.magenta.sdkresponse.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.magenta.sdkresponse.interfaces.StartChargingResponseInterface;
import com.magenta.sdkresponse.utilties.ApplicationClass;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rahul on 04--2-2022.
 */
public class StartChargingRequest {
    private StartChargingResponseInterface mListener;
    private int mRequestMethod;
    private Context mContext;
    private String Token ;

    public StartChargingRequest(String baseURL, Context context, StartChargingResponseInterface
            listener, int requestTag, String Token, String mobile, String Chargeboxid
            , String Chargertype , String Chargerserialno , String priceplan , String enterprise , String Createdby
            , String chargingamount ,String source) {
        mListener = listener;
        mRequestMethod = Request.Method.POST;
        this.mContext = context;
        this.Token=Token;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", Long.parseLong(mobile));
            jsonObject.put("chargeboxid", Chargeboxid);
            jsonObject.put("connectortype", Chargertype);
            jsonObject.put("connectorno", Chargerserialno);
            jsonObject.put("priceplan", priceplan);
            jsonObject.put("enterprise", enterprise);
            jsonObject.put("planid", "");
            jsonObject.put("createdby", Createdby);
            jsonObject.put("source",source);
            jsonObject.put("chargingamount", Integer.parseInt(chargingamount));

        } catch (Exception e) {
            e.printStackTrace();
        }
        fetchJSONDataFromWebService(baseURL+"/sm/secure/api/v1/remote/start/startinitialize", jsonObject, requestTag);
    }

    private void fetchJSONDataFromWebService(String URL, JSONObject jsonPayload, final int requestTag) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(mRequestMethod, URL, jsonPayload,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        mListener.StartCharging(response, requestTag);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mListener.onError(error, requestTag);

                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getCustomHeaders();
            }


        };
        jsonObjReq.setTag(requestTag);

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(1200000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        ApplicationClass.getInstance().addToRequestQueue(jsonObjReq);
    }


    private HashMap<String, String> getCustomHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("Authorization", "basic " +Token);
        headers.put("Accept", "'application/json");


        return headers;
    }

}
