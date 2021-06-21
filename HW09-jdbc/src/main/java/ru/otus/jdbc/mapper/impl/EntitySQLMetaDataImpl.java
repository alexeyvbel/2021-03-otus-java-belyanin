package ru.otus.jdbc.mapper.impl;

import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.EntitySQLMetaData;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData<T> {

    private final StringBuilder selectSqlAll;
    private final StringBuilder selectSql;
    private final StringBuilder insertSql;
    private final StringBuilder updateSql;

    private final EntityClassMetaData<T> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
        selectSqlAll = new StringBuilder().append("select * from ")
                .append(entityClassMetaData.getName());
        selectSql = selectSqlAll
                .append(" where ")
                .append(entityClassMetaData.getIdField().getName())
                .append(" = ?");
        insertSql = new StringBuilder()
                .append("insert into ")
                .append(entityClassMetaData.getName())
                .append(" ")
                .append(entityClassMetaData.getFieldsWithoutId().stream()
                        .map(Field::getName)
                        .collect(Collectors.joining(",","(",") values ")))
                .append(Collections.nCopies(entityClassMetaData.getFieldsWithoutId().size(), "?").stream()
                        .collect(Collectors.joining(",", "(", ")")));
        updateSql = new StringBuilder()
                .append("update ")
                .append(entityClassMetaData.getName())
                .append(" ")
                .append(entityClassMetaData.getFieldsWithoutId().stream()
                        .map(f -> " set " + f.getName() + " = ?")
                        .collect(Collectors.joining(",")));

    }

    @Override
    public String getSelectAllSql() {
        return selectSqlAll.toString();
    }

    @Override
    public String getSelectByIdSql() {
        return selectSql.toString();
    }

    @Override
    public String getInsertSql() {
        return insertSql.toString();
    }

    @Override
    public String getUpdateSql() {
        return updateSql.toString();
    }

}
