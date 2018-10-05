package com.github.bohnman.squiggly.expandable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.Annotations;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ExpandableProperty extends VirtualBeanPropertyWriter {

  public ExpandableProperty() {
  }

  public ExpandableProperty(BeanPropertyDefinition propDef, Annotations contextAnnotations,
                            JavaType declaredType) {
    super(propDef, contextAnnotations, declaredType);
  }

  @Override
  protected Object value(Object bean, JsonGenerator jgen, SerializerProvider prov)
      throws Exception {
    return findExpandables(bean.getClass());
  }

  @Override
  public VirtualBeanPropertyWriter withConfig(MapperConfig<?> config, AnnotatedClass declaringClass,
      BeanPropertyDefinition propDef, JavaType type) {
    return new ExpandableProperty(propDef, null, type);
  }

  private Set<String> findExpandables(Class<?> target) {
    Set<String> expandables = new HashSet<>();

    for (Field field : target.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.isAnnotationPresent(Expandable.class)) {
        expandables.add(field.getName());
      }
    }

    return expandables;
  }
}
