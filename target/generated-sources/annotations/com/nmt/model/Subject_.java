package com.nmt.model;

import com.nmt.model.ClassesSubject;
import com.nmt.model.Faculty;
import com.nmt.model.LecturerSubject;
import com.nmt.model.StudentSubject;
import com.nmt.model.SubjectSemester;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-13T14:05:29", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Subject.class)
public class Subject_ { 

    public static volatile SingularAttribute<Subject, Faculty> facultyId;
    public static volatile SetAttribute<Subject, ClassesSubject> classesSubjectSet;
    public static volatile SetAttribute<Subject, LecturerSubject> lecturerSubjectSet;
    public static volatile SingularAttribute<Subject, String> name;
    public static volatile SetAttribute<Subject, StudentSubject> studentSubjectSet;
    public static volatile SingularAttribute<Subject, String> id;
    public static volatile SingularAttribute<Subject, Integer> credit;
    public static volatile SetAttribute<Subject, SubjectSemester> subjectSemesterSet;

}