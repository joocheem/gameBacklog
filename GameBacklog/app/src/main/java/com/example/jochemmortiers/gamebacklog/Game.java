package com.example.jochemmortiers.gamebacklog;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Game implements Serializable{

    @NonNull
    @PrimaryKey(autoGenerate = true)
    Integer id;
    String title;
    String platform;
    String notes;
    Integer status;
    Long date;

    public static String[] getStatusStrings(Context pContext) {
        String[] list = new String[statusStrings.length];
        list[0] = pContext.getString(statusStrings[0]);
        list[1] = pContext.getString(statusStrings[1]);
        list[2] = pContext.getString(statusStrings[2]);
        list[3] = pContext.getString(statusStrings[3]);
        return list;
    }

    static int[] statusStrings = new int[]{
            R.string.status_playing,
            R.string.status_wantToPlay,
            R.string.status_stalled,
            R.string.status_dropped,
    };



    public Game(){

    }

    public Game(String title, String platform, String notes, Integer status, Long date) {
        this.title = title;
        this.platform = platform;
        this.notes = notes;
        this.status = status;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getDateFormatted() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(cal.getTime());
    }
}

