package com.androidchallenge.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidchallenge.data.model.Article;
import com.androidchallenge.utils.DateUtils;
import com.androidchallenge.utils.ImageLoaderUtils;

import java.util.List;


import com.androidchallenge.databinding.ItemArticleBinding;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {
    private final List<Article> articles;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Article article);
    }

    public ArticlesAdapter(List<Article> articles, OnItemClickListener listener) {
        this.articles = articles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemArticleBinding binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.binding.articleTitle.setText(article.getTitle());
        holder.binding.articleDate.setText(article.getPublishedAt());

        ImageLoaderUtils.loadImage(holder.binding.getRoot().getContext(),
                article.getUrlToImage(),
                holder.binding.articleImage,
                com.androidchallenge.R.drawable.placeholder_image,  // Placeholder while loading
                com.androidchallenge.R.drawable.placeholder_image ,  // Error image if failed
                20
        );

        holder.binding.readMoreButton.setOnClickListener(v -> listener.onItemClick(article));
        holder.binding.articleDate.setText(DateUtils.formatDate(article.getPublishedAt()));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private final ItemArticleBinding binding;

        public ArticleViewHolder(ItemArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

