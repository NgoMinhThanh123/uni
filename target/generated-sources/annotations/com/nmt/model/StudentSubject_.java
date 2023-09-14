package com.nmt.model;

import com.nmt.model.Student;
import com.nmt.model.Subject;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-14T17:14:06", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(StudentSubject.class)
public class StudentSubject_ { 

    public static volatile SingularAttribute<StudentSubject, Student> studentId;
    public static volatile SingularAttribute<StudentSubject, Integer> id;
    public static volatile SingularAttribute<StudentSubject, Subject> subjectId;

}