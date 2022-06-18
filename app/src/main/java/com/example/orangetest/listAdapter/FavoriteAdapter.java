package com.example.orangetest.listAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.orangetest.R;
import com.example.orangetest.object.Bored;

import java.util.ArrayList;

public class FavoriteAdapter extends ArrayAdapter<Bored> {

    public FavoriteAdapter(Context ct, ArrayList<Bored> favoriteArrayList){
        super(ct, R.layout.favorite_row, favoriteArrayList);
    }


//    public void remove(int position) {
//        favoriteArrayList.remove(position);
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Bored favorite = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.favorite_row, parent,false);
        }

        TextView activityTxt = convertView.findViewById(R.id.activityTxt);
        TextView typeTxt = convertView.findViewById(R.id.typeTxt2);
        TextView keyTxt = convertView.findViewById(R.id.keyTxt);
        TextView participantsTxt = convertView.findViewById(R.id.participantsTxt);
        TextView priceTxt = convertView.findViewById(R.id.priceTxt);
        TextView accessibilityTxt = convertView.findViewById(R.id.accessibilityTxt);
        TextView linkTxt = convertView.findViewById(R.id.linkTxt);


        activityTxt.setText(favorite.getActivity());
        typeTxt.setText(favorite.getType());
        keyTxt.setText(favorite.getKey());
        participantsTxt.setText(favorite.getParticipants());
        priceTxt.setText(favorite.getPrice());
        accessibilityTxt.setText(favorite.getAccessibility());
        linkTxt.setText(favorite.getLink());


        return convertView;
    }
}
