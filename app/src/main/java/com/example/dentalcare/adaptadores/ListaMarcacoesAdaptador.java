package com.example.dentalcare.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dentalcare.R;
import com.example.dentalcare.models.Consulta;

import java.util.ArrayList;

public class ListaMarcacoesAdaptador extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Consulta> marcacoes;

    public ListaMarcacoesAdaptador(Context context,ArrayList<Consulta> marcacoes){
        this.context = context;
        this.marcacoes = marcacoes;
    }

    @Override
    public int getCount() { return marcacoes.size();}

    @Override
    public Object getItem(int i) { return marcacoes.get(i); }

    @Override
    public long getItemId(int i) { return marcacoes.get(i).getId(); }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.item_lista_marcacoes, null);

        //otimização

        ListaMarcacoesAdaptador.ViewHolderLista viewHolder = (ListaMarcacoesAdaptador.ViewHolderLista) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ListaMarcacoesAdaptador.ViewHolderLista(view);
            view.setTag(viewHolder);
        }
        viewHolder.update(marcacoes.get(i));

        return view;
    }

    private class ViewHolderLista{
        private TextView tvHoraMarcacao,tvDataMarcacao, tvEstadoMarcacao;
        public ViewHolderLista(View view){
            tvHoraMarcacao=view.findViewById(R.id.tvHoraMarcacao);
            tvDataMarcacao=view.findViewById(R.id.tvDataMarcacao);
            tvEstadoMarcacao = view.findViewById(R.id.tvEstadoMarcacao);
        }

        public void update(Consulta marcacao){
            tvHoraMarcacao.setText(marcacao.getHora());
            tvDataMarcacao.setText(marcacao.getData());
            tvEstadoMarcacao.setText(marcacao.getEstado());
        }
    }
}
