/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nmt.service;

import com.nmt.model.Post;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public interface PostService {
    List<Post> getPosts(Map<String, String> params);
    Post addPost(Post post);
    Post getPostByid(int id);
    Boolean deletePost(int id);
}
