package consumer;

import java.util.List;

import pojo.Cliente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by mauriciocantu on 30/11/17.
 */

public interface IClienteService {

    String URL_BASE = "http://192.168.240.200:8080/compubras/";

    @POST("cliente2/")
    Call<Cliente> cadastrar(@Body Cliente c);

    @GET("cliente2/")
    Call<List<Cliente>> buscar();

    @PUT("cliente2/")
    Call<Void> atualizar(@Body Cliente c);

    @DELETE("cliente2/{id}")
    Call<Void> deletar(@Path("id") long id);
}
