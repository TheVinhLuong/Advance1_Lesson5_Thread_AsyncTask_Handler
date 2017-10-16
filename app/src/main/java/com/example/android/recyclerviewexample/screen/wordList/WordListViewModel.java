package com.example.android.recyclerviewexample.screen.wordList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import com.example.android.recyclerviewexample.data.word.Word;
import java.util.List;

/**
 * Exposes the data to be used in the WordList screen.
 */

public class WordListViewModel implements WordListContract.ViewModel, WordListAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = WordListViewModel.class.getSimpleName();
    private WordListContract.Presenter mPresenter;
    private Handler mUiHandler;
    private WordListAdapter mAdapter;

    public WordListViewModel(WordListAdapter adapter, Handler handler) {
        mAdapter = adapter;
        mAdapter.setItemClickListener(this);
        mUiHandler = handler;
    }

    public WordListAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(WordListAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void changeDataSet(List<Word> words){
        mAdapter.changeDataSet(words);
    }

    @Override
    public void showProgressDialog() {
        Log.d(TAG, "showProgressDialog");
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("order", "show");
        message.setData(bundle);
        mUiHandler.sendMessage(message);
    }

    @Override
    public void updateProgressDialog(int progress) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("order", "update");
        bundle.putInt("progress", progress);
        message.setData(bundle);
        mUiHandler.sendMessage(message);
    }

    @Override
    public void dismissProgressDialog() {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("order", "dismiss");
        message.setData(bundle);
        mUiHandler.sendMessage(message);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(WordListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public WordListContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onItemRecyclerViewClick(View view, Object item) {
        mPresenter.onItemWordListClicked((Word)item);
    }
}
