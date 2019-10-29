package com.brentvatne.exoplayer.cache;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;

public class CacheDataSourceFactory implements DataSource.Factory {
    private final DataSource.Factory defaultDataSourceFactory;
    private final long maxFileSize;
    private final Cache cache;

    public CacheDataSourceFactory(final Cache cache, final long maxFileSize, final DataSource.Factory factory) {
        this.cache = cache;
        this.maxFileSize = maxFileSize;
        this.defaultDataSourceFactory = factory;
    }

    @Override
    public DataSource createDataSource() {
        return new CacheDataSource(cache, defaultDataSourceFactory.createDataSource(),
                new FileDataSource(), new CacheDataSink(cache, maxFileSize),
                CacheDataSource.FLAG_BLOCK_ON_CACHE | CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR, null);
    }
}
