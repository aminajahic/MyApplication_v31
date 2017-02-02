package fit.ba.myapplication_v31;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fit.ba.myapplication_v31.Podaci.OpstinaVM;
import fit.ba.myapplication_v31.Podaci.Storage;
import fit.ba.myapplication_v31.helper.MyRunnable;

/**
 * Created by Developer on 31.01.2017..
 */

public class GradoviFragment extends DialogFragment{

    View view;
    List<OpstinaVM> novaLista=new ArrayList<>();
    ListView lista;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog_fragment,container,false);

         lista= (ListView) view.findViewById(R.id.listaGradova);
        BindGradovi();



        Button btnPretraga= (Button) view.findViewById(R.id.btnPretrazi);


        btnPretraga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                do_Pretraga();
            }
        });


        return view;

    }

    private void do_Pretraga() {

        EditText pretraga= (EditText) view.findViewById(R.id.unosPretrage);


        if(pretraga.getText().toString().length()!=0) {

            for (int i=0;i<Storage.getOpstine().size();i++)
            {
                if(Storage.getOpstine().get(i).getNaziv().toString().contains(pretraga.getText().toString()))
                    novaLista.add(new OpstinaVM(Storage.getOpstine().get(i).getId(),Storage.getOpstine().get(i).getNaziv().toString(),
                            Storage.getOpstine().get(i).getDrzava().toString()));

            }



        }
        if(novaLista!=null) {
            lista.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return novaLista.size();
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    LayoutInflater inflater1= (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView=inflater1.inflate(R.layout.dialog_detalji,parent,false);

                    OpstinaVM o=(OpstinaVM)novaLista.get(position);

                    ((TextView)convertView.findViewById(R.id.unosGrad)).setText(o.getNaziv().toString());
                    ((TextView)convertView.findViewById(R.id.unosDrzava)).setText(o.getDrzava().toString());




                    return  convertView;



                }
            });
        }




    }

    private void BindGradovi() {

        lista.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return Storage.getOpstine().size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater1= (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView=inflater1.inflate(R.layout.dialog_detalji,parent,false);

                OpstinaVM o=Storage.getOpstine().get(position);


                ((TextView)convertView.findViewById(R.id.unosGrad)).setText(o.getNaziv().toString());
                ((TextView)convertView.findViewById(R.id.unosDrzava)).setText(o.getDrzava().toString());




                return  convertView;

            }
        });


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyRunnable<OpstinaVM> opstine= (MyRunnable<OpstinaVM>) getArguments().getSerializable("korisnik");
                opstine.run(Storage.getOpstine().get(position));
                dismiss();

            }
        });


    }

    public static void newInstance(FragmentActivity activity, MyRunnable<OpstinaVM> onSuccess) {

        Bundle args = new Bundle();
        args.putSerializable("korisnik",onSuccess);
        FragmentManager fm= activity.getSupportFragmentManager();
        GradoviFragment fragment= new GradoviFragment();
        fragment.setArguments(args);
        fragment.show(fm,"");




    }
}
