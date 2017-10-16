package com.example.android.recyclerviewexample.screen.wordList;

import android.databinding.BaseObservable;
import com.example.android.recyclerviewexample.data.word.Word;
import com.example.android.recyclerviewexample.screen.BaseRecyclerViewAdapter;

/**
 * Created by VinhTL on 09/10/2017.
 */

public class ItemWordListViewModel extends BaseObservable{
    private static final String TAG = ItemWordListViewModel.class.getSimpleName();
    private Word mWord;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Word> mItemClickListener;

    public ItemWordListViewModel(Word mWord, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Word> mItemClickListener) {
        this.mWord = mWord;
        this.mItemClickListener = mItemClickListener;
    }
    
    public String getWord(){
        return mWord.getWord();
    }

    public BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Word> getItemClickListener() {
        return mItemClickListener;
    }

    public void setItemClickListener(
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Word> itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    
}

