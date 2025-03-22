package com.androidchallenge.di;

import com.androidchallenge.data.api.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger Hilt module responsible for providing network-related dependencies.
 * This includes OkHttpClient, Retrofit, and ApiService.
 */
@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    /**
     * Provides a singleton instance of {@link OkHttpClient} with logging enabled.
     *
     * @return A configured OkHttpClient instance.
     */
    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    /**
     * Provides a singleton instance of {@link Retrofit} configured with a base URL,
     * Gson converter, and RxJava3 adapter.
     *
     * @param client The OkHttpClient instance to be used with Retrofit.
     * @return A configured Retrofit instance.
     */
    @Provides
    @Singleton
    public static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://mocki.io/v1/") // Base URL for API calls
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()) // Converts JSON to Java objects
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Supports RxJava3
                .build();
    }

    /**
     * Provides a singleton instance of {@link ApiService}, which defines API endpoints.
     *
     * @param retrofit The Retrofit instance used to create the service.
     * @return An implementation of ApiService.
     */
    @Provides
    @Singleton
    public static ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
