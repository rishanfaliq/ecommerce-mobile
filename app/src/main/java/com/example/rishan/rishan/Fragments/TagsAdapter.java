package com.example.rishan.rishan.Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.rishan.rishan.Models.Tags;
import com.example.rishan.rishan.R;

import java.util.List;

/**
 * Created by Rishan on 5/30/2018.
 */

public class TagsAdapter extends ArrayAdapter<Tags> {

    Tags t;
    GridviewItemFragment gi;
    public TagsAdapter(@NonNull Context context, int resource, @NonNull List<Tags> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        t=getItem(position);

        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(R.layout.tag_custom, parent, false);

        CheckBox tagCheckBox=view.findViewById(R.id.tagCheckBox);

        tagCheckBox.setText(t.getTags());

        tagCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ( isChecked )
                {






                }
            }
        });


        return view;






    }
}
