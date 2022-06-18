package com.example.orangetest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.orangetest.DB.DBHandler;
import com.example.orangetest.fragments.ContextFragment;
import com.example.orangetest.R;

public class MainActivity extends AppCompatActivity {

    ImageButton Red, Blue, Green;
    Fragment contextFragment = new ContextFragment();
    public static final String BASE_URL = "https://www.boredapi.com/api/";
    DBHandler dbHandler = new DBHandler(MainActivity.this);


    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }
    public void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener;
    }

    private FragmentRefreshListener fragmentRefreshListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Red = findViewById(R.id.ibRed);
        Blue = findViewById(R.id.ibBlue);
        Green = findViewById(R.id.ibGreen);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, contextFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();


        //when clicking on blue button
        Blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete current bored from db
                ContextFragment ct = new ContextFragment();
                dbHandler.deleteBored(ct.getKeyFr());
                //open favorites activity
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });

        //reload contextFragment when clicking on red button
        Red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFragmentRefreshListener()!=null){
                    getFragmentRefreshListener().onRefresh();
                }
                //delete this bored from db
                ContextFragment ct = new ContextFragment();
                dbHandler.deleteBored(ct.getKeyFr());
            }
        });

        //add to db and reload contextFragment when clicking on green button
        Green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFragmentRefreshListener()!=null){
                    getFragmentRefreshListener().onRefresh();
                }
            }
        });
    }

    public interface FragmentRefreshListener{
        void onRefresh();
    }
}