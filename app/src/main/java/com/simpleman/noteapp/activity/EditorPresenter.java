package com.simpleman.noteapp.activity;

import android.util.Log;

import com.simpleman.noteapp.api.ApiClient;
import com.simpleman.noteapp.api.ApiInterface;
import com.simpleman.noteapp.model.Note;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    EditorView view;

    public EditorPresenter(EditorView view){
        this.view = view;
    }

    void saveNotes(final String title, final String note, final int color) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiCLient().create(ApiInterface.class);
        Call<Note> call = apiInterface.saveNote(title, note, color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null){

                    boolean success = response.body().isSuccess();

                    if (success){
                        view.onRequestSuccess(response.body().getMessage());
                    }else {
                        view.onRequestError(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void updateNote(int id, String title, String note, int color) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiCLient().create(ApiInterface.class);

        Call<Note> call = apiInterface.updateNote(id, title, note, color);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                view.hideProgress();

                boolean success = response.body().isSuccess();
                if (success){
                    view.onRequestSuccess(response.body().getMessage());
                }else{
                    view.onRequestError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deleteNote(int id){
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiCLient().create(ApiInterface.class);
        Call<Note> call = apiInterface.deleteNote(id);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null){

                    boolean success = response.body().isSuccess();
                    if (success) {
                        view.onRequestSuccess("Delete successfully");
                    }else {
                        view.onRequestError("Delete failure");
                    }

                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                view.hideProgress();
                Log.i("Info error : " , String.valueOf(t));
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

}
