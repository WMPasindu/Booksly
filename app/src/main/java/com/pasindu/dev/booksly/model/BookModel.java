package com.pasindu.dev.booksly.model;

public class BookModel {

    private int course_image;
    private String book_title;
    private String book_author;
    private double book_price;
    private int book_rating;

    public BookModel(int course_image, String book_title, String book_author, double book_price, int book_rating) {
        this.course_image = course_image;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_price = book_price;
        this.book_rating = book_rating;
    }

    public int getCourse_image() {
        return course_image;
    }

    public void setCourse_image(int course_image) {
        this.course_image = course_image;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    public int getBook_rating() {
        return book_rating;
    }

    public void setBook_rating(int book_rating) {
        this.book_rating = book_rating;
    }
}
