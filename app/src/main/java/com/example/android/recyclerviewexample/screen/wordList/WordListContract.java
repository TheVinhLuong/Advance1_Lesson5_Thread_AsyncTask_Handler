package com.example.android.recyclerviewexample.screen.wordList;

import com.example.android.recyclerviewexample.data.word.Word;
import com.example.android.recyclerviewexample.screen.BasePresenter;
import com.example.android.recyclerviewexample.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface WordListContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        public void changeDataSet(List<Word> words);
        public void showProgressDialog();
        public void updateProgressDialog(int progress);
        public void dismissProgressDialog();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onItemWordListClicked(Word word);
        void onButtonLoadingWithAsyncTaskClick(android.view.View v);
        void onButtonLoadingWithThreadClick(android.view.View v);
    }
    
}
