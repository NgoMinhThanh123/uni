package com.nmt.model;

import com.nmt.model.Comment;
import com.nmt.model.User;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-14T00:21:34", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, Date> postTime;
    public static volatile SetAttribute<Post, Comment> commentSet;
    public static volatile SingularAttribute<Post, Integer> id;
    public static volatile SingularAttribute<Post, String> title;
    public static volatile SingularAttribute<Post, User> userId;
    public static volatile SingularAttribute<Post, String> content;

}