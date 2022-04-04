package com.magenta.sdkresponse.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.magenta.sdkresponse.interfaces.StopChargingResponseInterface;
import com.magenta.sdkresponse.interfaces.TransactionResponseInterface;
import com.magenta.sdkresponse.utilties.ApplicationClass;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rahul on 04--2-2022.
 */
public class TransactionRequest {
    private TransactionResponseInterface mListener;
    private int mRequestMethod;
    private Context mContext;
    private String merchantKey ;

    public TransactionRequest(String baseURL, Context context, TransactionResponseInterface
            listener, int requestTag, String merchantKey, String mobile, String transactionid
           ) {
        mListener = listener;
        mRequestMethod = Request.Method.POST;
        this.mContext = context;
        this.merchantKey=merchantKey;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", Long.parseLong(mobile));
            jsonObject.put("transactionId", Long.parseLong(transactionid));



        } catch (Exception e) {
            e.printStackTrace();
        }
        fetchJSONDataFromWebService(baseURL+"/tm/secure/api/v1/charging/receipt", jsonObject, requestTag);
    }

    private void fetchJSONDataFromWebService(String URL, JSONObject jsonPayload, final int requestTag) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(mRequestMethod, URL, jsonPayload,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        mListener.transactionResponse(response, requestTag);
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
        //  jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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
