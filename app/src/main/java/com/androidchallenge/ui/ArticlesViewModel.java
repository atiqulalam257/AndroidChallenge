package com.androidchallenge.ui;

import androidx.lifecycle.ViewModel;
import com.androidchallenge.data.model.NewsResponse;
import com.androidchallenge.data.api.ApiService;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * ViewModel for managing and fetching article data from the API.
 * Uses RxJava for asynchronous API calls and handles retry logic for network failures.
 */
public class ArticlesViewModel extends ViewModel {

    /** API service instance for fetching articles. */
    private final ApiService apiService;

    /** Manages disposable RxJava subscriptions to prevent memory leaks. */
    private final CompositeDisposable disposable = new CompositeDisposable();

    /**
     * Constructor that injects the API service.
     *
     * @param apiService The API service used to fetch articles.
     */
    @Inject
    public ArticlesViewModel(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     * Returns the CompositeDisposable instance to manage RxJava subscriptions.
     *
     * @return The CompositeDisposable object.
     */
    public CompositeDisposable getDisposable() {
        return disposable;
    }

    /**
     * Fetches articles from the API asynchronously.
     * - Runs on the IO thread to avoid blocking the main thread.
     * - Observes results on the main thread.
     * - Implements retry logic for network failures (retries after 3 seconds if an IOException occurs).
     *
     * @return An observable emitting a {@link NewsResponse} containing the article list.
     */
    public Observable<NewsResponse> getArticles() {
        return apiService.getArticles()
                .subscribeOn(Schedulers.io()) // Perform network call in the background
                .observeOn(AndroidSchedulers.mainThread()) // Deliver results on the main thread
                .retryWhen(errors -> errors.flatMap(throwable -> {
                    if (throwable instanceof IOException) {
                        return Observable.timer(3, TimeUnit.SECONDS); // Retry after 3 seconds
                    }
                    return Observable.error(throwable); // Propagate other errors
                }));
    }

    /**
     * Called when the ViewModel is cleared.
     * Ensures that all active RxJava subscriptions are disposed of to prevent memory leaks.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear(); // Dispose all subscriptions
    }
}
