package com.nmt.model;

import com.nmt.model.ScoreValue;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-14T16:24:43", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ScoreColumn.class)
public class ScoreColumn_ { 

    public static volatile SingularAttribute<ScoreColumn, String> name;
    public static volatile SetAttribute<ScoreColumn, ScoreValue> scoreValueSet;
    public static volatile SingularAttribute<ScoreColumn, Integer> id;

}