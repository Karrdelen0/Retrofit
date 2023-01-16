package com.kamsiz.retrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kamsiz.retrofit.R;
import com.kamsiz.retrofit.apadter.RecylerViewAdapter;
import com.kamsiz.retrofit.model.CryptoModel;
import com.kamsiz.retrofit.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<CryptoModel>cryptoModels;
    private String BASE_URL="https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecylerViewAdapter recylerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);



        //RETROFİT $ JSON
        Gson gson=new GsonBuilder().setLenient().create();

      retrofit= new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create(gson))
              .build();


      loadData();
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    }
    private void loadData(){

        CryptoAPI cryptoAPI= retrofit.create(CryptoAPI.class);

        Call<List<CryptoModel>> call= cryptoAPI.getData();

        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {

                if (response.isSuccessful()){
                    List<CryptoModel> responseList= response.body();
                    cryptoModels= new ArrayList<>(responseList);

                    //RecyclerView verileri göstermek;
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recylerViewAdapter= new RecylerViewAdapter(cryptoModels);
                    recyclerView.setAdapter(recylerViewAdapter);

                    /*for (CryptoModel cryptoModel:cryptoModels){
                        System.out.println(cryptoModel.currency);

                    }*/
                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }
}