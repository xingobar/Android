package com.example.xingobar.fragmentnavigationdrawer;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/// https://developer.android.com/guide/topics/ui/controls/pickers.html
/// http://stackoverflow.com/questions/15027987/how-to-read-timepicker-chosen-values
/// http://stackoverflow.com/questions/12453075/how-to-set-timepicker-show-with-format-24h
/// https://android--examples.blogspot.tw/2015/04/timepickerdialog-in-android.html
/// http://stackoverflow.com/questions/19913661/strange-behavior-of-timepickerdialog-when-press-positive-button
public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {


    final String[] data= {"one","two","three"};
    final String[] fragments = {
        "com.example.xingobar.fragmentnavigationdrawer.FragmentOne",
        "com.example.xingobar.fragmentnavigationdrawer.FragmentTwo",
        "com.example.xingobar.fragmentnavigationdrawer.FragmentThree",
    };
    private int pickerHour = 0;
    private int pickerMin = 0;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.android_setting:
                return true;

            case R.id.add_item:
                TimePickerFragment newFragment = new TimePickerFragment();
                // the show method requires an instance of FragmentManager and a unique tag name for the fragment
                newFragment.show(getSupportFragmentManager(),"timePicker");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        pickerHour = hourOfDay;
        pickerMin = minute;
        Calendar calendar  = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,0);
        calendar.add(Calendar.MINUTE,1);

        /// register broadcast
        /// registerReceiver(broadcastReceiver, new IntentFilter("alarm_message"));
        Intent intent = new Intent(this,broadcastReceiver.class);
        intent.putExtra("message","alarm_message");
        intent.setAction("alarm_message");
       // sendBroadcast(intent);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,PendingIntent.FLAG_ONE_SHOT);



        // set alarmManager
        // system will send intent when time up
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

        Toast.makeText(this,"Hour : " + String.valueOf(hourOfDay + " Minute : " + String.valueOf(minute)),Toast.LENGTH_SHORT).show();
    }

    private class broadcastReceiver{
        private BroadcastReceiver broadcastReceiverShow = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
                if(bundle.get("message").equals(intent.getAction())){
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Recevied message")
                            .setPositiveButton("OK",new DialogInterface.OnClickListener(){

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // unregister broadcast
                                    //unregisterReceiver(broadcastReceiver);
                                }
                            }).show();
                }
            }
        };
    }

}