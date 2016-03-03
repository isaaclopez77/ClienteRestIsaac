package com.example.usuario.clienterestisaac;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.usuario.clienterestisaac.adaptador.Adaptador;
import com.example.usuario.clienterestisaac.api.ApiActividad;
import com.example.usuario.clienterestisaac.pojo.Actividad;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by USUARIO on 04/03/2016.
 */
public class VerActividades  extends AppCompatActivity{

    private RecyclerView recView;
    private FloatingActionButton fab;
    private ArrayList<Actividad> datos;
    private Adaptador adaptador;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_actividades);
        datos = new ArrayList<>();

        init();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ieszv.x10.bz/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiActividad api = retrofit.create(ApiActividad.class);
        Call<List<Actividad>> call = api.getActividades();

        call.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Response<List<Actividad>> response, Retrofit retrofit) {
                for (Actividad a : response.body()) {
                    datos.add(a);
                }

                adaptador.notifyDataSetChanged();
                recView.setAdapter(adaptador);
            }

            @Override
            public void onFailure(Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }

    public void init(){
        recView = (RecyclerView) findViewById(R.id.RecView);
        recView.setHasFixedSize(true);
        adaptador = new Adaptador(datos);
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recView.setItemAnimator(new DefaultItemAnimator());
        registerForContextMenu(recView);
    }
}