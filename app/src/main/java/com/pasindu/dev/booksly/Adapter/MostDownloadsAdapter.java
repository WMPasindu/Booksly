package com.pasindu.dev.booksly.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pasindu.dev.booksly.R;
import com.pasindu.dev.booksly.interfaces.BooksAdapterInterface;
import com.pasindu.dev.booksly.model.BookModel;

import java.util.ArrayList;

public class MostDownloadsAdapter extends RecyclerView.Adapter<MostDownloadsAdapter.Viewholder> {
    private Context context;
    private ArrayList<BookModel> bookModelArrayList;
    private BooksAdapterInterface clickListener;

    // Constructor
    public MostDownloadsAdapter(Context context, ArrayList<BookModel> bookModelArrayList) {
        this.context = context;
        this.bookModelArrayList = bookModelArrayList;
    }

    @NonNull
    @Override
    public MostDownloadsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_view, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostDownloadsAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        BookModel model = bookModelArrayList.get(position);
        holder.id_text_view_book_title.setText(model.getBook_title());
        holder.id_text_view_author.setText(model.getBook_author());
        holder.id_text_view_amount.setText("Rs. " + model.getBook_price());
        holder.id_text_view_ratings.setText(""+ model.getBook_rating());
        holder.id_image_view_book_image.setImageResource(model.getCourse_image());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return bookModelArrayList.size();
    }

    public void setClickListener(BooksAdapterInterface itemClickListener) {
        this.clickListener = itemClickListener;
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView id_image_view_book_image;
        private TextView id_text_view_book_title, id_text_view_author, id_text_view_amount, id_text_view_ratings;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            id_image_view_book_image = itemView.findViewById(R.id.id_image_view_book_image);
            id_text_view_book_title = itemView.findViewById(R.id.id_text_view_book_title);
            id_text_view_author = itemView.findViewById(R.id.id_text_view_author);
            id_text_view_amount = itemView.findViewById(R.id.id_text_view_amount);
            id_text_view_ratings = itemView.findViewById(R.id.id_text_view_ratings);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
