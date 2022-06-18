package com.example.orangetest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.orangetest.DB.DBHandler;
import com.example.orangetest.R;
import com.example.orangetest.listAdapter.FavoriteAdapter;
import com.example.orangetest.object.Bored;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ListView favoriteListView = findViewById(R.id.favoriteListView);

        ArrayList<Bored> boredArrayList = new ArrayList<Bored>();

        //get from db
        // create a new dbhandler class and pass our context to it
        DBHandler dbHandler = new DBHandler(FavoritesActivity.this);
        //get our bored array list from DBHandler class
        boredArrayList.clear();
        boredArrayList = dbHandler.readBored();
        System.out.println("boredArrayList size: "+boredArrayList.size());

        //initializing our adapter class.
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(FavoritesActivity.this, boredArrayList);

        //set adapter to listView
        favoriteListView.setAdapter(favoriteAdapter);
        favoriteListView.setClickable(true);


        //on item long click --> call alert dialog (delete)
        ArrayList<Bored> finalBoredArrayList = boredArrayList;
        favoriteListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                alertDialog.setTitle("delete?");


                //button cancel
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                dialogInterface.dismiss();
                            }
                        });

                //button delete
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                dbHandler.deleteBored(finalBoredArrayList.get(i).getKey());
                                favoriteAdapter.remove(finalBoredArrayList.get(i));
                                favoriteAdapter.notifyDataSetChanged();

                            }
                        });
                alertDialog.show();

                return true;
            }
        });


    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(FavoritesActivity.this, MainActivity.class);
        startActivity(intent);
    }

}