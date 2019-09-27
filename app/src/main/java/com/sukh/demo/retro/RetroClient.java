package com.sukh.demo.retro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sukh.demo.webservices.common.Constants.BASEURL;


/**
 * Created by Androgo on 06 Sep 2018
 */

public class RetroClient {

    /********
     * URLS
     *******/


    /**
     * Get Retrofit Instance
     */
    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIInterface getAPIService() {

        return getRetrofitInstance().create(APIInterface.class);
    }


}