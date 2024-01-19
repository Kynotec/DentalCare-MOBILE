package com.example.dentalcare.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dentalcare.R;
import com.example.dentalcare.models.Diagnostico;
import com.example.dentalcare.models.Produto;

import java.util.ArrayList;

public class ListaDiagnosticosAdaptador extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Diagnostico> diagnosticos;


    public ListaDiagnosticosAdaptador(Context context,ArrayList<Diagnostico> diagnosticos){
        this.context = context;
        this.diagnosticos = diagnosticos;
    }


    @Override
    public int getCount() {
        return diagnosticos.size();
    }

    @Override
    public Object getItem(int i) {
        return diagnosticos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return diagnosticos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.item_lista_diagnosticos, null);

        //otimização

        ListaDiagnosticosAdaptador.ViewHolderLista viewHolder = (ListaDiagnosticosAdaptador.ViewHolderLista) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ListaDiagnosticosAdaptador.ViewHolderLista(view);
            view.setTag(viewHolder);
        }
        viewHolder.update(diagnosticos.get(i));

        return view;
    }

    private class ViewHolderLista{
        private TextView tvDataDiagnostico, tvHoraDiagnostico;
        public ViewHolderLista(View view){
            tvDataDiagnostico=view.findViewById(R.id.tvDataDiagnostico);
            tvHoraDiagnostico=view.findViewById(R.id.tvHoraDiagnostico);
        }

        public void update(Diagnostico diagnostico){
            tvDataDiagnostico.setText(diagnostico.getData());
            tvHoraDiagnostico.setText(diagnostico.getHora());
        }
    }
}
