package com.androidchallenge.data.model;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Represents an article object retrieved from the API.
 * Implements {@link Serializable} to allow object serialization.
 */
public class Article implements Serializable {

    /** The source of the article (e.g., news agency, blog). */
    @SerializedName("source")
    private Source source;

    /** The author of the article. Can be null if not provided. */
    @SerializedName("author")
    private String author;

    /** The title of the article. */
    @SerializedName("title")
    private String title;

    /** A short description or summary of the article. */
    @SerializedName("description")
    private String description;

    /** The URL to the full article. */
    @SerializedName("url")
    private String url;

    /** The URL of the article's featured image. */
    @SerializedName("urlToImage")
    private String urlToImage;

    /** The published date and time of the article in ISO 8601 format (e.g., "2025-03-06T20:00:00Z"). */
    @SerializedName("publishedAt")
    private String publishedAt;

    /** The main content of the article. */
    @SerializedName("content")
    private String content;

    /**
     * Gets the source of the article.
     *
     * @return The source object containing source details.
     */
    public Source getSource() {
        return source;
    }

    /**
     * Gets the author of the article.
     *
     * @return The author's name as a string, or null if not available.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the title of the article.
     *
     * @return The article title as a string.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the description of the article.
     *
     * @return A short description of the article.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the URL of the full article.
     *
     * @return The full article URL as a string.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the URL of the article's featured image.
     *
     * @return The URL of the image.
     */
    public String getUrlToImage() {
        return urlToImage;
    }

    /**
     * Gets the publication date of the article.
     *
     * @return The published date in ISO 8601 format.
     */
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * Gets the content of the article.
     *
     * @return The main body content of the article.
     */
    public String getContent() {
        return content;
    }
}

