/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.service;

import com.nmt.model.Comment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface CommentService {
    List<Comment> getComments(Map<String, String> params);
    List<Comment> getCommentByPostId(int postId);
    Comment addComment(Comment comment);
}
