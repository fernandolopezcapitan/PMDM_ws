package com.dam.salesianostriana.pmdm.killduck;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by flopez on 08/02/2016.
 */
public interface Api {

    // Petición ME
    @GET("/killduck/me?")
    Call<Integer> obtenerMe(@Query("nickname") String nickname);

    // Petición RANKING
    @GET("/killduck/ranking")
    Call<ArrayList<Usuario>> obtenerRanking();

    // Petición REGISTER
    @GET("/killduck/register?")
    Call<String> obtenerRegister(@Query("nickname") String nickname);

    // Petición USER
    @GET("/killduck/user?")
    Call<Usuario> obtenerUser(@Query("nickname") String nickname,@Query ("points") String points);



}
