package com.example.dentalcare.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dentalcare.R;
import com.example.dentalcare.models.Fatura;
import com.example.dentalcare.models.Linha_fatura;

import java.util.ArrayList;

public class ListaLinhaFaturasAdaptador extends BaseAdapter {

    private Context context;

    private LayoutInflater inflater;

    private ArrayList<Linha_fatura> linha_faturas;

    public ListaLinhaFaturasAdaptador(Context context,ArrayList<Linha_fatura> linha_faturas){
        this.context = context;
        this.linha_faturas = linha_faturas;
    }

    @Override
    public int getCount() {
        return linha_faturas.size();
    }

    @Override
    public Object getItem(int position) {
        return linha_faturas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return linha_faturas.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.item_lista_linhafaturas, null);

        //otimização

        ListaLinhaFaturasAdaptador.ViewHolderLista viewHolder = (ListaLinhaFaturasAdaptador.ViewHolderLista) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ListaLinhaFaturasAdaptador.ViewHolderLista(view);
            view.setTag(viewHolder);
        }
        viewHolder.update(linha_faturas.get(position));

        return view;
    }
    private class ViewHolderLista {
        private TextView tvQuantidadeLinhaFatura, tvProdutoLinhaFatura, tvServicoLinhaFatura, tvValorTotalLinhaFatura;

        public ViewHolderLista(View view) {
            tvQuantidadeLinhaFatura = view.findViewById(R.id.tvQuantidadeLinhaFatura);
            tvProdutoLinhaFatura = view.findViewById(R.id.tvProdutoLinhaFatura);
            tvServicoLinhaFatura = view.findViewById(R.id.tvServicoLinhaFatura);
            tvValorTotalLinhaFatura = view.findViewById(R.id.tvValorTotalLinhaFatura);
        }

        public void update(Linha_fatura linhaFatura) {
            tvQuantidadeLinhaFatura.setText(""+linhaFatura.getQuantidade());
            tvProdutoLinhaFatura.setText(String.valueOf(linhaFatura.getProdutonome()));
            tvServicoLinhaFatura.setText(String.valueOf(linhaFatura.getServiconome()));
            tvValorTotalLinhaFatura.setText(String.format("%.2f€", linhaFatura.getValortotal()));

        }
    }
}
