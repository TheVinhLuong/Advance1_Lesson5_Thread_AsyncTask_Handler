package com.example.android.recyclerviewexample.data.word;

/**
 * Created by VinhTL on 13/10/2017.
 */

public class WordRepository implements WordDataSource {
    private static WordRepository INSTANCE = null;
    private final WordDataSource mWordLocalDatasource;

    private WordRepository(WordDataSource wordLocalDatasource) {
        mWordLocalDatasource = wordLocalDatasource;
    }

    public static WordRepository getInstance(WordDataSource wordLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new WordRepository(wordLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getAllWords(LoadAllWordsCallback callback) {
        mWordLocalDatasource.getAllWords(callback);
    }

    @Override
    public void addWord(Word word) {
        mWordLocalDatasource.addWord(word);
    }

    @Override
    public void deleteAll() {
        mWordLocalDatasource.deleteAll();
    }
}
