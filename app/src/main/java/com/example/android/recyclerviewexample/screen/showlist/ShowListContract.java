package com.example.android.recyclerviewexample.screen.showlist;

import android.support.annotation.NonNull;
import com.example.android.recyclerviewexample.data.word.Word;
import com.example.android.recyclerviewexample.screen.BasePresenter;
import com.example.android.recyclerviewexample.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ShowListContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onRequestPermissionsReuslt(int requestCode, @NonNull String[] permissions,
                @NonNull int[] grantResults);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onItemWordClicked(Word word);
        void onRequestPermissionsReuslt(int requestCode, @NonNull String[] permissions,
                @NonNull int[] grantResults);
    }
}
