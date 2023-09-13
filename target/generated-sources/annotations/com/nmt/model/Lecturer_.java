package com.nmt.model;

import com.nmt.model.Classes;
import com.nmt.model.Faculty;
import com.nmt.model.LecturerSubject;
import com.nmt.model.User;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-13T14:40:32", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Lecturer.class)
public class Lecturer_ { 

    public static volatile SingularAttribute<Lecturer, Date> birthday;
    public static volatile SingularAttribute<Lecturer, Faculty> facultyId;
    public static volatile SingularAttribute<Lecturer, Classes> classesId;
    public static volatile SingularAttribute<Lecturer, String> address;
    public static volatile SetAttribute<Lecturer, LecturerSubject> lecturerSubjectSet;
    public static volatile SingularAttribute<Lecturer, Short> gender;
    public static volatile SingularAttribute<Lecturer, String> phone;
    public static volatile SingularAttribute<Lecturer, String> name;
    public static volatile SingularAttribute<Lecturer, String> id;
    public static volatile SingularAttribute<Lecturer, User> userId;
    public static volatile SingularAttribute<Lecturer, String> email;

}