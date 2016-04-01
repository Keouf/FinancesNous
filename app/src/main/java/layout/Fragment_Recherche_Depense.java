package layout;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pcportablevidjay.financesnous.R;

import java.util.ArrayList;

import classes.Depense;
import classes.DepenseAdapter;
import classes.StorageHelper;

public class Fragment_Recherche_Depense extends Fragment {

    private TextView tvMessage;
    private ListView depensesListView;
    private DepenseAdapter adapater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recherche_depense, container, false);

        setHasOptionsMenu(true);

        //ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        tvMessage = (TextView) view.findViewById(R.id.recherche_tv_messageHolder);
        depensesListView = (ListView) view.findViewById(R.id.rechercheDepense_listView_10Depenses);

        // get 10 all depenses
        ArrayList<Depense> mes10DernierDepenses = StorageHelper.getUtilisateur(getActivity().getBaseContext()).get10DernierDepenses();
        Log.e("json", "arraylist of 10 depenses = " + mes10DernierDepenses.toString());

        if (mes10DernierDepenses == null || mes10DernierDepenses.isEmpty()) {
            depensesListView.setVisibility(View.GONE);
            tvMessage.setVisibility(View.VISIBLE);
            tvMessage.setText("Vous n'avez pas de depenses crée");
        } else {
            try {
                tvMessage.setVisibility(View.INVISIBLE);
                adapater = new DepenseAdapter(this.getActivity(), mes10DernierDepenses);
                depensesListView.setAdapter(adapater);
            } catch (Exception e) {
                Log.e("json", e.toString());
            }
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("json", "all 10 depenses : " + StorageHelper.getUtilisateur(getActivity().getBaseContext()).getAllDepensesInString());
        if (StorageHelper.getUtilisateur(getActivity().getBaseContext()).getMesDepenses().size() > 0)
        {
            adapater.clear();
            adapater.addAll(StorageHelper.getUtilisateur(getActivity().getBaseContext()).get10DernierDepenses());
            adapater.notifyDataSetChanged();
            depensesListView.setAdapter(adapater);
            depensesListView.invalidateViews();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_favorite:
                // do s.th.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_recherche_menu, menu);
    }

    public DepenseAdapter getAdapater() {
        return adapater;
    }

}
