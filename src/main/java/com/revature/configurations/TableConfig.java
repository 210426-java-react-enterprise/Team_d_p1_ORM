/*
 * File: TableConfig
 * Team_d_p1_ORM
 * Date created: 5/14/21, 7:45 PM
 * Last Modified: 5/14/21, 7:45 PM
 * Created by: Nicholas Recino
 */
package com.revature.configurations;

import com.revature.exception.ImproperConfigurationException;
import com.revature.services.ExtractionService;
import com.revature.types.ColumnFieldType;

import java.util.List;

public class TableConfig {

    private Class<?> dataClass;
    private String tableName;
    private List<ColumnFieldType> fieldTypes;


    /**
     * Setup a table config associated with the dataClass and field configurations. The table-name will be extracted
     * from the dataClass of the persisted object.
     * @param objectToBePersisted The object that is going to persisted into the database.
     */

    public TableConfig(Object objectToBePersisted) throws ImproperConfigurationException {
        this.dataClass = objectToBePersisted.getClass();
        this.tableName = ExtractionService.extractTableName(dataClass);
        extractData(objectToBePersisted);
    }
    /**
     * Setup a table config associated with the dataClass and field configurations. The table-name will be extracted
     * from the dataClass of the persisted object.
     * @param objectToBePersisted The object that is going to persisted into the database.
     */

    public TableConfig(Object objectToBePersisted,boolean insertOption) throws ImproperConfigurationException {
        this.dataClass = objectToBePersisted.getClass();
        this.tableName = ExtractionService.extractTableName(dataClass);
        extractData(objectToBePersisted,insertOption);
    }

    /**
     * Setup a table config associated with the dataClass, table-name, and field configurations.
     * @param dataClass Data class to model this table after
     * @param tableName Name of the table as this is used if only entity annotation is utilized.
     */
    public TableConfig(Class<?> dataClass, String tableName) {
        this.dataClass = dataClass;
        this.tableName = tableName;
    }

    /**
     *
     * @return the data class this table config is modeled after
     */
    public Class<?> getDataClass() {
        return dataClass;
    }

    /**
     *
     * @param dataClass Sets the data class that this table config is modeled after
     */
    public void setDataClass(Class<?> dataClass) {
        this.dataClass = dataClass;
    }

    /**
     *
     * @return the table name of this model configuration
     */
    public String getTableName() {
        return tableName;
    }

    /**
     *
     * @param tableName Sets the table name of this model configuration
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     *
     * @return a list of field types that represent the state of the columns in the table
     */
    public List<ColumnFieldType> getFieldTypes() {
        return fieldTypes;
    }

    /**
     *
     * @return a list of field types that represent the state of the columns in the table
     */
    public List<String> getAllFieldNames() {
        return ExtractionService.extractAllFieldNames(dataClass);
    }

    /**
     *
     * @param fieldTypes Sets the list of field types that represent the state of the columns in the table
     */
    public void setFieldTypes(List<ColumnFieldType> fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

    /**
     *  Extract the TableConfig for a particular class by looking for class and field annotations. This is used
     *  internally by other classes to configure a class.
     * @param clazz Class that the Table Config will be modeled after
     * @return an instance of the said  table configuration from the class
     */
    public static TableConfig fromClass(Class<?> clazz) {
        String tableName = ExtractionService.extractTableName(clazz);
        return new TableConfig(clazz, tableName);
    }

    /**
     * Extracts the data from an object and places the data in this configuration
     * @param objectToPersist  Object to have data extracted from, passes tableName to place into the fieldTypes
     * @throws ImproperConfigurationException when the shape of the table config doesnt match the data present in the objectToPersist
     */
    public void extractData(Object objectToPersist) throws ImproperConfigurationException {
        this.fieldTypes = ExtractionService.extractData(objectToPersist,tableName);
    }

    /**
     * Extracts the data from an object and places the data in this configuration
     * @param objectToPersist  Object to have data extracted from, passes tableName to place into the fieldTypes
     * @throws ImproperConfigurationException when the shape of the table config doesnt match the data present in the objectToPersist
     */
    public void extractData(Object objectToPersist,boolean insertOption) throws ImproperConfigurationException {
        this.fieldTypes = ExtractionService.extractData(objectToPersist,tableName, insertOption);
    }

    /**
     *
     * @return String representation of a TableConfig
     */
    @Override
    public String toString() {
        return "TableConfig{" +
                "dataClass=" + dataClass +
                ", tableName='" + tableName + '\'' +
                ", fieldTypes=" + fieldTypes +
                '}';
    }
}
