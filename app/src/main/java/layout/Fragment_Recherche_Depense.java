package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pcportablevidjay.financesnous.Accueil_Activity;
import com.example.pcportablevidjay.financesnous.R;

import org.json.JSONArray;

import java.util.ArrayList;

import classes.AndroidConnectivity;
import classes.Depense;
import classes.DepenseAdapter;
import classes.Global;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Recherche_Depense.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Recherche_Depense#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Recherche_Depense extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public AndroidConnectivity androidConnectivity = new AndroidConnectivity(this);

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_Recherche_Depense() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Recherche_Depense.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Recherche_Depense newInstance(String param1, String param2) {
        Fragment_Recherche_Depense fragment = new Fragment_Recherche_Depense();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        if (androidConnectivity.getConnectivityStatus())
        {
            // get 10 derniers dépeses
            JSONArray mes10DerniersDepenses = Accueil_Activity.myDBHelper.get10DerniersDepenses();
            Log.e("json", mes10DerniersDepenses.toString());
            Accueil_Activity.jsonConverter.convertToDepense(mes10DerniersDepenses, this);

            global = (Global)this.getApplication();
            Log.e("json", global.getMainUtilisateur().getMesDepenses().toString());


            // remplire Listview
            ArrayList<Depense> mesDepensesArray = jsonConverter.ConvertDepensesToDepenseArrayList(this);
            DepenseAdapter adapater = new DepenseAdapter(this, mesDepensesArray);
            ListView DepensesListView = (ListView) findViewById(R.id.contentAccueil_listView_10Depenses);
            DepensesListView.setAdapter(adapater);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recherche_depense, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
