package com.revature.statements; 

import com.revature.repos.Repo;
import com.revature.types.ColumnFieldType;
import com.revature.types.DataType;
import com.revature.util.datasource.ConnectionFactory;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

/** 
* QueryBuilder Tester. 
* 
* @author <Authors name> 
* @since <pre>May 18, 2021</pre> 
* @version 1.0 
*/ 
public class QueryBuilderTest {

    @InjectMocks
    QueryBuilder sut;

    @Mock
    Repo mockRepo;

    @Mock
    ResultSet rs;

    @Before
    public void before() throws Exception {
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
* Method: buildSelectStatement(ColumnFieldType conditionFieldName) 
* 
*/ 
@Test
public void testBuildSelectStatement() throws Exception {
    ColumnFieldType fieldData = new ColumnFieldType();
    fieldData.setTableName("test_table");
    fieldData.setColumnName("test_column");
    fieldData.setDefaultValue("delete");
    fieldData.setDataType(DataType.STRING);


    try {
        when(rs.next()).thenReturn(true);

        when(mockRepo.queryExecute(any())).thenReturn(rs);
        sut.buildSelectStatement(fieldData);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
}


} 
