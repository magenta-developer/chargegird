package com.magenta.cgsdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.magenta.sdkresponse.interfaces.ChargerListResponseInterface;
import com.magenta.sdkresponse.interfaces.ChargingProgressResponseInterface;
import com.magenta.sdkresponse.interfaces.StartChargingResponseInterface;
import com.magenta.sdkresponse.interfaces.StationDetailsResponseInterface;
import com.magenta.sdkresponse.interfaces.StopChargingResponseInterface;
import com.magenta.sdkresponse.interfaces.TransactionResponseInterface;
import com.magenta.sdkresponse.volley.ChargingProgress;
import com.magenta.sdkresponse.volley.GetAllChargerList;
import com.magenta.sdkresponse.volley.StartChargingRequest;
import com.magenta.sdkresponse.volley.StationDetails;
import com.magenta.sdkresponse.volley.StopChargingRequest;
import com.magenta.sdkresponse.volley.TransactionRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ChargerListResponseInterface, StationDetailsResponseInterface, StartChargingResponseInterface, ChargingProgressResponseInterface, StopChargingResponseInterface, TransactionResponseInterface {

    //private ArrayList<ClusterLatLon> clusterLatLons;
    private static final int GET_STATION_LIST = 1;
    private static final int GET_STATION_DETAILS = 2;
    TextView display ;
    EditText baseurl ,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        baseurl = findViewById(R.id.baseurl);
        token = findViewById(R.id.token);
        findViewById(R.id.getallstation).setOnClickListener(view -> {
            if ( Patterns.WEB_URL.matcher(baseurl.getText().toString()).matches()){
                new GetAllChargerList(baseurl.getText().toString(),this,this,GET_STATION_LIST,"","");
            }else {
                baseurl.requestFocus();
                baseurl.setError("Enter valid URL");
            }
        });
        findViewById(R.id.getstationDetails).setOnClickListener(view -> {
            if (!baseurl.getText().toString().isEmpty()){
                new StationDetails(baseurl.getText().toString(),this,this,GET_STATION_DETAILS,token.getText().toString(),"");
            }else {
                baseurl.requestFocus();
                baseurl.setError("Enter valid URL");
            }
        });

        findViewById(R.id.startcharging).setOnClickListener(view -> {
            if (!baseurl.getText().toString().isEmpty()){
                new StartChargingRequest(baseurl.getText().toString(),this,this,GET_STATION_DETAILS,token.getText().toString(),"",
                        "","","","","","","");
            }else {
                baseurl.requestFocus();
                baseurl.setError("Enter valid URL");
            }
        });
        findViewById(R.id.chargingprocess).setOnClickListener(view -> {
            if (!baseurl.getText().toString().isEmpty()){
                new ChargingProgress(baseurl.getText().toString(),this,this,GET_STATION_DETAILS,token.getText().toString(),"",
                        "","","","","");
            }else {
                baseurl.requestFocus();
                baseurl.setError("Enter valid URL");
            }
        });
        findViewById(R.id.stopcharging).setOnClickListener(view -> {
            if (!baseurl.getText().toString().isEmpty()){
                new StopChargingRequest(baseurl.getText().toString(),this,this,GET_STATION_DETAILS,token.getText().toString(),"",
                        "","","");
            }else {
                baseurl.requestFocus();
                baseurl.setError("Enter valid URL");
            }
        });

        findViewById(R.id.transaction).setOnClickListener(view -> {
            if (!baseurl.getText().toString().isEmpty()){
                new TransactionRequest(baseurl.getText().toString(),this,this,GET_STATION_DETAILS,token.getText().toString(),"",
                        "");
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
    public void chargerDetails(JSONObject object, int tag) {
        display.setText(object.toString());
    }

    @Override
    public void StartCharging(JSONObject object, int tag) {
        display.setText(object.toString());
    }

    @Override
    public void ChargingProgress(JSONObject object, int tag) {
        display.setText(object.toString());
    }

    @Override
    public void StopCharging(JSONObject object, int tag) {
        display.setText(object.toString());
    }

    @Override
    public void transactionResponse(JSONObject object, int tag) {
        display.setText(object.toString());
    }

    @Override
    public void onError(VolleyError error, int requestTag) {
        baseurl.requestFocus();

        baseurl.setError("Enter valid URL");
    }
}