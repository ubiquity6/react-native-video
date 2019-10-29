package com.brentvatne.exoplayer.cache;

import com.facebook.react.bridge.ReactContext;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;

public class HlsCache {
    private static volatile SimpleCache cache;
    private static final int CACHE_SIZE = 25 * 1024 * 1024;

    public static Cache getCache(ReactContext context) {
        if (cache == null) {
            synchronized (HlsCache.class) {
                LeastRecentlyUsedCacheEvictor evictor = new LeastRecentlyUsedCacheEvictor(CACHE_SIZE);
                cache = new SimpleCache(new File(context.getFilesDir(), "media"), evictor);
            }
        }
        return cache;
    }
}
