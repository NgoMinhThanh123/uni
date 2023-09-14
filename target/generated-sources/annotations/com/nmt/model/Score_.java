package com.nmt.model;

import com.nmt.model.ScoreValue;
import com.nmt.model.Semester;
import com.nmt.model.StudentSubject;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-14T16:24:43", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Score.class)
public class Score_ { 

    public static volatile SingularAttribute<Score, StudentSubject> studentSubjectId;
    public static volatile SingularAttribute<Score, Semester> semesterId;
    public static volatile SetAttribute<Score, ScoreValue> scoreValueSet;
    public static volatile SingularAttribute<Score, Integer> id;

}