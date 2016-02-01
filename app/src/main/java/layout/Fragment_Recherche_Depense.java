package layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pcportablevidjay.financesnous.R;

import classes.Global;

public class Fragment_Recherche_Depense extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recherche_depense, container, false);
    }

    public void Remplir10DerniersDepensesListe(Global global)
    {

    }
}
