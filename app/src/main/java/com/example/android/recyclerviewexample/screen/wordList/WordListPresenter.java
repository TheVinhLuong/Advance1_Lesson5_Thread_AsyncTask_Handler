package com.example.android.recyclerviewexample.screen.wordList;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.example.android.recyclerviewexample.data.word.Word;
import com.example.android.recyclerviewexample.data.word.WordDataSource;
import com.example.android.recyclerviewexample.data.word.WordRepository;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link WordListFragment}), retrieves the data and updates
 * the UI as required.
 */
final class WordListPresenter implements WordListContract.Presenter {
    private static final String TAG = WordListPresenter.class.getSimpleName();
    private final WordListContract.ViewModel mViewModel;
    private final WordRepository mWordRepository;
    private List<Word> mWords;
    private static AsyncTask asyncTask;
    private Handler mHandler;

    public WordListPresenter(WordListContract.ViewModel viewModel, WordRepository wordRepository) {
        mViewModel = viewModel;
        mWordRepository = wordRepository;
        mHandler = new Handler();
    }

    @Override
    public void onStart() {
        if (mWordRepository != null) {
            mWordRepository.deleteAll();
            mWordRepository.addWord(new Word("a"));
            mWordRepository.addWord(new Word("b"));
            mWordRepository.addWord(new Word("c"));
            mWordRepository.getAllWords(new WordDataSource.LoadAllWordsCallback() {
                @Override
                public void onAllWordLoaded(List<Word> words) {
                    mWords = words;
                    mViewModel.changeDataSet(mWords);
                }

                @Override
                public void onDataNotAvailable() {

                }
            });
        }
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onItemWordListClicked(Word word) {
        Log.d(TAG, word.getWord() + " " + WordListPresenter.class.getName());
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onButtonLoadingWithAsyncTaskClick(View v) {
        Log.d(TAG, "on asynctask button clicked");
        if (asyncTask == null) {
            asyncTask = new AsyncTask<Void, Integer, Void>() {
                @Override
                protected void onPreExecute() {
                    mViewModel.showProgressDialog();
                }
                

                @Override
                protected void onProgressUpdate(Integer... values) {
                    super.onProgressUpdate(values);
                    mViewModel.updateProgressDialog(values[0]);
                }

                @Override
                protected Void doInBackground(Void... voids) {
                    mHandler.post(() -> new CountDownTimer(101000, 1000) {
                        
                        public void onTick(long millisUntilFinished) {
                            if(100 - Math.round(millisUntilFinished / 1000) <= 100) {
                                publishProgress(100 - Math.round(millisUntilFinished / 1000));
                            }else{
                                mViewModel.dismissProgressDialog();
                            }
                        }

                        public void onFinish() {

                        }
                    }.start());
                    return null;
                }
            }.execute();
        }else{
            asyncTask.execute();
        }
    }

    @Override
    public void onButtonLoadingWithThreadClick(View v) {
        Log.d(TAG, "on thread button clicked");
        mViewModel.showProgressDialog();
        delayCounting(0);
    }

    private void delayCounting(final int count) {
        mHandler.postDelayed(() -> {
            if (count <= 100) {
                delayCounting(count + 1);
            } else {
                mViewModel.dismissProgressDialog();
            }
        }, 1000);
    }
}
