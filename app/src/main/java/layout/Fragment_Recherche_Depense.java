package layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pcportablevidjay.financesnous.R;

public class Fragment_Recherche_Depense extends Fragment {

    //private View myFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recherche_depense, container, false);
        //myView = view.findViewById(R.id.rechercheDepense_listView_10Depenses);
        return view;
    }

}
