package com.revature.statements; 

import com.revature.annotations.Column;
import com.revature.exception.ImproperConfigurationException;
import com.revature.repos.Repo;
import com.revature.types.ColumnFieldType;
import com.revature.types.DataType;
import com.revature.util.datasource.ConnectionFactory;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/** 
* InsertBuilder Tester. 
* 
* @author <Authors name> 
* @since <pre>May 18, 2021</pre> 
* @version 1.0 
*/


public class InsertBuilderTest {

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

    public TestClass testClass;




    @InjectMocks
    InsertBuilder sut;

    @Mock
    Repo mockRepo;

    @Mock
    ResultSet rs;

    @Before
    public void before() throws Exception {
        testClass = new TestClass();
        ConnectionFactory.setConnection("bankoffsm.c2iiztx3t7wq.us-east-1.rds.amazonaws.com","postgres","revature","public");
        openMocks(this);
    }

@After
public void after() throws Exception {
    sut = null;
    mockRepo = null;
    rs = null;
} 

/** 
* 
* Method: buildInsertStatement(ColumnFieldType[] fieldsData, String tableName) 
* 
*/ 
@Test
public void testBuildInsertStatement() {
    ColumnFieldType fieldData = new ColumnFieldType();
    fieldData.setColumnName("test_column");
    fieldData.setDefaultValue("String1");
    fieldData.setDataType(DataType.STRING);

    ColumnFieldType fieldData2 = new ColumnFieldType();
    fieldData2.setColumnName("test_column2");
    fieldData2.setDefaultValue("String2");
    fieldData2.setDataType(DataType.STRING);

    ColumnFieldType fieldData3 = new ColumnFieldType();
    fieldData3.setColumnName("test_column3");
    fieldData3.setDefaultValue("String3");
    fieldData3.setDataType(DataType.STRING);


    ColumnFieldType[] fieldsData = new ColumnFieldType[]{fieldData,fieldData2,fieldData3};
    String tableName = "test_table";


    try {
        when(rs.next()).thenReturn(true);

        when(mockRepo.queryExecute(any())).thenReturn(rs);
        sut.buildInsertStatement(fieldsData,tableName);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
}

    @Test
    public void testBuildStatementIntegration() throws SQLException, ImproperConfigurationException {
        sut.buildStatement(testClass);
    }


} 
