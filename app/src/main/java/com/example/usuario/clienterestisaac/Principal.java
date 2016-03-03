package com.example.usuario.clienterestisaac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.usuario.clienterestisaac.api.ApiActividad;
import com.example.usuario.clienterestisaac.pojo.Actividad;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Principal extends AppCompatActivity {

    private EditText etID, etFecha1, etHora1, etLugar1, etHora2, etFecha2, etLugar2, etDescripcion, etAlumno;
    private RadioGroup radio;
    private String profesorId, tipo, fecha1, fecha2,lugar1,lugar2, descripcion, alumno;
    private Button btnOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        init();
    }

    public void init() {
        etID = (EditText) findViewById(R.id.idProfesor);
        etFecha1 = (EditText) findViewById(R.id.fecha1);
        etFecha2 = (EditText) findViewById(R.id.fecha2);
        etHora1 = (EditText) findViewById(R.id.hora1);
        etHora2 = (EditText) findViewById(R.id.hora2);
        etLugar1 = (EditText) findViewById(R.id.lugar1);
        etLugar2 = (EditText) findViewById(R.id.lugar2);
        etDescripcion = (EditText) findViewById(R.id.descripcion);
        etAlumno = (EditText) findViewById(R.id.alumno);
        radio = (RadioGroup) findViewById(R.id.rgroup);
        btnOk =(Button)findViewById(R.id.btn);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recoger valores
                if (!etID.getText().equals("")) {
                    profesorId = etID.getText().toString();
                }

                int id = radio.getCheckedRadioButtonId();
                if (id == R.id.rComplementaria) {
                    tipo = "complementaria";
                } else if (id == R.id.rExtraescolar) {
                    tipo = "extraescolar";
                }

                if (!etFecha1.getText().equals("") && !etHora1.getText().equals("")) {
                    fecha1 = etFecha1.getText().toString() + " " + etHora1.getText().toString();
                }

                if (!etFecha2.getText().equals("") && !etHora2.getText().equals("")) {
                    fecha2 = etFecha2.getText().toString() + " " + etHora2.getText().toString();
                }

                if (!etLugar1.getText().equals("")) {
                    lugar1 = etLugar1.getText().toString();
                }

                if (!etLugar2.getText().equals("")) {
                    lugar2 = etLugar2.getText().toString();
                }

                if (!etDescripcion.getText().equals("")) {
                    descripcion = etDescripcion.getText().toString();
                }

                if (!etAlumno.getText().equals("")) {
                    alumno = etAlumno.getText().toString();
                }

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://ieszv.x10.bz/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiActividad api = retrofit.create(ApiActividad.class);
                Actividad ac = new Actividad(profesorId, tipo, fecha1, fecha2, lugar1, lugar2, descripcion, alumno);
                Call<Actividad> call = api.crearActividad(ac);
                call.enqueue(new Callback<Actividad>() {
                    @Override
                    public void onResponse(Response<Actividad> response, Retrofit retrofit) {
                        Toast.makeText(getApplicationContext(), "CREADA", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getApplicationContext(), "NO CREADA", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void verActividades(View v){
        Intent i =new Intent(this,VerActividades.class);
        startActivity(i);
    }

    }

