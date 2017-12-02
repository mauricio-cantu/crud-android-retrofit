package consumer;

import java.util.List;

import pojo.Cliente;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mauriciocantu on 30/11/17.
 */

public class ClienteConsumer {

    private Retrofit retrofit;
    private IClienteService clienteService;

    public ClienteConsumer(){
        this.retrofit = new Retrofit.
                Builder().
                baseUrl(IClienteService.URL_BASE).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        this.clienteService = retrofit.create(IClienteService.class);
    }

    public Call<Cliente> cadastrar(Cliente c){
        return this.clienteService.cadastrar(c);
    }

    public Call<List<Cliente>> buscar(){
        return this.clienteService.buscar();
    }

    public Call<Void> atualizar(Cliente c){
        return this.clienteService.atualizar(c);
    }

    public Call<Void> deletar(long id){
        return this.clienteService.deletar(id);
    }
}
