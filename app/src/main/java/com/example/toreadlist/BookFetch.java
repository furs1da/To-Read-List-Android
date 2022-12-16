package com.example.toreadlist;

import java.util.ArrayList;

public class BookFetch {

    private String title;
    private String description;
    private String publisher;
    private String publishedDate;

    private int pageCount;
    private Double averageRating;

    private String imageUrl;
    private String bookDetailsLink;
    private String buyLink;

    private ArrayList<String> authors;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getBookDetailsLink() {
        return bookDetailsLink;
    }

    public void setBookDetailsLink(String bookDetailsLink) {
        this.bookDetailsLink = bookDetailsLink;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }


    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    //creating a constructr class for our BookInfo
    public BookFetch(String title,  String description, String publisher, String publishedDate,
                     int pageCount, Double averageRating,String imageUrl,
                     String bookDetailsLink, String buyLink,
                     ArrayList<String> authors) {
        this.title = title;
        this.description = description;

        this.publisher = publisher;
        this.publishedDate = publishedDate;

        this.pageCount = pageCount;
        this.averageRating = averageRating;

        this.imageUrl = imageUrl;
        this.bookDetailsLink = bookDetailsLink;
        this.buyLink = buyLink;
        this.authors = authors;
    }
}
