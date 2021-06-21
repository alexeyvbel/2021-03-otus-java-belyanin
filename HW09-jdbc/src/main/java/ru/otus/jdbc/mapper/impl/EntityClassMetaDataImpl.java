package ru.otus.jdbc.mapper.impl;

import ru.otus.crm.annotations.Id;
import ru.otus.jdbc.mapper.EntityClassMetaData;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final String name;
    private final Constructor<T> constructor;
    private final Field field;
    private final List<Field> fields;
    private final List<Field> fieldsWithoutId;

    public EntityClassMetaDataImpl(Class<T> aClass) throws NoSuchMethodException {
        this.name = aClass.getSimpleName().toLowerCase();
        this.constructor = aClass.getDeclaredConstructor();
        this.field = Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow();
        this.fields = List.of(aClass.getDeclaredFields());
        this.fieldsWithoutId = Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return field;
    }

    @Override
    public List<Field> getAllFields() {
        return fields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return fieldsWithoutId;
    }
}
