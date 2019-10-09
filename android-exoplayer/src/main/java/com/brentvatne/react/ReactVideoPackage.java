package com.brentvatne.react;

import com.brentvatne.exoplayer.ReactExoplayerViewManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.List;
import java.io.File;

import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;

public class ReactVideoPackage implements ReactPackage {
    private SimpleCache cache = null;

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    // Deprecated RN 0.47	
    public List<Class<? extends JavaScriptModule>> createJSModules() {	
        return Collections.emptyList();	
    }	

    public SimpleCache getSimpleCache(ReactApplicationContext reactContext) {
        if (this.cache == null) {
            LeastRecentlyUsedCacheEvictor evictor = new LeastRecentlyUsedCacheEvictor(20 * 1024 * 1024);
            this.cache = new SimpleCache(new File(reactContext.getCacheDir(), "media"), evictor);
            return this.cache;
        }
        return this.cache;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.<ViewManager>singletonList(new ReactExoplayerViewManager(this.getSimpleCache(reactContext)));
    }
}
