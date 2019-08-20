package com.example.rishan.rishan.Models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rishan.rishan.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rishan on 5/16/2018.
 */

public class InquiryAdapter extends ArrayAdapter<Inquiry> {


    public InquiryAdapter(@NonNull Context context,  List<Inquiry> objects) {
        super(context, R.layout.custom_review, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Inquiry i=new Inquiry();
        i=getItem(position);
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(R.layout.custom_review, parent, false);

        ImageView inquiryImage=view.findViewById(R.id.inquiryImage);
        TextView inquiryText=view.findViewById(R.id.inquiryText);
        TextView inquirySender=view.findViewById(R.id.inquirySender);
        TextView inquiryTime=view.findViewById(R.id.inquiryTime);
        RatingBar reviewRating=view.findViewById(R.id.reviewRating);

        reviewRating.setRating(i.getRating());
        User u=i.getSender();

        inquiryText.setText(i.getMessage());
        inquirySender.setText(u.getUsernameSave());
        inquiryTime.setText(i.getTime());

        //use shared preferences to set image view photo to comment

        Picasso.get()
                .load(u.getProfilePicture())
                .placeholder(R.drawable.ic_launcher)
                .into(inquiryImage);

return view;






    }
}
