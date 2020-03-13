package com.simpleman.noteapp.activity;

import com.simpleman.noteapp.model.Note;

import java.util.List;

public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Note> notes);
    void onErrorLoading(String massage);
}
