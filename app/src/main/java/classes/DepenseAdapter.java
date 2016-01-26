package classes;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pcportablevidjay.financesnous.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DepenseAdapter extends ArrayAdapter<Depense> {
    public DepenseAdapter(Context context, ArrayList<Depense> depenses){
        super(context, 0, depenses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

        Depense depense = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.depense_item, parent, false);
        }
        TextView tvNom = (TextView) convertView.findViewById(R.id.tvNom);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvMontant = (TextView) convertView.findViewById(R.id.tvMontant);

        tvNom.setText(depense.getMagasin().getNom_managasin());
        tvDate.setText(formatter.format(depense.getDateDepense()));
        tvMontant.setText(Double.toString(depense.getMontant())+" €");
        return convertView;
    }

}
