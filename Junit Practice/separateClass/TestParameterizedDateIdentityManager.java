package com.pingidentity.mtfuji.tests;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.pingidentity.mtfuji.core.IdentityManager;

@RunWith(Parameterized.class)
public class TestParameterizedDateIdentityManager {
	private IdentityManager identityManager;
    private String TfirstName;
    private String TlastName;
    private String TbirthDate;
    
    public TestParameterizedDateIdentityManager (String firstName, String lastName, String birthDate){
    	
    	TfirstName = firstName;
    	TlastName = lastName;
    	TbirthDate = birthDate;
    }
    
    @Parameters (name = "Input {index} : {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 {"DifferentDataFormat", "Dalinski", "2014.12.5"},
                 {"InvalidDateNumber -25/110000/1429980", "Dalinski", "-25/110000/1429980"},
                 {"DeciPointInMonth", "Dalinski", "-25.5/110000/1429980"},
                 {"DeciPointInDay", "Dalinski", "-25/11.5/142"},
                 {"AllZerosInDate", "Dalinski", "0/0/0"},
                 {"EmptyDay", "Dalinski", "/5/2014"},
                 {"EmptyMonth", "Dalinski", "12//2014"},
                 {"EmptyYear", "Dalinski", "12/5/"},
                 {"DeciPointInYear", "Dalinski", "-25/110000/14.2"},
                 {"Buster", "Dalinski", "12/five/eleven"},
           });
    }
    
    @Before
    public void setUpTest() throws Exception
    {
        // Executed before each test
        identityManager = IdentityManager.getInstance();
    }
    @Test (expected = ParseException.class)
    public void useInvalidValueAsDateInput() throws Exception 
    {
        Long returnId = identityManager.createIdentity(TfirstName, TlastName, TbirthDate);
        assert(returnId != -1);
    }
    

}