package com.revature.statements; 

import com.revature.repos.Repo;
import com.revature.types.ColumnFieldType;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;

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

    @InjectMocks
    InsertBuilder sut;

    @Mock
    Repo mockRepo;

    @Mock
    ResultSet rs;

    @Before
    public void before() throws Exception {
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
public void testBuildInsertStatement() throws Exception {
    ColumnFieldType[] fieldsData = new ColumnFieldType[3];
    String tableName = "test_table";

    when(rs.next()).thenReturn(true);

    when(mockRepo.queryExecute(any())).thenReturn(rs);
    sut.buildInsertStatement(fieldsData,tableName);

} 


} 
