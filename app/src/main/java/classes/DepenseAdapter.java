package classes;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pcportablevidjay.financesnous.DepenseDetail_Activity;
import com.example.pcportablevidjay.financesnous.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DepenseAdapter extends ArrayAdapter<Depense> {

    Context context;

    public DepenseAdapter(Context context, ArrayList<Depense> depenses) {
        super(context, 0, depenses);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", java.util.Locale.getDefault());

        final Depense depense = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.depense_item, parent, false);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DepenseDetail_Activity.class);
                intent.putExtra("Depense", depense);
                context.startActivity(intent);
            }

        });


        TextView tvNom = (TextView) convertView.findViewById(R.id.tvNom);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvMontant = (TextView) convertView.findViewById(R.id.tvMontant);

        tvNom.setText(depense.getMagasin().getNom_managasin());
        tvDate.setText(formatter.format(depense.getDateDepense()));
        tvMontant.setText(Double.toString(depense.getMontant()) + " €");
        return convertView;
    }

}
