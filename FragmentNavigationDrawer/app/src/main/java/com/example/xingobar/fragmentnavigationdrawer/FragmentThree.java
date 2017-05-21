package com.example.xingobar.fragmentnavigationdrawer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xingobar on 2017/5/21.
 */

public class FragmentThree extends Fragment {

    public static Fragment newInstance(Context context){
        FragmentThree fragment = new FragmentThree();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_three,null);
        return viewGroup;
    }
}
