package com.example.mauriciocantu.prova4iretrofit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import consumer.ClienteConsumer;
import pojo.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveActivity extends Activity {

    private Button btConcluir, btDeletar;
    private EditText etNome, etCpf, etTelefone;
    private RadioGroup rgSexo;
    private RadioButton rbMasc, rbFem;
    private String estados[] = {"RS", "SC", "RJ", "BA"};

    private Spinner spEstados;
    private ArrayAdapter<String> spAdapter;
    private CheckBox cbTecnico, cbSupComp, cbSupIncomp;
    private Cliente cliente;
    private ClienteConsumer clienteConsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        initComps();
        cliente = new Cliente();
        if(getIntent().getStringExtra("acao").equals("cadastrar")){
            btConcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    cadastraDados();

                    clienteConsumer.cadastrar(cliente).enqueue(new Callback<Cliente>() {
                        @Override
                        public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(SaveActivity.this, "Salvo com sucesso", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(SaveActivity.this, "Não foi possível salvar", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Cliente> call, Throwable t) {
                            Toast.makeText(SaveActivity.this, "Falha de conexão", Toast.LENGTH_LONG).show();
                        }
                    });
                    Intent itMenu = new Intent(SaveActivity.this, MainActivity.class);
                    startActivity(itMenu);
                    finish();
                }
            });



        }else{
            this.btDeletar.setVisibility(View.VISIBLE);

            cliente = (Cliente) getIntent().getSerializableExtra("cliente");

            preencheCampos();

            btConcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    cadastraDados();

                    clienteConsumer.atualizar(cliente).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(SaveActivity.this, "Salvo com sucesso", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(SaveActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(SaveActivity.this, "Falha de conexão", Toast.LENGTH_LONG).show();
                        }
                    });
                    Intent itMenu = new Intent(SaveActivity.this, MainActivity.class);
                    startActivity(itMenu);
                    finish();
                }
            });

            btDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clienteConsumer.deletar(cliente.getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(SaveActivity.this, "Deletado", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(SaveActivity.this, "Nao deletou", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(SaveActivity.this, "Falha de conexão", Toast.LENGTH_LONG).show();
                        }
                    });

                    Intent itMenu = new Intent(SaveActivity.this, MainActivity.class);
                    startActivity(itMenu);
                    finish();
                }

            });

        }

    }

    private void preencheCampos(){
        this.etNome.setText(cliente.getNome());
        this.etCpf.setText(cliente.getCpf());
        this.etTelefone.setText(cliente.getTelefone());

        if(cliente.getGenero().equals("Masculino")){
            this.rbMasc.toggle();
        }else{
            this.rbFem.toggle();
        }

        if(cliente.isSuperiorComp()){
            this.cbSupComp.toggle();
        }

        if(cliente.isSuperiorIncomp()){
            this.cbSupIncomp.toggle();
        }

        if(cliente.isTecnico()){
            this.cbTecnico.toggle();
        }

        this.spEstados.setSelection(spAdapter.getPosition(cliente.getEstado()));

    }

    private void initComps(){
        this.btConcluir = findViewById(R.id.bt_concluir);
        this.btDeletar = findViewById(R.id.bt_deletar);
        this.etNome = findViewById(R.id.et_nome);
        this.etCpf = findViewById(R.id.et_cpf);
        this.etTelefone = findViewById(R.id.et_telefone);
        this.rgSexo = findViewById(R.id.rg_sexo);
        this.rbFem = findViewById(R.id.rb_fem);
        this.rbMasc = findViewById(R.id.rb_masc);
        this.spEstados = findViewById(R.id.sp_estados);
        this.spAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, estados);
        this.spEstados.setAdapter(spAdapter);
        this.cbTecnico = findViewById(R.id.cb_tecnico);
        this.cbSupComp = findViewById(R.id.cb_sup_comp);
        this.cbSupIncomp = findViewById(R.id.cb_sup_incomp);
        this.clienteConsumer = new ClienteConsumer();
    }

    private void cadastraDados(){
        cliente.setNome(etNome.getText().toString());
        cliente.setCpf(etCpf.getText().toString());
        cliente.setTelefone(etTelefone.getText().toString());
        cliente.setGenero(
                (rgSexo.getCheckedRadioButtonId() == R.id.rb_masc) ? "Masculino" : "Feminino"
        );

        cliente.setEstado(spEstados.getSelectedItem().toString());

        cliente.setTecnico(
                cbTecnico.isChecked()
        );

        cliente.setSuperiorComp(
                cbSupComp.isChecked()
        );

        cliente.setSuperiorIncomp(
                cbSupIncomp.isChecked()
        );
    }
}
