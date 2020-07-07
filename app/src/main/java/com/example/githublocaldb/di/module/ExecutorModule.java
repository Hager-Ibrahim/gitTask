package com.example.githublocaldb.di.module;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class ExecutorModule {

    private static ExecutorModule instance;


    public static ExecutorModule getInstance(){
        if(instance == null){
            instance = new ExecutorModule();
        }
        return instance;
    }

    private final Executor mDiskIO = Executors.newSingleThreadExecutor(); // Will not use additional threads

    private final Executor mMainThreadExecutor = new MainThreadExecutor(); // executes on main thread

    public Executor diskIO() {
        return mDiskIO;
    }

    public Executor mainThread(){
        return mMainThreadExecutor;
    }


    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }


    /*
    @Provides
    @Singleton
    public ScheduledExecutorService providesNetworkIO(){
        final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);
        return mNetworkIO;
    }

     */


}
