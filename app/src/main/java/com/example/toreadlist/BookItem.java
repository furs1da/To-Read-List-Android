package com.example.toreadlist;

import java.util.ArrayList;

public class BookItem {

    private Integer id;
    private String title;
    private String description;
    private String publisher;
    private String publishedDate;
    private int pageCount;

    private String imageUrl;
    private String bookDetailsLink;

    private Boolean isCompleted;
    private String language;


    public Integer getId() {
        return id;
    }

    public void setId(Integer isCompleted) {
        this.id = id;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

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


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }



    public BookItem(String title,  String description, String publisher, String publishedDate,
                     int pageCount, String imageUrl,
                     String bookDetailsLink, String language, Boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.language = language;

        this.publisher = publisher;
        this.publishedDate = publishedDate;

        this.pageCount = pageCount;


        this.imageUrl = imageUrl;
        this.bookDetailsLink = bookDetailsLink;
        this.isCompleted = isCompleted;
    }

    public BookItem(String title,  String description, String publisher, String publishedDate,
                    int pageCount, String imageUrl,
                    String bookDetailsLink, String language, Boolean isCompleted, Integer id) {
        this.title = title;
        this.description = description;
        this.language = language;

        this.publisher = publisher;
        this.publishedDate = publishedDate;

        this.pageCount = pageCount;

        this.imageUrl = imageUrl;
        this.bookDetailsLink = bookDetailsLink;
        this.isCompleted = isCompleted;
        this.id = id;
    }

    public BookItem() {

    }
}
