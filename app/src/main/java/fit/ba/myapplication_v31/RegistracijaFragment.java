package fit.ba.myapplication_v31;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fit.ba.myapplication_v31.Podaci.KorisnikVM;
import fit.ba.myapplication_v31.Podaci.OpstinaVM;
import fit.ba.myapplication_v31.helper.MyRunnable;

/**
 * Created by Developer on 31.01.2017..
 */

public class RegistracijaFragment extends Fragment {

View view;
    Button btnGrad;
    OpstinaVM opstina;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.registracija_fragment,container,false);

        btnGrad= (Button) view.findViewById(R.id.btnGrad);
        btnGrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                do_btnOdaberiGrad();
            }
        });

Button btnSpremi= (Button) view.findViewById(R.id.btnSpremi);
        btnSpremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_SpremiPodatke();
            }
        });


return view;

    }

    private void do_SpremiPodatke() {

        EditText ime= (EditText) view.findViewById(R.id.imeUnos);
        EditText prezime= (EditText) view.findViewById(R.id.prezimeUnos);

        if(ime.getText().length()==0 || prezime.getText().length()==0 || btnGrad.getText().toString().contains("Grad"))
            Toast.makeText(getActivity(),"Niste popunili sve podatke",Toast.LENGTH_LONG).show();


        else
        {

            KorisnikVM k= new KorisnikVM(ime.getText().toString(),prezime.getText().toString(),opstina);
            FragmentManager fm= getActivity().getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            Bundle arg= new Bundle();
            arg.putSerializable("korisnik",k);
            PotvrdaFragment fragment= new PotvrdaFragment();
            fragment.setArguments(arg);
            ft.replace(R.id.activity_main,fragment);
            ft.commit();






        }



    }

    private void do_btnOdaberiGrad() {

        GradoviFragment.newInstance(getActivity(), new MyRunnable<OpstinaVM>() {
            @Override
            public void run(OpstinaVM opstinaVM) {


                btnGrad.setText(opstinaVM.getNaziv().toString());
                opstina=opstinaVM;
            }
        });
    }
}
