package com.pasindu.dev.booksly.ui.fragments.comments;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pasindu.dev.booksly.model.CommentsModel;

import java.util.List;

public class CommentViewModel  extends ViewModel {
    private MutableLiveData<List<CommentsModel>> mutableLiveDataFoodList;

    public CommentViewModel() {
        mutableLiveDataFoodList = new MutableLiveData();
    }

    public MutableLiveData<List<CommentsModel>> getMutableLiveDataFoodList() {
        return mutableLiveDataFoodList;
    }

    public void setCommentList(List<CommentsModel> commnentList) {
        mutableLiveDataFoodList.setValue(commnentList);
    }
}