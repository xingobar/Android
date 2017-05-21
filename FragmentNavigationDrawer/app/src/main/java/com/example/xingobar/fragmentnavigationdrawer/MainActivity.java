package com.example.xingobar.fragmentnavigationdrawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {


    final String[] data= {"one","two","three"};
    final String[] fragments = {
        "com.example.xingobar.fragmentnavigationdrawer.FragmentOne",
        "com.example.xingobar.fragmentnavigationdrawer.FragmentTwo",
        "com.example.xingobar.fragmentnavigationdrawer.FragmentThree",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        final DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        final ListView navList = (ListView)findViewById(R.id.nav_list);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        navList.setAdapter(adapter); // add array into list view
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos,long id){
                drawer.setDrawerListener( new DrawerLayout.SimpleDrawerListener(){
                    @Override
                    public void onDrawerClosed(View drawerView){
                        super.onDrawerClosed(drawerView);
                        // replace layout_main fragment with the selected item
                        // step 1 : getSupportFragmentMangager
                        // step 2: beginTranscation
                        // step 3: replace / add / remove .. etc action
                        // step 4. commit transcation
                        FragmentTransaction fragmentTranscation = getSupportFragmentManager().beginTransaction();
                        fragmentTranscation.replace(R.id.main, Fragment.instantiate(MainActivity.this, fragments[pos]));
                        fragmentTranscation.commit();
                    }
                });
                drawer.closeDrawer(navList);
            }
        });
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main,Fragment.instantiate(MainActivity.this,fragments[0]));
        fragmentTransaction.commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}