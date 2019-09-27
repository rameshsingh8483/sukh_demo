package com.sukh.demo.webservices;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.sukh.demo.webservices.interfaces.WebservicesVolleyListener;
import com.sukh.demo.webservices.networking.CacheRequest;
import com.sukh.demo.webservices.networking.Singleton;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class WebService {


    public static void allVollyRequest(

            final Map<String , String> headersparams ,
            final Map<String , String> params2 ,
            final String url ,
            final int method ,
            final Context context,final WebservicesVolleyListener listener) {

        Log.w("allVollyRequest","URL >> "+url);
        Log.w("allVollyRequest","PARMS >> "+params2.toString());

        StringRequest strReq = new StringRequest(method , url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String nresponse) {


                        listener.onResponse(nresponse);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d("VollyError", "Error: " + error.getMessage());
                Log.e("LoginError", error.getMessage() + "Some Error");
                listener.onError(error) ;
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params = params2 ;



                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params = headersparams ;

//                params.put("Accept", "application/json");
//                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
            @Override
            public String getCacheKey() {

                return generateCacheKeyWithParam(super.getCacheKey(), getParams());
            }
        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(
                10000 ,
                3 ,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Singleton.getInstance(context).addToRequestQueue(strReq, url);


    }




    public static void getRequest(final String url,
                                  final Context context, final WebservicesVolleyListener listener) {

        StringRequest strReq = new StringRequest(Request.Method.GET , url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String nresponse) {




                        listener.onResponse(nresponse);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d("VollyError", "Error: " + error.getMessage());
                Log.e("LoginError", error.getMessage() + "Some Error");
                listener.onError(error) ;
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                Log.w("URl","URL >> "+url);

                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


//                params.put("Accept", "application/json");
//                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
            @Override
            public String getCacheKey() {

                return generateCacheKeyWithParam(super.getCacheKey(), getParams());
            }
        };

        Singleton.getInstance(context).addToRequestQueue(strReq, url);


    }



    public static String generateCacheKeyWithParam(String url, Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            url += entry.getKey() + "=" + entry.getValue();
        }
        return url;


    }


    public static void allVollyRequestGet(
            final String url ,

            final Context context,final WebservicesVolleyListener listener)
    {
Log.e("URL",url);
        StringRequest strReq = new StringRequest(Request.Method.GET , url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String nresponse) {




                        listener.onResponse(nresponse);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d("VollyError", "Error: " + error.getMessage());
                Log.e("LoginError", error.getMessage() + "Some Error");
                listener.onError(error) ;
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();





                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


                return params;
            }
//            @Override
//            public String getCacheKey() {
//
//                return generateCacheKeyWithParam(super.getCacheKey(), getParams());
//            }
        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getInstance(context).addToRequestQueue(strReq, url);


    }


    public static void sendCacheRequest(
            final Map<String, String> headersparams,final Map<String, String> params2,
            final String url,
            final Context context,final WebservicesVolleyListener listener) {


        CacheRequest strReq = new CacheRequest( Request.Method.POST, url ,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse nresponse) {

                       // listener.onResponse(nresponse);


                        String response = "";
                        try {


                            response = new String(nresponse.data,
                                    HttpHeaderParser.parseCharset(nresponse.headers));

                            Log.e("Response", response+"");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        listener.onResponse(response);


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d("VollyError", "Error: " + error.getMessage());
                Log.e("LoginError", error.getMessage() + "Some Error");
                listener.onError(error) ;
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params = params2 ;



                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params = headersparams ;

//                params.put("Accept", "application/json");
//                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
            @Override
            public String getCacheKey() {
                return generateCacheKeyWithParam(super.getCacheKey(), getParams());
            }
        };

        Singleton.getInstance(context).addToRequestQueue(strReq, url);


    }



    public static void sendCacheRequestGet(
            final String url,
            final Context context,final WebservicesVolleyListener listener) {


        Log.e("sendCacheRequestGet","URLLLL>>>>>> " + url) ;


        CacheRequest strReq = new CacheRequest( Request.Method.GET, url ,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse nresponse) {

                        // listener.onResponse(nresponse);



                        String response = "";
                        try {


                            response = new String(nresponse.data,
                                    HttpHeaderParser.parseCharset(nresponse.headers));

                            Log.e("Response", response+"");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        listener.onResponse(response);


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d("VollyError", "Error: " + error.getMessage());
                Log.e("LoginError", error.getMessage() + "Some Error");
                listener.onError(error) ;
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();




                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


//                params.put("Accept", "application/json");
//                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
            @Override
            public String getCacheKey() {
                return generateCacheKeyWithParam(super.getCacheKey(), getParams());
            }
        };


        strReq.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Singleton.getInstance(context).addToRequestQueue(strReq, url);


    }





}
