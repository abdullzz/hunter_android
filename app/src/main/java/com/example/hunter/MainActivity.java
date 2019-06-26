package com.example.hunter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    boolean check = false;
    FragmentTransaction transaction;
    String value;

    String id_user ;
    String nama_lengkap;
    String alamat;
    String no_ktp;
    String no_hp;
    String point;
    String picture;
//    Bundle bundle = new Bundle();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);


        id_user = getIntent().getStringExtra("id_user");
        nama_lengkap = getIntent().getStringExtra("nama_lengkap");

//        bundle.putString("nama_lengkap",nama_lengkap);
//        bundle.putString("nama_lengkap",nama_lengkap);
//        bundle.putString("alamat",alamat);
//        bundle.putString("no_ktp",no_ktp);
//        bundle.putString("no_hp",no_hp);
//        bundle.putString("point",point);
//        bundle.putString("picture",picture);




//        Bundle args = new Bundle();
//        args.putString("id_user", id_user);
//        args.putString("nama_lengkap", nama_lengkap);
//        args.putString("alamat", alamat);
//        args.putString("no_ktp", no_ktp);
//        args.putString("no_hp", no_hp);
//        args.putString("point", point);
//        args.putString("picture", picture);


    }


    @Override
    public void onBackPressed(){

    }

    //getter itu alternative buat bundle
    public String getId_user() {
        return id_user;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getPoint() {
        return point;
    }

    public String getPicture() {
        return picture;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = new HomeFragment();
            selectedFragment = new HomeFragment();

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_history:
                    Intent gotoHistoryActivity = new Intent(MainActivity.this, HistoryActivity.class);
                    startActivity(gotoHistoryActivity);
                    break;
                case R.id.nav_message:
                    selectedFragment = new MessageFragment();
                    break;
                case R.id.nav_profile:
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.nav_report:
                    Intent gotoDaftarActivity = new Intent(MainActivity.this, DaftarActivity.class);
                    startActivity(gotoDaftarActivity);
                    break;

                default:
                    selectedFragment = new HomeFragment();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}
