package com.example.rishan.rishan.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rishan.rishan.R;

/**
 * Created by Rishan on 6/7/2018.
 */

public class ContactUsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.contact_us, container, false);
        getActivity().setTitle("Contact us");

        Button button= view.findViewById(R.id.button_chatbox_send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Review Submitted!",Toast.LENGTH_LONG);
            }
        });
        return view;
    }
    }
