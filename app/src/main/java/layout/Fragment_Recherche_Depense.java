package layout;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pcportablevidjay.financesnous.R;

import java.util.ArrayList;

import classes.Depense;
import classes.DepenseAdapter;
import classes.Global;

public class Fragment_Recherche_Depense extends Fragment {

    //private View myFragmentView;
    Global global;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recherche_depense, container, false);
        // get 10 all depenses
        global = (Global)getActivity().getApplication();
        ArrayList<Depense> mes10DernierDepenses = global.getMainUtilisateur().get10DernierDepenses();
        Log.e("json", "arraylist of 10 depenses = " + mes10DernierDepenses.toString());

        // remplire Listview
        DepenseAdapter adapater = new DepenseAdapter(global.getMyContext(), mes10DernierDepenses);
        try {
            ListView depensesListView = (ListView)view.findViewById(R.id.rechercheDepense_listView_10Depenses);
            depensesListView.setAdapter(adapater);
        } catch (Exception e) {
            Log.e("json", e.toString());
        }

        return view;
    }

}
