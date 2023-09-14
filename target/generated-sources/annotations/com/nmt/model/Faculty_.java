package com.nmt.model;

import com.nmt.model.Classes;
import com.nmt.model.Lecturer;
import com.nmt.model.Major;
import com.nmt.model.Student;
import com.nmt.model.Subject;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-14T16:24:43", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Faculty.class)
public class Faculty_ { 

    public static volatile SetAttribute<Faculty, Student> studentSet;
    public static volatile SetAttribute<Faculty, Lecturer> lecturerSet;
    public static volatile SetAttribute<Faculty, Major> majorSet;
    public static volatile SingularAttribute<Faculty, String> name;
    public static volatile SetAttribute<Faculty, Subject> subjectSet;
    public static volatile SetAttribute<Faculty, Classes> classesSet;
    public static volatile SingularAttribute<Faculty, String> id;

}