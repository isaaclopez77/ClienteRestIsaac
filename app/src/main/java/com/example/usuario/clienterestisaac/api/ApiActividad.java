package com.example.usuario.clienterestisaac.api;


import com.example.usuario.clienterestisaac.pojo.Actividad;


import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiActividad {
    @POST("/restful/api/actividad/isaac")
    Call<Actividad> crearActividad(@Body Actividad actividad);
    @GET("restful/api/actividad/isaac")
    Call<List<Actividad>> getActividades();
}
