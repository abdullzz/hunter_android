package com.example.hunter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    View inflatedview;


    //        Bundle args = new Bundle();
//        args.putString("id_user", id_user);
//        args.putString("nama_lengkap", nama_lengkap);
//        args.putString("alamat", alamat);
//        args.putString("no_ktp", no_ktp);
//        args.putString("no_hp", no_hp);
//        args.putString("point", point);
//        args.putString("picture", picture);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflatedview = inflater.inflate(R.layout.fragment_home, container, false);
        MainActivity activity = (MainActivity) getActivity();
        String nama_lengkap = activity.getNama_lengkap();
        String point = activity.getPoint();
        String picture = activity.getPicture();
        String firstname = "";
        String lastname = "";
        if(nama_lengkap.split("\\w+").length>1){

            lastname = nama_lengkap.substring(nama_lengkap.indexOf(" ")+1,nama_lengkap.indexOf(" ",nama_lengkap.indexOf(" ")+1));
            firstname = nama_lengkap.substring(0, nama_lengkap.indexOf(' '));
        }
        else{
            firstname = nama_lengkap;
        }

        TextView viewfirstname = inflatedview.findViewById(R.id.nama1);
        viewfirstname.setText(firstname);
        TextView viewlastname = inflatedview.findViewById(R.id.nama2);
        viewlastname.setText(lastname);

//        return inflater.inflate(R.layout.fragment_home, container,false);
        return inflatedview;
    }
}
