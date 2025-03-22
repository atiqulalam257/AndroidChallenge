package com.androidchallenge.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Represents the response received from the news API.
 * Contains the status of the request, total number of results, and a list of articles.
 */
public class NewsResponse {

    /** The status of the API response (e.g., "ok" or "error"). */
    @SerializedName("status")
    private String status;

    /** The total number of articles available in the response. */
    @SerializedName("totalResults")
    private int totalResults;

    /** A list of articles retrieved from the API. */
    @SerializedName("articles")
    private List<Article> articles;

    /**
     * Gets the status of the API response.
     *
     * @return The status as a string (e.g., "ok", "error").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the total number of articles available in the response.
     *
     * @return The total count of articles.
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * Gets the list of articles retrieved from the API.
     *
     * @return A list of {@link Article} objects.
     */
    public List<Article> getArticles() {
        return articles;
    }
}

