package com.example.android.recyclerviewexample.screen.wordList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.android.recyclerviewexample.R;
import com.example.android.recyclerviewexample.data.word.Word;
import com.example.android.recyclerviewexample.databinding.ItemWordListBinding;
import com.example.android.recyclerviewexample.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhTL on 09/10/2017.
 */

public class WordListAdapter extends BaseRecyclerViewAdapter<WordListAdapter.ItemViewHolder> {
    private List<Word> mWords;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Word> mItemClickListener;

    protected WordListAdapter(Context context, List<Word> words) {
        super(context);
        mWords = new ArrayList<>();
        if (words != null) {
            mWords.addAll(words);
        }
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Word> itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    
    public void changeDataSet(List<Word> words){
        if (words != null) {
            mWords.clear();
            mWords.addAll(words);
        }else{
            mWords = new ArrayList<>();
            mWords.addAll(words);
        }
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWordListBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_word_list, parent, false);
        return new ItemViewHolder(binding, mItemClickListener);
    }
    
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mWords.get(position));
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemWordListBinding mBinding;
        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Word> mItemClickListener;

        public ItemViewHolder(ItemWordListBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Word> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Word word) {
            mBinding.setItem(word);
            mBinding.setItemListener(mItemClickListener);
            mBinding.setImageUrl("https://i.imgur.com/SVxB2jr.jpg");
            mBinding.executePendingBindings();
        }
    }
}
