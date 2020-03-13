package com.simpleman.noteapp.activity;

public interface EditorView {

    void showProgress();
    void hideProgress();
    void onRequestSuccess(String massage);
    void onRequestError(String massage);

}
