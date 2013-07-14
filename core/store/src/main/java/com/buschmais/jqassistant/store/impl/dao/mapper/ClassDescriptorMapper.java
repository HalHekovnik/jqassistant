package com.buschmais.jqassistant.store.impl.dao.mapper;

import com.buschmais.jqassistant.store.api.DescriptorDAO.CoreLabel;
import com.buschmais.jqassistant.store.api.model.AbstractDescriptor;
import com.buschmais.jqassistant.store.api.model.ClassDescriptor;
import com.buschmais.jqassistant.store.impl.model.RelationType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ClassDescriptorMapper extends AbstractDescriptorMapper<ClassDescriptor> {

    @Override
    public Class<ClassDescriptor> getJavaType() {
        return ClassDescriptor.class;
    }

    @Override
    public CoreLabel getCoreLabel() {
        return CoreLabel.CLASS;
    }

    @Override
    public ClassDescriptor createInstance() {
        return new ClassDescriptor();
    }

    @Override
    public Map<RelationType, Set<? extends AbstractDescriptor>> getRelations(ClassDescriptor descriptor) {
        Map<RelationType, Set<? extends AbstractDescriptor>> relations = new HashMap<RelationType, Set<? extends AbstractDescriptor>>();
        relations.put(RelationType.ANNOTATED_BY, descriptor.getAnnotatedBy());
        relations.put(RelationType.CONTAINS, descriptor.getContains());
        relations.put(RelationType.DEPENDS_ON, descriptor.getDependencies());
        relations.put(RelationType.IMPLEMENTS, descriptor.getInterfaces());
        relations.put(RelationType.EXTENDS, asSet(descriptor.getSuperClass()));
        return relations;
    }

    @Override
    protected void setRelation(ClassDescriptor descriptor, RelationType relation, AbstractDescriptor target) {
        switch (relation) {
            case ANNOTATED_BY:
                descriptor.getAnnotatedBy().add((ClassDescriptor) target);
                break;
            case CONTAINS:
                descriptor.getContains().add(target);
                break;
            case DEPENDS_ON:
                descriptor.getDependencies().add((ClassDescriptor) target);
                break;
            case IMPLEMENTS:
                descriptor.getInterfaces().add((ClassDescriptor) target);
                break;
            case EXTENDS:
                descriptor.setSuperClass((ClassDescriptor) target);
                break;
            default:
                throw new IllegalArgumentException("Unsupported relation type " + relation);
        }
    }

}
