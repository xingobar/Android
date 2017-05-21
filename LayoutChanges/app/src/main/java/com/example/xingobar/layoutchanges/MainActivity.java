package com.example.xingobar.layoutchanges;

import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mContainerView;
    private static final String[] COUNTRIES = new String[]{
            "Belgium", "France", "Italy", "Germany", "Spain",
            "Austria", "Russia", "Poland", "Croatia", "Greece",
            "Ukraine",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_changes);
        // set the vertical container layout
        mContainerView = (ViewGroup)findViewById(R.id.container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        // add menu layout
        getMenuInflater().inflate(R.menu.layout_changes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case android.R.id.home:
                NavUtils.navigateUpTo(this,new Intent(this,MainActivity.class));
                return true;
            case R.id.action_add_item:
                // hide the empty view
                findViewById(android.R.id.empty).setVisibility(View.GONE);
                addItem();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItem() {

        // param1 : list item layout
        // param2 : container name
        // add list item into mContainerView on this layout
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.list_item,
                mContainerView,false);

        ((TextView) newView.findViewById(android.R.id.text1)).setText(COUNTRIES[(int) (Math.random() * COUNTRIES.length)]);


        newView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // remove the row from its parent
                mContainerView.removeView(newView);
                if(mContainerView.getChildCount() ==0){
                    findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
                }
            }
        });
        mContainerView.addView(newView,0);
    }
}
