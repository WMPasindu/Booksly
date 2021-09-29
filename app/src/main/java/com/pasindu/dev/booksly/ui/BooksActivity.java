package com.pasindu.dev.booksly.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pasindu.dev.booksly.R;
import com.pasindu.dev.booksly.common.Common;
import com.pasindu.dev.booksly.model.BookModel;
import com.pasindu.dev.booksly.model.CommentsModel;
import com.pasindu.dev.booksly.ui.fragments.comments.CommentsFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BooksActivity extends AppCompatActivity {

    @BindView(R.id.img_book)
    ImageView img_book;
    @BindView(R.id.book_downloads)
    TextView book_downloads;
    @BindView(R.id.book_description)
    TextView book_description;
    @BindView(R.id.book_name)
    TextView book_name;
    @BindView(R.id.book_author)
    TextView book_author;
    @BindView(R.id.book_category)
    TextView book_category;
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
        book_downloads.setText(new StringBuilder(Common.formatPrice(bookModel.getBook_price())));
        book_author.setText(new StringBuilder(bookModel.getBook_author()));
        book_category.setText(new StringBuilder("Story"));
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

    @OnClick(R.id.btn_rating)
    void onShowRatings() {
        showDialogRating();
    }

    private void showDialogRating() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Rate The Book");
        builder.setMessage("Please add your rating for this book.");

        View itemView = LayoutInflater.from(this).inflate(R.layout.layout_rating_bar, null);
        RatingBar ratingBar = (RatingBar) itemView.findViewById(R.id.rating_bar);
        EditText comments = (EditText) itemView.findViewById(R.id.edit_comment);

        builder.setView(itemView);
        builder.setNegativeButton("CANCEL", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });

        builder.setPositiveButton("OK", (dialogInterface, i) -> {

            CommentsModel commentsModel = new CommentsModel();
//            commentsModel.setUid(Common.currentUser.getUserId());
//            commentsModel.setName(Common.currentUser.getUserName());
//            commentsModel.setComments(comments.getText().toString());
//            commentsModel.setRatingValue(ratingBar.getRating());
//            Map<String, Object> serverTimeStamp = new HashMap<>();
//            serverTimeStamp.put("timeStamp", ServerValue.TIMESTAMP);
//            commentsModel.setCommentsTimeStamp(serverTimeStamp);
//
//            foodDetailViewModel.setCommentModel(commentsModel);

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}