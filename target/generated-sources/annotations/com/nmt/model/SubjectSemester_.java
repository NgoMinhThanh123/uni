package com.nmt.model;

import com.nmt.model.Semester;
import com.nmt.model.Subject;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-14T16:24:43", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(SubjectSemester.class)
public class SubjectSemester_ { 

    public static volatile SingularAttribute<SubjectSemester, Semester> semesterId;
    public static volatile SingularAttribute<SubjectSemester, Integer> id;
    public static volatile SingularAttribute<SubjectSemester, Subject> subjectId;

}