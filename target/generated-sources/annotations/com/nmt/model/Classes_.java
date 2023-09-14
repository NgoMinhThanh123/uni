package com.nmt.model;

import com.nmt.model.ClassesSubject;
import com.nmt.model.Faculty;
import com.nmt.model.Lecturer;
import com.nmt.model.Student;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-14T16:24:43", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Classes.class)
public class Classes_ { 

    public static volatile SetAttribute<Classes, Student> studentSet;
    public static volatile SingularAttribute<Classes, Faculty> facultyId;
    public static volatile SetAttribute<Classes, ClassesSubject> classesSubjectSet;
    public static volatile SetAttribute<Classes, Lecturer> lecturerSet;
    public static volatile SingularAttribute<Classes, String> id;

}