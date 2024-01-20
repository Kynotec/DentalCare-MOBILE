package com.example.dentalcare.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalcare.CarrinhoActivity;
import com.example.dentalcare.DetalhesFaturasActivity;
import com.example.dentalcare.MenuMainActivity;
import com.example.dentalcare.R;
import com.example.dentalcare.listeners.CarrinhosListener;
import com.example.dentalcare.listeners.DetalhesDiagnosticoListener;
import com.example.dentalcare.listeners.DetalhesMarcacaoListener;
import com.example.dentalcare.listeners.DetalhesProdutoListener;
import com.example.dentalcare.listeners.DetalhesServicoListener;
import com.example.dentalcare.listeners.DiagnosticoListener;
import com.example.dentalcare.listeners.FaturaListener;
import com.example.dentalcare.listeners.LinhaFaturaListener;
import com.example.dentalcare.listeners.LoginListener;
import com.example.dentalcare.listeners.MarcacaoListener;
import com.example.dentalcare.listeners.PerfilListener;
import com.example.dentalcare.listeners.ProdutosListener;
import com.example.dentalcare.listeners.ServicosListener;
import com.example.dentalcare.utils.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingletonGestorApp {

    private String ipAddress;  // Variável para armazenar o endereço IP

    private int fatura_id;

    private ArrayList<Produto> produtos;
    private ArrayList<Servico> servicos;

    private ArrayList<Consulta> marcacoes;
    private Perfil perfils;

    private ArrayList<Diagnostico> diagnosticos;

    private ArrayList<Fatura> faturas;

    private ArrayList<Linha_fatura> linha_faturas;

    private static SingletonGestorApp instance = null;
    private static RequestQueue volleyQueue = null;
    private LoginListener loginListener;
    private PerfilListener perfilListener;
    private ProdutosListener produtosListener;
    private ServicosListener servicosListener;
    private DiagnosticoListener diagnosticosListener;
    private FaturaListener faturasListener;
    private LinhaFaturaListener linhafaturasListener;

    private MarcacaoListener marcacoesListener;
    private DetalhesMarcacaoListener marcacaoListener;
    private DetalhesServicoListener detalhesServicoListener;
    private DetalhesProdutoListener detalhesProdutoListener;
    private DetalhesFaturasActivity detalhesFaturaListener;
    private DetalhesDiagnosticoListener detalhesDiagnosticoListener;

    private DetalhesMarcacaoListener detalhesMarcacaoListener;

    private CarrinhosListener carrinhosListener;
    private BDHelper BD;

    private BDHelper marcacoesBD;



    public static synchronized SingletonGestorApp getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonGestorApp(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }

    public SingletonGestorApp(Context context) {
        BD = new BDHelper(context);

        produtos = new ArrayList<>();
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }
    public void setCarrinhosListener(CarrinhosListener carrinhosListener) {
        this.carrinhosListener = carrinhosListener;
    }

    public void setServicosListener(ServicosListener servicosListener){
        this.servicosListener = servicosListener;
    }

    public void setDiagnosticosListener(DiagnosticoListener diagnosticosListener){
        this.diagnosticosListener = diagnosticosListener;
    }

    public void setFaturasListener(FaturaListener faturasListener){
        this.faturasListener = faturasListener;
    }

    public void setLinhaFaturasListener(LinhaFaturaListener linhafaturasListener){
        this.linhafaturasListener = linhafaturasListener;
    }

    public void setPerfilListener(PerfilListener perfilListener) {
        this.perfilListener = perfilListener;
    }

    public void setDetalhesServicoListener(DetalhesServicoListener detalhesServicoListener) {
        this.detalhesServicoListener = detalhesServicoListener;
    }
    public void setDetalhesDiagnosticoListener(DetalhesDiagnosticoListener detalhesDiagnosticoListener) {
        this.detalhesDiagnosticoListener = detalhesDiagnosticoListener;
    }

    public void setProdutosListener(ProdutosListener produtosListener){
        this.produtosListener = produtosListener;
    }
    public void setDetalhesProdutoListener(DetalhesProdutoListener detalhesProdutoListener) {
        this.detalhesProdutoListener = detalhesProdutoListener;
    }

    public void setDetalhesFaturaListener(DetalhesFaturasActivity detalhesFaturaListener) {
        this.detalhesFaturaListener = detalhesFaturaListener;
    }

    public void setMarcacoesListener(MarcacaoListener marcacoesListener){
        this.marcacoesListener = marcacoesListener;
    }

    public void setMarcacaoListener(DetalhesMarcacaoListener marcacaoListener){
        this.marcacaoListener = marcacaoListener;
    }


    public void setDetalhesMarcacaoListener(DetalhesMarcacaoListener detalhesMarcacaoListener) {
        this.detalhesMarcacaoListener = detalhesMarcacaoListener;

    }

    //Adiciona o IP no Singleton
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;

    }
    //Buscar o IP
    public String getIpAddress() {
        return ipAddress;
    }

    public Servico getServico(int id) {
        for (Servico s : servicos) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    public Produto getProduto(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }
    public Fatura getFatura(int id) {
        for (Fatura s : faturas) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    public Consulta getMarcacao(int id) {
        for (Consulta c : marcacoes) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }
    public Linha_fatura getLinhaFatura(int id) {
        for (Linha_fatura s : linha_faturas) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    public Diagnostico getDiagnostico(int id) {
        for (Diagnostico d : diagnosticos) {
            if (d.getId() == id)
                return d;
        }
        return null;
    }
    public void removerMarcacaoBD(int idMarcacao) {
        marcacoesBD.removerMarcacaoBD(idMarcacao);
    }

    public void editarMarcacaoBD(Consulta marcacao) {

        if (marcacao != null)
            marcacoesBD.editarMarcacaoBD(marcacao);
    }
    public void loginAPI(final String username, final String password, final Context context) {
        // Obter o endereço IP armazenado nas SharedPreferences
        String ipAddress = SingletonGestorApp.getInstance(context).getIpAddress();

        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Sem ligação à internet", Toast.LENGTH_LONG).show();
        } else {
            // Substituir o valor fixo de APILogin pelo endereço IP
            final String APILoginWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/auth/login";

            StringRequest req = new StringRequest(Request.Method.GET, APILoginWithIP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject loginJSON = new JSONObject(response);
                        String token = JsonParser.parserJsonLogin(response);
                        String username = JsonParser.parserJsonUsername(response);
                        int id = loginJSON.getInt("user_id");
                        SharedPreferences sharedToken = context.getSharedPreferences("DADOS_USER", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedToken.edit();

                        editor.putInt("id", id);
                        editor.apply();

                        if (loginListener != null)
                            loginListener.onValidateLogin(token, username, context);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Username ou password incorreto", Toast.LENGTH_LONG).show();
                    VolleyLog.d("LOGIN: Error" + error.getMessage());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    String credentials = username + ":" + password;
                    String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Basic " + base64EncodedCredentials);
                    return headers;
                }
            };
            volleyQueue.add(req);
        }
    }

    public void getPerfilAPI(final Context context, String token) {
        if(!JsonParser.isConnectionInternet(context)){
            Toast.makeText(context, context.getString(R.string.sem_ligacao), Toast.LENGTH_SHORT).show();
        }else {
            if (token != null && !token.trim().isEmpty()) {
                // Make the API request
            } else {
                // Handle the case when the token is null or empty
                Log.e("API_ERROR", "Invalid access token");
                // Add appropriate handling or notify the user
            }


            final String APIPerfilWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/user";
            StringRequest req = new StringRequest(Request.Method.GET, APIPerfilWithIP + "/get-perfil?=" + token,  new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    perfils = JsonParser.parserJsonPerfil(response);
                    if(perfilListener !=null)
                        perfilListener.onShowPerfil(perfils);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("TOKEN_DEBUG", "Access token: " + token);
                    Log.d("API_REQUEST_DEBUG", "API URL: " + APIPerfilWithIP + "/get-perfil?=" + token);

                    Toast.makeText(context, "Error during API request", Toast.LENGTH_LONG).show();
                }
            });

            volleyQueue.add(req);
        }
    }

    public void editPerfilAPI(final Perfil perfil, final Context context, String token) {
        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, context.getString(R.string.sem_ligacao), Toast.LENGTH_SHORT).show();
        } else {
            final String APIPerfilWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/user";
            StringRequest req = new StringRequest(Request.Method.PUT, APIPerfilWithIP + "/atualizar-perfil?=" + token,  new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (perfilListener != null)
                        perfilListener.onShowPerfil(perfil);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("nome", perfil.getNome());
                    params.put("telefone", perfil.getTelefone() + "");
                    params.put("nif", perfil.getNif() + "");
                    params.put("codigopostal", perfil.getCodigopostal() + "");
                    params.put("morada", perfil.getMorada());
                    return params;
                }
            };
            volleyQueue.add(req);
        }

    }


    public ArrayList<Produto> getProdutosBD(){
        produtos = BD.getAllProdutosBD();
        return new ArrayList(produtos);
    }

    public void adicionarProdutosBD(ArrayList<Produto> produtos)
    {
        BD.removerAllProdutos();
        for(Produto a:produtos)
        {
            adicionarProdutoBD(a);
        }
    }
    public void adicionarProdutoBD(Produto a)
    {
        BD.adcionarProdutoBD(a);
    }

    public void getAllProdutosAPI(final Context context) {
        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, context.getString(R.string.sem_ligacao), Toast.LENGTH_SHORT).show();
            if (produtosListener != null) {
                // Carrega produtos do banco de dados local
                produtosListener.onRefreshListaProdutos(BD.getAllProdutosBD());
            }
        } else {
            final String APIProdutoWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/produto/produtosimagem";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIProdutoWithIP, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    produtos = JsonParser.parserJsonProdutos(response);
                    adicionarProdutosBD(produtos);
                    if (produtosListener != null) {
                        produtosListener.onRefreshListaProdutos(produtos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, R.string.a_carregar_produtos, Toast.LENGTH_SHORT).show();

                    if (produtosListener != null) {
                        // Em caso de falha na API, carrega produtos do banco de dados local
                        produtosListener.onRefreshListaProdutos(BD.getAllProdutosBD());
                    }
                }
            });

            volleyQueue.add(jsonArrayRequest);
        }
    }


    public ArrayList<Servico> getServicosBD() { // return da copia dos Servicos
        servicos=BD.getAllServicosBD();
        return new ArrayList(servicos);
    }

    public void adicionarServicosBD(ArrayList<Servico> servicos)
    {
        BD.removerAllServicos();
        for(Servico a:servicos)
        {
            adicionarServicoBD(a);
        }
    }

    public void adicionarServicoBD(Servico a)
    {
        BD.adcionarServicoBD(a);
    }



    public void getAllServicosAPI(final Context context) {
        if(!JsonParser.isConnectionInternet(context)){
            Toast.makeText(context, context.getString(R.string.sem_ligacao), Toast.LENGTH_SHORT).show();
        }else {

            final String APIServicoWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/servico/get-iva";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIServicoWithIP, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    servicos = JsonParser.parserJsonServicos(response);
                    adicionarServicosBD(servicos);
                    if (servicosListener != null) {
                        servicosListener.onRefreshListaServicos(servicos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, R.string.a_carregar_servicos, Toast.LENGTH_SHORT).show();

                    if (servicosListener != null) {
                        // Em caso de falha na API, carrega produtos do banco de dados local
                        servicosListener.onRefreshListaServicos(BD.getAllServicosBD());
                    }
                }
            });
            volleyQueue.add(jsonArrayRequest);
        }
    }



    public void getAllDiagnosticosAPI(final Context context, String token) {
        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Sem ligação á internet", Toast.LENGTH_LONG).show();
        } else {

            final String APIDiagnosticoWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/diagnostico";
            String url = APIDiagnosticoWithIP + "/get-perfil-diagnostico?token=" + token;

            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    diagnosticos = JsonParser.parserJsonDiagnosticos(response);

                    if (diagnosticosListener != null) {
                        diagnosticosListener.onRefreshListaDiagnosticos(diagnosticos);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            volleyQueue.add(req);
        }
    }
    public void getAllFaturasAPI(final Context context, String token) {
        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Sem ligação á internet", Toast.LENGTH_LONG).show();
        } else {

            final String APIFaturaWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/fatura";
            String url = APIFaturaWithIP + "/get-perfil-fatura?token=" + token;

            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    faturas = JsonParser.parserJsonFaturas(response);

                    if (faturasListener != null) {
                        faturasListener.onRefreshListaFaturas(faturas);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            volleyQueue.add(req);
        }
    }

    public void getAllLinhaFaturasAPI(final Context context, String token) {
        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Sem ligação á internet", Toast.LENGTH_LONG).show();
        } else {

            // Recuperar o ID do SharedPreferences
            SharedPreferences sharedFatura = context.getSharedPreferences("FATURA_ID", Context.MODE_PRIVATE);
            int idRecuperado = sharedFatura.getInt("id", 0); // Substitua DEFAULT_VALUE pelo valor padrão a ser usado caso o ID não exista

            final String APILinhaFaturaWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/linhafatura";
            String url = APILinhaFaturaWithIP + "/linha/"+idRecuperado+"?token=" + token;

            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    linha_faturas = JsonParser.parserJsonLinhaFaturas(response);

                    if (linhafaturasListener != null) {
                        linhafaturasListener.onRefreshLinhaFaturas(linha_faturas);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            volleyQueue.add(req);
        }
    }



    public void addCarrinhoAPI(final Context context,final Produto produto,int quantidade) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
         String token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);
        SharedPreferences preferences = context.getSharedPreferences("DADOS_USER", Context.MODE_PRIVATE);
        int id= preferences.getInt("id",0);
        if(!JsonParser.isConnectionInternet(context)){
            Toast.makeText(context, context.getString(R.string.sem_ligacao), Toast.LENGTH_SHORT).show();
        }else {

            final String APICarrinhoWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/carrinho";
            String urlCarrinho = APICarrinhoWithIP + "/adicionar/" + produto.getId() + "/user/" + id +"?token=" + token;

            StringRequest StringRequest = new StringRequest(Request.Method.PUT, urlCarrinho, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if(carrinhosListener !=null)
                    {
                        carrinhosListener.onRefreshDetalhes(CarrinhoActivity.ADD);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            })
            {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("quantidade", String.valueOf(quantidade));
                    return params;
                }
            }
                    ;

            volleyQueue.add(StringRequest);
        }
    }


    public void viewCarrinhoAPI(final Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);
        SharedPreferences preferences = context.getSharedPreferences("DADOS_USER", Context.MODE_PRIVATE);
        int id= preferences.getInt("id",0);
        if(!JsonParser.isConnectionInternet(context)){
            Toast.makeText(context, context.getString(R.string.sem_ligacao), Toast.LENGTH_SHORT).show();
        }else {

            final String APICarrinhoWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/carrinho";
            String urlCarrinho = APICarrinhoWithIP + "/buscarcarrinho/" + "/user/" + id +"?token=" + token;

            JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlCarrinho, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    ArrayList<Linha_carrinho> linhaCarrinhos = JsonParser.parserJsonCarrinho(response);
                   // if()


                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });


            volleyQueue.add(JsonArrayRequest);
        }
    }
    //Marcações
    public void getAllMarcacoesAPI(final Context context, String token) {
        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Sem ligação á internet", Toast.LENGTH_LONG).show();
        } else {

            final String APIMarcacaoWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/consulta";
            String url = APIMarcacaoWithIP + "/get-marcacao-perfil?token=" + token;

            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    marcacoes = JsonParser.parserJsonMarcacoes(response);

                    if (marcacoesListener != null) {
                        marcacoesListener.onRefreshListaMarcacoes(marcacoes);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            volleyQueue.add(req);
        }
    }

    public void removerMarcacaoAPI(final Consulta marcacao, final Context context, String token) {

        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Nao têm ligação à internet", Toast.LENGTH_SHORT).show();
        } else {
            final String APIMarcacaoWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/consulta";
            StringRequest request = new StringRequest(Request.Method.DELETE, APIMarcacaoWithIP + "/" + marcacao.getId() + "?access-token=" + token, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //1-remover BD Local
                    //removerMarcacaoBD(marcacao.getId());

                    //TODO 2- informar a vista
                    if(marcacaoListener !=null){
                        marcacaoListener.onRefreshDetalhes(MenuMainActivity.DELETE);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(request);
        }

    }

    public void editarMarcacaoAPI(final Consulta marcacao, final Context context, String token) {
        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Nao têm ligação à internet", Toast.LENGTH_SHORT).show();
        } else {

            final String APIMarcacaoWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/consulta";
            StringRequest request = new StringRequest(Request.Method.PUT, APIMarcacaoWithIP + "/" + marcacao.getId() + "?access-token=" + token, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //1-editar BD Local
                    //editarMarcacaoBD(marcacao);
                    //TODO 2- informar a vista
                    if (marcacaoListener != null) {
                        marcacaoListener.onRefreshDetalhes(MenuMainActivity.EDIT);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("token", token);
                    params.put("descricao", marcacao.getDescricao());
                    params.put("data", marcacao.getData());
                    params.put("hora", marcacao.getHora());
                    params.put("estado", marcacao.getEstado());
                    params.put("servico_id", marcacao.getNomeservico());

                    return params;
                }
            };
            volleyQueue.add(request);
        }
    }

}
