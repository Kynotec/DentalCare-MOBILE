package com.example.dentalcare.adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dentalcare.R;
import com.example.dentalcare.models.Produto;

import java.util.ArrayList;

public class ListaProdutosAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Produto> produtos;


    public ListaProdutosAdaptador(Context context,ArrayList<Produto> produtos){
        this.context = context;
        this.produtos = produtos;
    }


    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int i) {
        return produtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return produtos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.item_lista_produtos, null);

        //otimização

        ViewHolderLista viewHolder = (ViewHolderLista) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolderLista(view);
            view.setTag(viewHolder);
        }
        viewHolder.update(produtos.get(i));

        return view;
    }

        private class ViewHolderLista{
            private TextView tvNomeProduto,tvStock, tvDescricaoProduto, tvPrecoUnitarioProduto;
          //  private ImageView imgCapa;
            public ViewHolderLista(View view){
                tvNomeProduto=view.findViewById(R.id.tvNomeProduto);
                tvDescricaoProduto=view.findViewById(R.id.tvDescricaoProduto);
                tvPrecoUnitarioProduto=view.findViewById(R.id.tvPrecoUnitarioProduto);
                tvStock = view.findViewById(R.id.tvStock);
                //imgCapa = view.findViewById(R.id.imgCapa);
            }

            public void update(Produto produto){
                tvNomeProduto.setText(produto.getNome());
                tvDescricaoProduto.setText(produto.getDescricao());
                tvPrecoUnitarioProduto.setText(String.format("%.2f€", produto.getPrecounitario()));
                if (produto.getStock() != 0) {
                    tvStock.setText(R.string.emStock);
                    tvStock.setTextColor(Color.parseColor("#048000"));
                } else {
                    tvStock.setText(R.string.foraStock);
                    tvStock.setTextColor(Color.parseColor("#b00200"));
                }
                //imgCapa.setImageResource(livro.getCapa());
                /*
                Glide.with(context)
                        .load(livro.getCapa())
                        .placeholder(R.drawable.logoipl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgCapa);

                 */
            }


        }
}
