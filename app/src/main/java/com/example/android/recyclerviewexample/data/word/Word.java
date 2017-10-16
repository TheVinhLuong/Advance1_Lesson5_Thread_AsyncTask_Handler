package com.example.android.recyclerviewexample.data.word;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by VinhTL on 09/10/2017.
 */

public class Word implements Parcelable {
    private int id;
    private String word;

    public Word() {
    }

    public Word(String word){
        this.word = word;
    }

    public Word(int id, String word) {
        this.id = id;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected Word(Parcel in) {
        id = in.readInt();
        word = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(word);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };
}
