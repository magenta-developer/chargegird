package com.magenta.cgsdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;
import com.magenta.sdkresponse.interfaces.ChargerResponseInterface;
import com.magenta.sdkresponse.model.ClusterLatLon;
import com.magenta.sdkresponse.volley.ChargerListWebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ChargerResponseInterface {

    //private ArrayList<ClusterLatLon> clusterLatLons;
    private static final int GET_STATION_LIST = 1;
    TextView display ;
    EditText baseurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        baseurl = findViewById(R.id.baseurl);
        findViewById(R.id.getallstation).setOnClickListener(view -> {
            if ( Patterns.WEB_URL.matcher(baseurl.getText().toString()).matches()){
                new ChargerListWebService(baseurl.getText().toString(),this,this,GET_STATION_LIST,"");
            }else {
                baseurl.requestFocus();
                baseurl.setError("Enter valid URL");
            }
        });

    }

    @Override
    public void ChargerListResponse(JSONArray array, int tag) {
        switch (tag){
            case GET_STATION_LIST:
                    display.setText(array.toString());

                break;
        }
    }

    @Override
    public void onError(VolleyError error, int requestTag) {
        baseurl.requestFocus();
        baseurl.setError("Enter valid URL");
    }
}