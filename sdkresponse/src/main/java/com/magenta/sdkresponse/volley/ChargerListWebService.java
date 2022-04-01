package com.magenta.sdkresponse.volley;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.magenta.sdkresponse.utilties.ApplicationClass;


import org.json.JSONArray;
import org.json.JSONObject;
import com.magenta.sdkresponse.interfaces.ChargerResponseInterface;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rahul on 04--2-2022.
 */
public class ChargerListWebService {
    private ChargerResponseInterface mListener;
    private int mRequestMethod;
    private Context mContext;
    private String merchantKey ;

    public ChargerListWebService(String baseURL, Context context,  ChargerResponseInterface listener,int requestTag,String merchantKey) {
        mListener = listener;
        mRequestMethod = Request.Method.GET;
        this.mContext = context;
        this.merchantKey=merchantKey;
        fetchJSONArrayFromWebService(baseURL+"/am/api/v1/getlocations", null, requestTag);
    }


    private void fetchJSONArrayFromWebService(String URL, JSONArray jsonPayload, final int requestTag) {


        JsonArrayRequest jsonObjReq = new JsonArrayRequest(mRequestMethod, URL, jsonPayload,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray array) {


                        mListener.ChargerListResponse(array, requestTag);
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
        ApplicationClass.getInstance().addToRequestQueue(jsonObjReq);
    }

    private HashMap<String, String> getCustomHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("Authorization", "basic " +merchantKey);
        headers.put("Accept", "'application/json");


        return headers;
    }

}
