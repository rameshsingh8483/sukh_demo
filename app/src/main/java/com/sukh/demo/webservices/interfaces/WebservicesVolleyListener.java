package com.sukh.demo.webservices.interfaces;


import com.android.volley.VolleyError;

/**
 * Created by ravi on 3/31/2018.
 */

public interface WebservicesVolleyListener {

    void onResponse(String response);

    void onError(VolleyError message);
}
