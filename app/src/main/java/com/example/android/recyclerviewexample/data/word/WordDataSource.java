package com.example.android.recyclerviewexample.data.word;

import java.util.List;

/**
 * Created by VinhTL on 13/10/2017.
 */

public interface WordDataSource {
    
    interface LoadAllWordsCallback {
        void onAllWordLoaded(List<Word>words);
        void onDataNotAvailable();
    }
    
    void getAllWords(LoadAllWordsCallback callback);
    void addWord(Word word);
    void deleteAll();
}
