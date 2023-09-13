package com.nmt.model;

import com.nmt.model.Score;
import com.nmt.model.ScoreColumn;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-13T14:05:29", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ScoreValue.class)
public class ScoreValue_ { 

    public static volatile SingularAttribute<ScoreValue, Score> scoreId;
    public static volatile SingularAttribute<ScoreValue, Integer> id;
    public static volatile SingularAttribute<ScoreValue, Double> value;
    public static volatile SingularAttribute<ScoreValue, ScoreColumn> scoreColumnId;

}