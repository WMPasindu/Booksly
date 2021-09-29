package com.pasindu.dev.booksly.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pasindu.dev.booksly.R;
import com.pasindu.dev.booksly.model.BookModel;
import com.pasindu.dev.booksly.ui.fragments.comments.CommentsFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BooksActivity extends AppCompatActivity {

    @BindView(R.id.img_book)
    ImageView img_book;
    @BindView(R.id.book_price)
    TextView book_price;
    @BindView(R.id.book_description)
    TextView book_description;
    @BindView(R.id.book_name)
    TextView book_name;
    @BindView(R.id.rating_bar)
    RatingBar rating_bar;
    @BindView(R.id.btn_show_comments)
    Button btn_show_comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        BookModel bookModel = (BookModel) intent.getSerializableExtra("BUNDLE");
        displayInfo(bookModel);
    }

    private void displayInfo(BookModel bookModel) {
        Glide.with(this).load(bookModel.getCourse_image()).into(img_book);
        book_name.setText(new StringBuilder(bookModel.getBook_title()));
        book_price.setText(new StringBuilder("" + bookModel.getBook_price()));
        book_description.setText(new StringBuilder(bookModel.getBook_author()));

        if (bookModel.getBook_rating() != 0)
            rating_bar.setRating(bookModel.getBook_rating());

//        getApplication().getSupportActionBar()
//                .setTitle(Common.selectFood.getName());

    }

    @OnClick(R.id.btn_show_comments)
    void onShowComments() {
        CommentsFragment commentFragment = CommentsFragment.getInstance();
        commentFragment.show(this.getSupportFragmentManager(), "CommentFragment");
    }
}