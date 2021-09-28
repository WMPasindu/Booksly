package com.pasindu.dev.booksly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.os.Handler;

import com.pasindu.dev.booksly.Adapter.MostDownloadsAdapter;
import com.pasindu.dev.booksly.Adapter.SliderAdapter;
import com.pasindu.dev.booksly.model.BookModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();
    private RecyclerView bookRecyclerView;
    private ArrayList<BookModel> bookModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        bookRecyclerView = findViewById(R.id.MostDownloadBookRecyclerView);

        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.book1));
        sliderItems.add(new SliderItems(R.drawable.book2));
        sliderItems.add(new SliderItems(R.drawable.book3));
        sliderItems.add(new SliderItems(R.drawable.book4));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000); // slide duration 2 seconds
            }
        });
        makeArray();
    }

    private void makeArray() {
        bookModelArrayList = new ArrayList<>();
        bookModelArrayList.add(new BookModel(R.drawable.book1, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", 5000.50, 5));
        bookModelArrayList.add(new BookModel(R.drawable.book2, "The Lord of the Rings", "J.R.R. Tolkien", 12000.60, 3));
        bookModelArrayList.add(new BookModel(R.drawable.book3, "Slaughterhouse-Five", "Kurt Vonnegut", 5200.75, 2));
        bookModelArrayList.add(new BookModel(R.drawable.book4, "To Kill a Mockingbird", "Harper Lee", 12200.00, 4));
        setAdapter();
    }

    private void setAdapter() {
        MostDownloadsAdapter courseAdapter = new MostDownloadsAdapter(this, bookModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        bookRecyclerView.setLayoutManager(linearLayoutManager);
        bookRecyclerView.setAdapter(courseAdapter);
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);
    }
}