package fit.ba.myapplication_v31;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fit.ba.myapplication_v31.Podaci.KorisnikVM;

/**
 * Created by Developer on 31.01.2017..
 */

public class PotvrdaFragment  extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.potvrda_fragment,container,false);


        KorisnikVM k=(KorisnikVM) getArguments().get("korisnik");


        ((TextView)view.findViewById(R.id.unosPotvrde)).setText("Poštovani "+k.getIme().toString()+" uspjesno ste se registorvaila");




        return view;
    }
}
