package com.example.mauriciocantu.prova4iretrofit;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import consumer.ClienteConsumer;
import pojo.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListItemsActivity extends ListActivity {

    private List<Cliente> listaClientes;
    private ArrayAdapter<Cliente> adapterClientes;
    private ClienteConsumer clienteConsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComps();
    }

    private void initComps(){
        clienteConsumer = new ClienteConsumer();
        pegaListaClientes();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent itEdit = new Intent(getApplicationContext(), SaveActivity.class);
        itEdit.putExtra("acao", "editar");
        itEdit.putExtra("cliente", listaClientes.get(position));
        startActivity(itEdit);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pegaListaClientes();
    }

    private void pegaListaClientes(){
        clienteConsumer.buscar().enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if(response.isSuccessful()){
                    listaClientes = response.body();
                    adapterClientes = new ArrayAdapter<Cliente>(ListItemsActivity.this, android.R.layout.simple_list_item_1, listaClientes);
                    setListAdapter(adapterClientes);
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {

            }
        });
    }
}
