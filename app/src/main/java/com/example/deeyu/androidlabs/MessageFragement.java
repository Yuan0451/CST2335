package com.example.deeyu.androidlabs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by deeyu on 23-Mar-17.
 */

public class MessageFragement extends Fragment{

    Long id;

//-------------lab7 step5
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View gui = inflater.inflate(R.layout.activity_message_details, null);
        TextView tv = (TextView)gui.findViewById(R.id.message);
        tv.setText("You clicked on ID:" + id);
        return gui;
    }

}
