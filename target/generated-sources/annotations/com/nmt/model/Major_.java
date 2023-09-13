package com.nmt.model;

import com.nmt.model.Faculty;
import com.nmt.model.Student;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-13T14:05:29", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Major.class)
public class Major_ { 

    public static volatile SingularAttribute<Major, Faculty> facultyId;
    public static volatile SetAttribute<Major, Student> studentSet;
    public static volatile SingularAttribute<Major, String> name;
    public static volatile SingularAttribute<Major, String> id;

}