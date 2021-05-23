package com.revature.statements; 

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Table;
import com.revature.exception.ImproperConfigurationException;
import com.revature.repos.Repo;
import com.revature.types.ColumnFieldType;
import com.revature.types.DataType;
import com.revature.util.ORMState;
import com.revature.util.datasource.ConnectionFactory;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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

    public TestClass testClass;

    QueryBuilder sut;

    Repo properRepo;

    @Mock
    ResultSet rs;

    @Before
    public void before() throws Exception {
        ConnectionFactory.setConnection("bankoffsm.c2iiztx3t7wq.us-east-1.rds.amazonaws.com","postgres","revature","public");
        properRepo = new Repo(ConnectionFactory.getInstance().getConnection());
        sut = new QueryBuilder(properRepo);
        testClass = new TestClass();

        ORMState state = ORMState.getInstance();
    }

    @After
    public void after() throws Exception {
        sut = null;
        rs = null;
        testClass = null;
    }

    @Test
    public void testUpdateStatementIntegration() throws SQLException, ImproperConfigurationException {
        ResultSet rs = sut.buildStatement(testClass,"user_id");
        rs.next();
        System.out.println(rs.getString("username"));
    }
    @Test
    public void testUpdateStatementIntegrationWithMultipleFields() throws SQLException, ImproperConfigurationException {
        sut.buildStatement(testClass,"password","username");
    }


} 
