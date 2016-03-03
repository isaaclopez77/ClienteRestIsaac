package com.example.usuario.clienterestisaac.adaptador;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.clienterestisaac.R;
import com.example.usuario.clienterestisaac.pojo.Actividad;

import java.util.ArrayList;

/**
 * Created by USUARIO on 04/03/2016.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private View.OnContextClickListener contextClickListener;
    private ArrayList<Actividad> datos;
    private int position;

    @Override
    public Adaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        final ViewHolder tvh = new ViewHolder(itemView);
        itemView.setOnClickListener(this);
        //android:background="?android:attr/selectableItemBackground"


        tvh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(tvh.getAdapterPosition());
                Log.v("HOLAAAAAAA", tvh.getAdapterPosition() + "");

                return false;
            }
        });
        return tvh;
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);    }

    @Override
    public void onBindViewHolder(Adaptador.ViewHolder holder, int position) {
        Actividad item = datos.get(position);


        holder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Adaptador(ArrayList<Actividad> datos) {
        this.datos = datos;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }


    public static class ViewHolder
            extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private TextView txtDes;
        private TextView txtTipo;
        private TextView txtLugar;
        private TextView txtFecha;

        public ViewHolder(View itemView) {
            super(itemView);

            txtDes = (TextView) itemView.findViewById(R.id.tvDescripcion);
            txtFecha = (TextView) itemView.findViewById(R.id.tvfecha);
            txtLugar = (TextView) itemView.findViewById(R.id.tvLugar);
            txtTipo = (TextView) itemView.findViewById(R.id.tvTipo);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bindTitular(Actividad t) {
            txtDes.setText(t.getDescripcion());
            txtFecha.setText(t.getFechai());
            txtLugar.setText(t.getLugari());
            txtTipo.setText(t.getTipo());
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Opciones");
            menu.add(0, v.getId(), 0, "Editar");//groupId, itemId, order, title
            menu.add(0, v.getId(), 0, "Borrar");
        }
    }


}
