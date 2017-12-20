package com.pingidentity.mtfuji.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.pingidentity.mtfuji.core.IdentityManager;


@RunWith(Parameterized.class)
public class TestParameterizedIdentityManager {
	private IdentityManager identityManager;
    private String TfirstName;
    private String TlastName;
    private String TbirthDate;
    
    public TestParameterizedIdentityManager (String firstName, String lastName, String birthDate){
    	
    	TfirstName = firstName;
    	TlastName = lastName;
    	TbirthDate = birthDate;
    }
    
    @Parameters (name = "Input {index} : {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { "NormalInput", "Dalinski", "12/11/2014"},
                 {"Space+ ", "Dalinski", "12/11/2014"},
                 {" +Space", "Dalinski", "12/11/2014"},
                 {"Space Space", "Dalinski", "12/11/2014"},
                 {"LastNamespace+", "space+ ", "12/11/2014"},
                 {"+spaceLastName", " +space", "12/11/2014"},
                 {"SpaceBetweenLastName", "space space", "12/11/2014"},
                 {"LastNamespace++", "space++  ", "12/11/2014"},
                 {"++spaceLastName", "  ++space", "12/11/2014"},
                 {"DoubleSpaceBetweenLastName", "double  space", "12/11/2014"},
                 {"Björk", "Präs", "12/11/2014"},
                 {"这是中文", "これは日本語です", "12/11/2014"},
                 {"", "", "12/11/2014"},
                 {"DifferentDataFormat", "Dalinski", "2014.12.5"},
                 {"InvalidDateNumber", "Dalinski", "-25/110000/1429980"},
                 {"DeciPointInMonth", "Dalinski", "-25.5/110000/1429980"},
                 {"DeciPointInDay", "Dalinski", "-25/11.5/142"},
                 {"AllZerosInDate", "Dalinski", "0/0/0"},
                 {"EmptyDay", "Dalinski", "/5/2014"},
                 {"EmptyMonth", "Dalinski", "12//2014"},
                 {"EmptyYear", "Dalinski", "12/5/"},
                 {"DeciPointInYear", "Dalinski", "-25/110000/14.2"},

           });
    }
    
    @Before
    public void setUpTest() throws Exception
    {
        // Executed before each test
        identityManager = IdentityManager.getInstance();
    }
    @Test
    public void createValidIdentity() throws Exception 
    {
        Long returnId = identityManager.createIdentity(TfirstName, TlastName, TbirthDate);
        assert(returnId != -1);
    }
    
    @Test
    public void createdBirthdateShouldEqualToInputDate() throws Exception
    {	Long returnId = identityManager.createIdentity(TfirstName, TlastName, TbirthDate);
    	String storedDate = identityManager.getIdentity(returnId).getBirthDate();
    	assert(TbirthDate.equals(storedDate));
    }
    
    @Test
    public void updateValidIdentity() throws Exception
    {	Long returnId = identityManager.createIdentity("Buster", "Dalinski", "12/11/2014");
    	String statusMessage = identityManager.updateIdentity(returnId, TfirstName, TlastName, TbirthDate);
    	assertEquals(statusMessage, "Identity successfully updated.");
    }
}
