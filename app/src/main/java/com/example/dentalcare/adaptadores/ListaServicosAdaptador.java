package com.example.dentalcare.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dentalcare.R;
import com.example.dentalcare.models.Produto;
import com.example.dentalcare.models.Servico;

import java.util.ArrayList;

public class ListaServicosAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Servico> servicos;


    public ListaServicosAdaptador(Context context,ArrayList<Servico> servicos){
        this.context = context;
        this.servicos = servicos;
    }


    @Override
    public int getCount() {
        return servicos.size();
    }

    @Override
    public Object getItem(int i) {
        return servicos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return servicos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.item_lista_servicos, null);

        //otimização

        ViewHolderLista viewHolder = (ViewHolderLista) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolderLista(view);
            view.setTag(viewHolder);
        }
        viewHolder.update(servicos.get(i));

        return view;
    }

    private class ViewHolderLista{
        private TextView tvNome, tvPrecoServico, tvTaxaIva;
        //  private ImageView imgCapa;
        public ViewHolderLista(View view){
            tvNome=view.findViewById(R.id.tvNomeServico);
            tvTaxaIva=view.findViewById(R.id.tvTaxaIva);
            tvPrecoServico=view.findViewById(R.id.tvPrecoServico);

            //imgCapa = view.findViewById(R.id.imgCapa);
        }

        public void update(Servico servico){
            tvNome.setText(servico.getNome());
            tvTaxaIva.setText(String.valueOf(servico.getIvaspercentagem()) + "%");
            tvPrecoServico.setText(String.format("%.2f€", servico.getPreco()));

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
