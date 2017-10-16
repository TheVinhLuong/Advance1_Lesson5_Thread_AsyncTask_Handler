package com.example.android.recyclerviewexample.screen.showlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.android.recyclerviewexample.R;
import com.example.android.recyclerviewexample.databinding.ActivityShowListBinding;
import com.example.android.recyclerviewexample.permission.PermissionHandler;
import com.example.android.recyclerviewexample.screen.BaseActivity;

/**
 * RecyclerView Screen.
 */
public class ShowListActivity extends BaseActivity {
    private static final String TAG = ShowListActivity.class.getSimpleName();
    private ShowListViewModel mViewModel;
    private ShowListContract.Presenter presenter;
    private PermissionHandler mPermissionHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPermissionHandler = new PermissionHandler(this);
        mViewModel = new ShowListViewModel();
        presenter =
                new ShowListPresenter(mViewModel, mPermissionHandler);
        mViewModel.setPresenter(presenter);
        ActivityShowListBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_show_list);
        binding.setViewModel(mViewModel);
        binding.setViewPagerAdapter(new ShowListPagerAdapter(getFragmentManager()));
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsReuslt");
        mViewModel.onRequestPermissionsReuslt(requestCode, permissions, grantResults);
    }
}
