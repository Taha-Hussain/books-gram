package com.ticktech.booksgram.model;

/**
 * Created by Taha on 18/03/2016.
 */
public class Books {

    private int book_id;
    private String book_name;
    private String book_publisher;
    private float book_rating;
    private String book_logo;
    private String book_price;
    private String book_description;
    private String book_link;

    private String book_publishYear;
    private String book_shortDescription;
    private String book_categoryName;



    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public float getBook_rating() {
        return book_rating;
    }

    public void setBook_rating(float book_rating) {
        this.book_rating = book_rating;
    }

    public String getBook_logo() {
        return book_logo;
    }

    public void setBook_logo(String book_logo) {
        this.book_logo = book_logo;
    }

    public String getBook_price() {
        return book_price;
    }

    public void setBook_price(String book_price) {
        this.book_price = book_price;
    }

    public String getBook_link() {
        return book_link;
    }

    public void setBook_link(String book_link) {
        this.book_link = book_link;
    }

    public String getBook_categoryName() {
        return book_categoryName;
    }

    public void setBook_categoryName(String book_categoryName) {
        this.book_categoryName = book_categoryName;
    }

    public String getBook_shortDescription() {
        return book_shortDescription;
    }

    public void setBook_shortDescription(String book_shortDescription) {
        this.book_shortDescription = book_shortDescription;
    }

    public String getBook_publishYear() {
        return book_publishYear;
    }

    public void setBook_publishYear(String book_publishYear) {
        this.book_publishYear = book_publishYear;
    }
}
