package com.revature.configurations; 

import com.revature.annotations.Column;
import com.revature.types.ColumnFieldType;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* TableConfig Tester. 
* 
* @author <Authors name> 
* @since <pre>May 21, 2021</pre> 
* @version 1.0 
*/ 
public class TableConfigTest {
    public TableConfig sut;
    public TestClass testClass;


protected static class TestClass{
    @Column(columnName = "Test Column 1",notNull = true)
    private int testInt = 18;
    @Column(columnName = "Test Column 2")
    private String testString = "test me";
    @Column
    private int testInteger = 12;
    @Column
    private long testLong = 3478L;
}
@Before
public void before() throws Exception {
    testClass = new TestClass();
    sut = new TableConfig(testClass);

} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getDataClass() 
* 
*/ 
@Test
public void testGetDataClass() throws Exception { 
    for(ColumnFieldType type:sut.getFieldTypes()){
        System.out.println(type);
    }
} 

/** 
* 
* Method: setDataClass(Class<T> dataClass) 
* 
*/ 
@Test
public void testSetDataClass() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getTableName() 
* 
*/ 
@Test
public void testGetTableName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setTableName(String tableName) 
* 
*/ 
@Test
public void testSetTableName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getFieldConfigs() 
* 
*/ 
@Test
public void testGetFieldConfigs() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setFieldConfigs(List<ColumnFieldConfig> fieldConfigs) 
* 
*/ 
@Test
public void testSetFieldConfigs() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getFieldTypes() 
* 
*/ 
@Test
public void testGetFieldTypes() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setFieldTypes(ColumnFieldType[] fieldTypes) 
* 
*/ 
@Test
public void testSetFieldTypes() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: fromClass(Class<T> clazz) 
* 
*/ 
@Test
public void testFromClass() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: extractTableName(Class<T> clazz) 
* 
*/ 
@Test
public void testExtractTableName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: extractFieldTypes(Class<T> clazz) 
* 
*/ 
@Test
public void testExtractFieldTypes() throws Exception { 
//TODO: Test goes here... 
} 


} 
