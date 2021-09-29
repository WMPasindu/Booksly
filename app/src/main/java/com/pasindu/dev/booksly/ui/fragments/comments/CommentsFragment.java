package com.pasindu.dev.booksly.ui.fragments.comments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.pasindu.dev.booksly.Adapter.CommentAdapter;
import com.pasindu.dev.booksly.R;
import com.pasindu.dev.booksly.interfaces.ICommentCallbackListener;
import com.pasindu.dev.booksly.model.CommentsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;


public class CommentsFragment extends BottomSheetDialogFragment implements ICommentCallbackListener{
    private Unbinder unbinder;
    private CommentViewModel commentViewModel;

    @BindView(R.id.recycler_comment)
    RecyclerView recycler_comment;

    AlertDialog dialog;
    ICommentCallbackListener listener;

    public CommentsFragment() {
        listener = this;
    }

    private static CommentsFragment instance;

    public static CommentsFragment getInstance() {
        if(instance == null)
            instance = new CommentsFragment();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = LayoutInflater.from(getContext())
                .inflate(R.layout.bottom_seet_comment_fragment,container,false);
        unbinder = ButterKnife.bind(this,itemView);
        initViews();
        loadCommentFromFirebase();
        commentViewModel.getMutableLiveDataFoodList().observe(this, commenntsModels -> {
            CommentAdapter adapter = new CommentAdapter(getContext(), commenntsModels);
            recycler_comment.setAdapter(adapter);

        });
        return itemView;
    }

    private void initViews() {
        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        dialog = new SpotsDialog.Builder().setContext(getContext()).setCancelable(false).build();

        recycler_comment.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, true);
        recycler_comment.setLayoutManager(layoutManager);
        recycler_comment.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
    }

    private void loadCommentFromFirebase() {
        dialog.show();
        List<CommentsModel> commentsModels = new ArrayList<>();
//        FirebaseDatabase.getInstance().getReference(Common.COMMENT_REF)
//                .child(Common.selectFood.getId())
//                .orderByChild("serverTimeStamp")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot commentSnapshot : dataSnapshot.getChildren()) {
//                            CommentsModel commentsModel = commentSnapshot.getValue(CommentsModel.class);
//                            commentsModels.add(commentsModel);
//                        }
//                        listener.onCommentLoadSuccess(commentsModels);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        listener.onCommentLOadFailed(databaseError.getMessage());
//                    }
//                });
    }

    @Override
    public void onCommentLoadSuccess(List<CommentsModel> commentsModels) {
        dialog.dismiss();
        commentViewModel.setCommentList(commentsModels);
    }

    @Override
    public void onCommentLOadFailed(String message) {
        dialog.dismiss();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
