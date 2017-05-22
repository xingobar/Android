package com.example.xingobar.fragmentnavigationdrawer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by xingobar on 2017/5/21.
 */

public class TimePickerFragment extends DialogFragment{

    private boolean mIsClickOkButton  = false;
    private Activity mActivity;
    private TimePickerDialog.OnTimeSetListener mListener ;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();

        try{
            mListener = (TimePickerDialog.OnTimeSetListener)getActivity();
        }catch (ClassCastException exception){
            throw new ClassCastException(getActivity().toString() + "must implmenet OnTimeSetListener");
        }
    }

//    @Override
//    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//        // do something with the time chosen by the user
//        Log.d("selectedHour","[SelectedHour] : " + selectedHour);
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // use the current time as the default values for the picker
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // create a new instance of TimePickerDialog
        TimePickerDialog dialog = new TimePickerDialog(getActivity(),mListener,hour,minute,
                DateFormat.is24HourFormat(getActivity()));

        // positive button
//        dialog.setButton(TimePickerDialog.BUTTON_POSITIVE,"確定",new TimePickerDialog.OnClickListener(){
//
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                if(which == DialogInterface.BUTTON_POSITIVE){
//                    mIsClickOkButton = true;
//                    Log.d("selectedHour","[Clicked]");
//                }
//            }
//        });
//
//        /// set negative button
//        dialog.setButton(TimePickerDialog.BUTTON_NEGATIVE,"取消",new TimePickerDialog.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                if(which == DialogInterface.BUTTON_NEGATIVE){
//                    mIsClickOkButton = false;
//                }
//            }
//        });
        return dialog;

        // create a new instance of TimePickerDialog and return it
//        return new TimePickerDialog(getActivity(),this,hour,minute,
//                DateFormat.is24HourFormat(getActivity()));
    }

}
