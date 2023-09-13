package com.nmt.model;

import com.nmt.model.Lecturer;
import com.nmt.model.Subject;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-13T14:05:29", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(LecturerSubject.class)
public class LecturerSubject_ { 

    public static volatile SingularAttribute<LecturerSubject, Lecturer> lecturerId;
    public static volatile SingularAttribute<LecturerSubject, Integer> id;
    public static volatile SingularAttribute<LecturerSubject, Subject> subjectId;

}