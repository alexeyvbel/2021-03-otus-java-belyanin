package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData<T> entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData<T> entitySQLMetaData, EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection,
                entitySQLMetaData.getSelectByIdSql(),
                List.of(id),
                rs -> {
                    try {
                        if (rs.next()) {
                            T entity = (T) entityClassMetaData.getConstructor().newInstance();
                            setFields(rs, entity, entityClassMetaData.getAllFields());
                            return entity;
                        }
                    } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new DataTemplateException(e);
                    }
                    return null;
                });
    }

    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelect(connection,
                entitySQLMetaData.getSelectAllSql(),
                Collections.emptyList(),
                rs -> {
                    List<T> tList = new ArrayList<>();
                    try {
                        while (rs.next()) {
                            T entity = (T) entityClassMetaData.getConstructor().newInstance();
                            setFields(rs, entity, entityClassMetaData.getAllFields());
                            tList.add(entity);
                        }
                        return tList;
                    } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new DataTemplateException(e);
                    }
                }).orElseThrow(() -> new RuntimeException("Unexpected error"));
    }

    @Override
    public long insert(Connection connection, T client) {
        try {
            return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(), getInsertParameters(client));
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    @Override
    public void update(Connection connection, T client) {
        try {
            dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(),  getUpdateParameters(client));
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    private void setFieldValue(T entity, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(entity, value);
        } catch (IllegalAccessException e) {
            throw new DataTemplateException(e);
        }
    }

    private void setFields(ResultSet rs, T entity, List<Field> fields) throws SQLException {
        for (Field field : fields) {
            setFieldValue(entity, field, rs.getObject(field.getName()));
        }
    }

    private List<Object> getInsertParameters(T client) {
        List<Object> parameterList = new ArrayList<>();

        entityClassMetaData.getFieldsWithoutId().stream().forEach(field -> {
            parameterList.add(getFieldValue(client, (Field) field));
        });
        return parameterList;
    }

    private Object getFieldValue(T entity, Field field) {
        try {
            field.setAccessible(true);
            return field.get(entity);
        } catch (IllegalAccessException e) {
            throw new DataTemplateException(e);
        }
    }

    private List<Object> getUpdateParameters(T client) {
        List<Object> parameterList = new ArrayList<>();
        entityClassMetaData.getFieldsWithoutId().stream().forEach(field -> {
            parameterList.add(getFieldValue(client, (Field) field));
        });
        parameterList.add(getFieldValue(client, entityClassMetaData.getIdField()));
        return parameterList;
    }
}
