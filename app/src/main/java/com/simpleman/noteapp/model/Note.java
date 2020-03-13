package com.simpleman.noteapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Note {

    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("title") private String title;
    @Expose
    @SerializedName("note") private String note;
    @Expose
    @SerializedName("color") private int color;
    @Expose
    @SerializedName("date") private String date;
    @Expose
    @SerializedName("search") private List<Note> result;
    @Expose
    @SerializedName("value") private String value;
    @Expose
    @SerializedName("success") private boolean success;
    @Expose
    @SerializedName("message") private String message;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public int getColor() {
        return color;
    }

    public String getDate() {
        return date;
    }

    public List<Note> getResult() {
        return result;
    }

    public String getValue() {
        return value;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setResult(List<Note> result) {
        this.result = result;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
