package com.buschmais.jqassistant.plugin.java.impl.store.descriptor;

import static com.buschmais.xo.neo4j.api.annotation.Relation.Incoming;
import static com.buschmais.xo.neo4j.api.annotation.Relation.Outgoing;

import com.buschmais.xo.neo4j.api.annotation.Relation;

/**
 * Defines a READs relation between a method and a field.
 */
@Relation("READS")
public interface ReadsDescriptor extends LineNumberDescriptor {

    @Outgoing
    MethodDescriptor getMethod();

    @Incoming
    FieldDescriptor getField();

}
