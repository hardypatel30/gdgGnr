package com.example.android.navigationsample.pagedlist.data;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

public class ItempagedKeyDatasource extends ItemKeyedDataSource {
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @NonNull
    @Override
    public Object getKey(@NonNull Object item) {
        return null;
    }
}
