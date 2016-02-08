package com.dam.salesianostriana.pmdm.killduck;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by flopez on 08/02/2016.
 */
public class Servicio {

    public static Api pedirServicio(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rest.miguelcr.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api service = retrofit.create(Api.class);
        return service;
    }
}
