package com.nmt.model;

import com.nmt.model.Score;
import com.nmt.model.SubjectSemester;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-13T14:05:29", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Semester.class)
public class Semester_ { 

    public static volatile SingularAttribute<Semester, Date> fromDate;
    public static volatile SingularAttribute<Semester, Date> toDate;
    public static volatile SingularAttribute<Semester, String> name;
    public static volatile SingularAttribute<Semester, Integer> schoolYear;
    public static volatile SingularAttribute<Semester, String> id;
    public static volatile SetAttribute<Semester, SubjectSemester> subjectSemesterSet;
    public static volatile SetAttribute<Semester, Score> scoreSet;

}