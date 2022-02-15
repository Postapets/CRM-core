package ru.sfedu.crm.lab1.api;

import java.util.List;

public interface IMetadataProvider {
    /**
     * Returns all schemas
     * @return list
     */
    List getSchemas();

    /**
     * Returns all users
     * @return list
     */
    List getUsers();

    /**
     * Returns all tables
     * @return list
     */
    List getTables();

    /**
     * Returns all columns
     * @return list
     */
    List getColumns();



}
