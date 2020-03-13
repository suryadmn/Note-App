package com.simpleman.noteapp.activity;

import com.simpleman.noteapp.api.ApiClient;
import com.simpleman.noteapp.api.ApiInterface;
import com.simpleman.noteapp.model.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView mainView){
        this.view = mainView;
    }

    void getData(){
        view.showLoading();

        //Request to server
        ApiInterface apiInterface = ApiClient.getApiCLient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.getNote();

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

    /*void searchData(String search){
        view.showLoading();

        //Request to server
        ApiInterface apiInterface = ApiClient.getApiCLient().create(ApiInterface.class);
        Call<Note> call = apiInterface.searchData(search);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                view.hideLoading();

                String value = response.body().getValue();

                if (value.equals("1")){
                    view.onGetResult(response.body().getResult());
                    Log.i("Info result", String.valueOf(response.body().getResult()));
                }else {
                    view.onErrorLoading("Error");
                }

            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }*/
}
