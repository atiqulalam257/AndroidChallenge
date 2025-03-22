package com.androidchallenge.ui;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.androidchallenge.R;
import com.androidchallenge.data.model.Article;
import com.androidchallenge.databinding.ActivityArticleDetailBinding;
import com.androidchallenge.utils.DateUtils;
import com.androidchallenge.utils.ImageLoaderUtils;
import com.androidchallenge.utils.NetworkLiveData;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ArticleDetailActivity extends AppCompatActivity {
    private ActivityArticleDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticleDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar_background));
        Article article = (Article) getIntent().getSerializableExtra("article");
        if (article != null) {
            binding.articleTitle.setText(article.getTitle());
            binding.articleContent.setText(article.getContent());
            ImageLoaderUtils.loadImage(this,
                    article.getUrlToImage(),
                    binding.articleImage,
                    R.drawable.placeholder_image,  // Placeholder while loading
                    R.drawable.placeholder_image,   // Error image if failed
                    20
            );
            binding.articleTime.setText(DateUtils.getTimeAgo(article.getPublishedAt()));
        }


        binding.backButton.setOnClickListener(v -> finish());

    }
}
