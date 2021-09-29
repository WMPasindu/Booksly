package com.pasindu.dev.booksly.interfaces;

import com.pasindu.dev.booksly.model.CommentsModel;

import java.util.List;

public interface ICommentCallbackListener {
    void onCommentLoadSuccess(List<CommentsModel> commentsModels);
    void onCommentLOadFailed(String message);
}
