package com.androidchallenge.ui.articles;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidchallenge.data.model.Article;
import com.androidchallenge.utils.DateUtils;
import com.androidchallenge.utils.ImageLoaderUtils;

import java.util.List;


import com.androidchallenge.databinding.ItemArticleBinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.androidchallenge.databinding.ItemArticleBinding;
import java.util.List;

/**
 * Adapter for displaying a list of articles in a RecyclerView.
 * Handles binding article data to the UI components.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {
    private final List<Article> articles;
    private final OnItemClickListener listener;

    /**
     * Interface for handling click events on an article item.
     */
    public interface OnItemClickListener {
        /**
         * Called when an article item is clicked.
         * @param article The clicked article.
         */
        void onItemClick(Article article);
    }

    /**
     * Constructor for the ArticlesAdapter.
     *
     * @param articles The list of articles to display.
     * @param listener The click listener for handling item clicks.
     */
    public ArticlesAdapter(List<Article> articles, OnItemClickListener listener) {
        this.articles = articles;
        this.listener = listener;
    }

    /**
     * Inflates the item layout and creates a new ViewHolder.
     *
     * @param parent The parent ViewGroup.
     * @param viewType The view type of the new View.
     * @return A new ArticleViewHolder instance.
     */
    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemArticleBinding binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ArticleViewHolder(binding);
    }

    /**
     * Binds article data to the ViewHolder.
     *
     * @param holder The ViewHolder instance.
     * @param position The position of the item in the dataset.
     */
    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.binding.articleTitle.setText(article.getTitle());
        holder.binding.articleDate.setText(article.getPublishedAt());

        // Load article image using Glide utility
        ImageLoaderUtils.loadImage(holder.binding.getRoot().getContext(),
                article.getUrlToImage(),
                holder.binding.articleImage,
                com.androidchallenge.R.drawable.placeholder_image,  // Placeholder while loading
                com.androidchallenge.R.drawable.placeholder_image,  // Error image if failed
                20
        );

        // Handle "Read More" button click
        holder.binding.readMoreButton.setOnClickListener(v -> listener.onItemClick(article));

        // Format and display article date
        holder.binding.articleDate.setText(DateUtils.formatDate(article.getPublishedAt()));
    }

    /**
     * Returns the total number of articles.
     *
     * @return The size of the articles list.
     */
    @Override
    public int getItemCount() {
        return articles.size();
    }

    /**
     * ViewHolder class for holding the article item views.
     */
    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private final ItemArticleBinding binding;

        /**
         * Constructor for the ViewHolder.
         *
         * @param binding The view binding for the article item.
         */
        public ArticleViewHolder(ItemArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}


