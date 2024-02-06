package com.example.dentalcare.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dentalcare.R;
import com.example.dentalcare.listeners.RemoverLinhaCarrinhoListener;
import com.example.dentalcare.models.Linha_carrinho;
import com.example.dentalcare.models.SingletonGestorApp;

import java.util.ArrayList;

public class ListaLinhaCarrinhoAdaptador  extends BaseAdapter implements RemoverLinhaCarrinhoListener {

    private Context context;

    private LayoutInflater inflater;

    private Button RemoverCarrinhoProduto;

    private ArrayList<Linha_carrinho> linha_carrinhos;

    public ListaLinhaCarrinhoAdaptador(Context context,ArrayList<Linha_carrinho> linha_carrinhos){
        this.context = context;
        this.linha_carrinhos = linha_carrinhos;
    }



    @Override
    public int getCount() {
        return linha_carrinhos.size();
    }

    @Override
    public Object getItem(int i) {
        return linha_carrinhos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return linha_carrinhos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = LayoutInflater.from(context);
        if (view == null)
            view = inflater.inflate(R.layout.item_lista_carrinhos, null);

        SingletonGestorApp.getInstance(view.getContext()).setRemoverLinhaCarrinhoListener(this);
        //otimização

        RemoverCarrinhoProduto = view.findViewById(R.id.RemoverCarrinhoProduto);

        ListaLinhaCarrinhoAdaptador.ViewHolderLista viewHolder = (ListaLinhaCarrinhoAdaptador.ViewHolderLista) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ListaLinhaCarrinhoAdaptador.ViewHolderLista(view);
            view.setTag(viewHolder);
        }
        viewHolder.update(linha_carrinhos.get(i));

        RemoverCarrinhoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SingletonGestorApp.getInstance(view.getContext()).removerlinhaCarrinhoAPI(linha_carrinhos.get(i).getId(), context.getApplicationContext());
                linha_carrinhos.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    private void showToast(String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onRefreshLinhaCarrinho(boolean temlinha) {
        if (temlinha){
            showToast("Produto removido com sucesso!");
        }else{
            showToast("Erro ao remover o produto!");
        }
    }

    private class ViewHolderLista{
        private TextView tvNomeProduto,tvQuantidadeProduto,tvValorTotalProduto;
        private ImageView imgCapa;
        public ViewHolderLista(View view){
            tvNomeProduto=view.findViewById(R.id.tvNomeProduto);
            tvQuantidadeProduto=view.findViewById(R.id.tvQuantidadeProduto);
            tvValorTotalProduto=view.findViewById(R.id.tvValorTotalProduto);
            imgCapa = view.findViewById(R.id.imgProduto);
        }



        public void update(Linha_carrinho linhaCarrinho){
            tvNomeProduto.setText(linhaCarrinho.getNome());
            tvQuantidadeProduto.setText((""+ linhaCarrinho.getQuantidade()));
            tvValorTotalProduto.setText(String.format("%.2f€", linhaCarrinho.getValortotal()));


            Glide.with(context)
                    .load(linhaCarrinho.getImagem())
                    .placeholder(R.drawable.dentalcare_logo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgCapa);
        }
    }
    public void clearCarrinho() {
        if (linha_carrinhos != null) {
            linha_carrinhos.clear();
            notifyDataSetChanged();
            showToast("A sua compra foi realizada com sucesso!");
        }
    }

}
