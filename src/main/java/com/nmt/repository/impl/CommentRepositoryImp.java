/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.repository.impl;

import com.nmt.model.Comment;
import com.nmt.model.Post;
import com.nmt.model.User;
import com.nmt.repository.CommentRepository;
import com.nmt.repository.PostRepository;
import com.nmt.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author acer
 */
@Repository
@Transactional
public class CommentRepositoryImp implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PostRepository postRepo;

    @Override
    public List<Comment> getComments(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Comment> q = b.createQuery(Comment.class);
        Root root = q.from(Post.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("content"), String.format("%%%s%%", kw)));
            }
            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("id")));

        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Comment> getCommentByPostId(int postId) {

        Session s = this.factory.getObject().getCurrentSession();

        List<Object[]> objects = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();

        try {
            String sql = "SELECT comment.id, comment.content, comment.date_created, user.id as user_id, post.id as post_id\n"
                    + " FROM comment join post on comment.post_id = post.id\n"
                    + " join user on comment.user_id = user.id\n"
                    + " where post.id = :postId";
            Query query = s.createNativeQuery(sql);
            query.setParameter("postId", postId);
            objects = query.getResultList();
            for (int i = 0; i < objects.size(); i++) {
                Comment comment = new Comment();
                User user = this.userRepo.getUserById(Integer.parseInt(objects.get(i)[3].toString()));
                Post post = this.postRepo.getPostByid(Integer.parseInt(objects.get(i)[4].toString()));
                comment.setId(Integer.parseInt(objects.get(i)[0].toString()));
                comment.setContent(objects.get(i)[1].toString());
                comment.setDateCreated((Date) objects.get(i)[2]);
                comment.setUserId(user);
                comment.setPostId(post);
                comments.add(comment);
            }
            System.out.println(comments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public Comment addComment(Comment comment) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(comment);
        return comment;
    }

}
