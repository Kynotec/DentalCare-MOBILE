package com.example.dentalcare.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dentalcare.R;
import com.example.dentalcare.models.Fatura;

import java.util.ArrayList;

public class ListaFaturasAdaptador extends BaseAdapter {

    private Context context;

    private LayoutInflater inflater;

    private ArrayList<Fatura> faturas;

    public ListaFaturasAdaptador(Context context,ArrayList<Fatura> faturas){
        this.context = context;
        this.faturas = faturas;
    }

    @Override
    public int getCount() {
        return faturas.size();
    }

    @Override
    public Object getItem(int i) {
        return faturas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return faturas.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = LayoutInflater.from(context);
        if (view == null)
            view = inflater.inflate(R.layout.item_lista_faturas, null);

        //otimização

        ListaFaturasAdaptador.ViewHolderLista viewHolder = (ListaFaturasAdaptador.ViewHolderLista) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ListaFaturasAdaptador.ViewHolderLista(view);
            view.setTag(viewHolder);
        }
        viewHolder.update(faturas.get(i));

        return view;
    }

    private class ViewHolderLista{
        private TextView tvDataFatura, tvValorTotalFatura, tvIvaTotalFatura, tvSubTotalFatura, tvEstadoFatura;
        public ViewHolderLista(View view){
            tvDataFatura=view.findViewById(R.id.tvIdLinhaFatura);
            tvValorTotalFatura=view.findViewById(R.id.tvValorTotalLinhaFatura);
            tvIvaTotalFatura=view.findViewById(R.id.tvProdutoLinhaFatura);
            tvSubTotalFatura=view.findViewById(R.id.tvServicoLinhaFatura);
            tvEstadoFatura=view.findViewById(R.id.tvQuantidadeLinhaFatura);
        }

        public void update(Fatura fatura){
            tvDataFatura.setText(fatura.getData());
            tvValorTotalFatura.setText(String.format("%.2f€",fatura.getValortotal()));
            tvIvaTotalFatura.setText(String.format("%.2f€",fatura.getIvatotal()));
            tvSubTotalFatura.setText(String.format("%.2f€",fatura.getSubtotal()));
            tvEstadoFatura.setText(fatura.getEstado());
        }
    }

}
