package com.androidchallenge.data.api;

import com.androidchallenge.data.model.NewsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * Fetches articles from the API using Retrofit.
 * @return NewsResponse with articles
 */

public interface ApiService {
    @GET("9b040bf5-62aa-4ba6-b3f2-f7a1e146097a")
    Observable<NewsResponse> getArticles();
}
