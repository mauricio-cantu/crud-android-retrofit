package com.example.mauriciocantu.prova4iretrofit;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity{

    private String opts[] = {"Cadastrar", "Listar"};
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComps();
        setListAdapter(listAdapter);
    }

    private void initComps(){
        this.listAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, opts);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if(position == 0){
            Intent itCreate = new Intent(getApplicationContext(), SaveActivity.class);
            itCreate.putExtra("acao", "cadastrar");
            startActivity(itCreate);
        }

        if(position == 1){
            Intent itList = new Intent(getApplicationContext(), ListItemsActivity.class);
            startActivity(itList);
        }
    }
}
