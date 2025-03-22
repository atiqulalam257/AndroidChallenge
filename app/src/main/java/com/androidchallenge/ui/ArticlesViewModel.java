package com.androidchallenge.ui;

import androidx.lifecycle.ViewModel;

import com.androidchallenge.data.model.NewsResponse;
import com.androidchallenge.data.api.ApiService;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ArticlesViewModel extends ViewModel {
    private final ApiService apiService;
    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public ArticlesViewModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public CompositeDisposable getDisposable() {
        return disposable;
    }

    public Observable<NewsResponse> getArticles() {
        return apiService.getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(errors -> errors.flatMap(throwable -> {
                    if (throwable instanceof IOException) {
                        return Observable.timer(3, TimeUnit.SECONDS); // Retry after 3 seconds
                    }
                    return Observable.error(throwable); // Propagate other errors
                }));
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
