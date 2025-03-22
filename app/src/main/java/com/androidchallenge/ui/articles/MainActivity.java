package com.androidchallenge.ui.articles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.androidchallenge.R;
import com.androidchallenge.data.prefrences.SharedPreferencesHelper;
import com.androidchallenge.databinding.ActivityMainBinding;
import com.androidchallenge.ui.article.ArticleDetailActivity;
import com.androidchallenge.ui.ArticlesViewModel;
import com.androidchallenge.utils.ImageLoaderUtils;
import com.androidchallenge.utils.NetworkLiveData;
import com.androidchallenge.utils.NetworkUtils;

import java.io.IOException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.disposables.Disposable;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    SharedPreferencesHelper preferencesHelper;  // Injected SharedPreferencesHelper

    @Inject
    ArticlesViewModel viewModel;

    private ActivityMainBinding binding;
    private boolean isGridView;
    private ArticlesAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (!NetworkUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, "No internet connection. Some features may not work.", Toast.LENGTH_LONG).show();
        }

        isGridView = preferencesHelper.getViewMode();
        setLayoutManager();

        binding.gridIcon.setOnClickListener(v -> {
            isGridView = !isGridView;
            preferencesHelper.saveViewMode(isGridView);
            setLayoutManager();
        });


        if (NetworkUtils.isNetworkAvailable(this)){
            callApi();
        } else {
            binding.noInternetLayout.setVisibility(View.VISIBLE);
        }
        binding.txtRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi();
            }
        });


        NetworkLiveData.getInstance(this).observe(this, isConnected -> {
            if (isConnected) {
                Toast.makeText(MainActivity.this, "Internet Connected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void callApi(){
        // Store the Disposable returned by subscribe()
        binding.pb.setVisibility(View.VISIBLE);
        Disposable disposable = viewModel.getArticles()
                .subscribe(
                        response -> {
                            binding.pb.setVisibility(View.GONE);
                            if (response != null) {
                                adapter = new ArticlesAdapter(response.getArticles(), article -> {
                                    Intent intent = new Intent(this, ArticleDetailActivity.class);
                                    intent.putExtra("article", article);
                                    startActivity(intent);
                                });
                                binding.recyclerView.setAdapter(adapter);
                                binding.recyclerView.setVisibility(View.VISIBLE);
                                binding.noInternetLayout.setVisibility(View.GONE);
                            } else {
                                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable -> {
                            binding.pb.setVisibility(View.GONE);
                            binding.recyclerView.setVisibility(View.GONE);
                            if (throwable instanceof IOException) {
                                binding.noInternetLayout.setVisibility(View.VISIBLE);
                                Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }
                );



        viewModel.getDisposable().add(disposable);
    }

    private void setLayoutManager() {
        if (isGridView) {
            binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            ImageLoaderUtils.loadImage(this, R.drawable.ic_list, binding.gridIcon);
        } else {
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ImageLoaderUtils.loadImage(this, R.drawable.ic_grid, binding.gridIcon);
        }
    }
}
