package com.nmt.model;

import com.nmt.model.Lecturer;
import com.nmt.model.Student;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-13T22:54:45", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SetAttribute<User, Student> studentSet;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> role;
    public static volatile SetAttribute<User, Lecturer> lecturerSet;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}