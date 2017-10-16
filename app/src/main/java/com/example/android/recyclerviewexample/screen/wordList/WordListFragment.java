package com.example.android.recyclerviewexample.screen.wordList;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.recyclerviewexample.R;
import com.example.android.recyclerviewexample.data.word.Word;
import com.example.android.recyclerviewexample.data.word.WordRepository;
import com.example.android.recyclerviewexample.data.word.local.WordLocalDatasource;
import com.example.android.recyclerviewexample.databinding.FragmentWordListBinding;
import com.example.android.recyclerviewexample.screen.BaseFragment;
import java.util.ArrayList;

/**
 * WordList Screen.
 */
public class WordListFragment extends BaseFragment{
    private static final String TAG = WordListFragment.class.getSimpleName();
    private WordListContract.ViewModel mViewModel;
    private WordListAdapter mWordListAdapter;
    private WordRepository mWordRepository;
    private ProgressDialog mProgressDialog;
    private static Handler HANDLER;

    public WordListFragment() {

    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        HANDLER = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "hadnleMessage");
                String order = msg.getData().getString("order");
                if(order == null){
                    return;
                }
                switch (order){
                    case "show":
                        Log.d(TAG, "show()");
                        mProgressDialog.show();
                        break;
                    case "update":
                        Log.d(TAG, "update: " + msg.getData().getInt("progress"));
                        if(mProgressDialog != null){
                            mProgressDialog.setProgress(msg.getData().getInt("progress"));
                        }
                        break;
                    case "dismiss":
                        Log.d(TAG, "dismiss");
                        if(mProgressDialog != null){
                            mProgressDialog.dismiss();
                        }
                        break;
                }
            }
        };
        ArrayList<Word> words = new ArrayList<>();
        mWordRepository = WordRepository.getInstance(new WordLocalDatasource(getActivity()));
        mWordListAdapter = new WordListAdapter(getActivity(), words);
        mViewModel = new WordListViewModel(mWordListAdapter, HANDLER);
        WordListContract.Presenter presenter = new WordListPresenter(mViewModel, mWordRepository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentWordListBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_word_list, container, false);
        binding.setViewModel((WordListViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
    
}
