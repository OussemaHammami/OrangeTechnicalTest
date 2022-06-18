package com.example.orangetest.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.orangetest.object.Bored;
import com.example.orangetest.DB.DBHandler;
import com.example.orangetest.object.JSONPlaceholder;
import com.example.orangetest.R;
import com.example.orangetest.activities.MainActivity;
import com.example.orangetest.control.UnsafeOkHttpClient;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ContextFragment extends Fragment {

    private DBHandler dbHandler;
    public static final String BASE_URL = "https://www.boredapi.com/api/";
    TextView tvContext ;
    public static String keyfr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_context, container, false);

        tvContext = root.findViewById(R.id.tvContext);
        tvContext.setText("");


        getApiData();

        ((MainActivity)getActivity()).setFragmentRefreshListener(new MainActivity.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                getApiData();
            }
        });

        return root;
    }


    public void getApiData(){
        //trust unsafe SSL certificate
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        // create a retrofit builder and pass our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create an instance for our jsonPlaceholder class
        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        Call<Bored> call = jsonPlaceholder.getBored();

        //extract data
        call.enqueue(new Callback<Bored>() {
            @Override
            public void onResponse(@NonNull Call<Bored> call, @NonNull Response<Bored> response) {
                if(!response.isSuccessful()){
                    System.out.println("response not successful");
                }

                else{
                    System.out.println("success");
                    Bored bored = response.body();
                    assert bored != null;
                    System.out.println("test1: "+bored.getActivity());

                    tvContext.setText(bored.getActivity());

                    //to get it from main activity
                    setKeyFr(bored.getKey());

                    //insert into local db
                    // create a new dbhandler class and pass our context to it.
                    dbHandler = new DBHandler(getContext());
                    dbHandler.addNewBored(  bored.getKey(),
                                            bored.getActivity(),
                                            bored.getType(),
                                            bored.getParticipants(),
                                            bored.getPrice(),
                                            bored.getLink(),
                                            bored.getAccessibility());
                }

            }



            @Override
            public void onFailure(@NonNull Call<Bored> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    public void setKeyFr(String key){
        keyfr = key;
    }

    public String getKeyFr(){
        return keyfr;
    }
}

