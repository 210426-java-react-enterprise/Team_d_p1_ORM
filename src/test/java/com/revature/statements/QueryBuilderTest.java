package com.revature.statements; 

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Table;
import com.revature.exception.ImproperConfigurationException;
import com.revature.repos.Repo;
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

    @Table(name = "users")
    @Entity
    protected static class TestClass{
        @Column(columnName = "user_id")
        public int userID = 2;
        @Column
        public String username = "JackSparrow";
        @Column
        public String password = "password!";
    }

    TestClass testClass;

    @InjectMocks
    QueryBuilder sut;

    @Mock
    Repo mockRepo;

    @Mock
    ResultSet rs;

    @Before
    public void before() throws Exception {
        ConnectionFactory.setConnection("task-force.c2iiztx3t7wq.us-east-1.rds.amazonaws.com","postgres","revature","test");
        testClass = new TestClass();
        openMocks(this);
    }

    @After
    public void after(){
        sut = null;
        rs = null;
        testClass = null;
        mockRepo = null;
    }

    @Test
    public void testUpdateStatementIntegration() throws SQLException, ImproperConfigurationException {
        when(rs.next()).thenReturn(true);
        when(mockRepo.queryExecute(any())).thenReturn(rs);
        when(mockRepo.statementExecute(any())).thenReturn(rs);
        ResultSet rs = sut.buildStatement(testClass,"user_id");
        rs.next();
        System.out.println(rs.getString("username"));
    }
    @Test
    public void testUpdateStatementIntegrationWithMultipleFields() throws SQLException, ImproperConfigurationException {
        when(rs.next()).thenReturn(true);
        when(mockRepo.queryExecute(any())).thenReturn(rs);
        when(mockRepo.statementExecute(any())).thenReturn(rs);
        sut.buildStatement(testClass,"password","username");
    }


} 
