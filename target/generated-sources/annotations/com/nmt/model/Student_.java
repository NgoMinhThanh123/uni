package com.nmt.model;

import com.nmt.model.Classes;
import com.nmt.model.Faculty;
import com.nmt.model.Major;
import com.nmt.model.StudentSubject;
import com.nmt.model.User;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-14T17:14:06", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, Date> birthday;
    public static volatile SingularAttribute<Student, Faculty> facultyId;
    public static volatile SingularAttribute<Student, Classes> classesId;
    public static volatile SingularAttribute<Student, String> address;
    public static volatile SingularAttribute<Student, Major> majorId;
    public static volatile SingularAttribute<Student, Short> gender;
    public static volatile SingularAttribute<Student, String> phone;
    public static volatile SingularAttribute<Student, String> name;
    public static volatile SetAttribute<Student, StudentSubject> studentSubjectSet;
    public static volatile SingularAttribute<Student, String> id;
    public static volatile SingularAttribute<Student, User> userId;

}