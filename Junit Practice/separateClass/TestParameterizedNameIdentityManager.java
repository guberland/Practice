package com.pingidentity.mtfuji.tests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.pingidentity.mtfuji.core.IdentityManager;

@RunWith(Parameterized.class)
public class TestParameterizedNameIdentityManager {
	private IdentityManager identityManager;
    private String TfirstName;
    private String TlastName;
    private String TbirthDate;
    
    public TestParameterizedNameIdentityManager (String firstName, String lastName, String birthDate){
    	
    	TfirstName = firstName;
    	TlastName = lastName;
    	TbirthDate = birthDate;
    }
    
    @Parameters (name = "Input {index} : {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 
                 {"Space+ ", "Dalinski", "12/11/2014"},
                 {" +Space", "Dalinski", "12/11/2014"},
                 {"Space Space", "Dalinski", "12/11/2014"},
                 {"LastNamespace+", "space+ ", "12/11/2014"},
                 {"+spaceLastName", " +space", "12/11/2014"},
                 {"SpaceBetweenLastName", "space space", "12/11/2014"},
                 {"LastNamespace++", "space++  ", "12/11/2014"},
                 {"++spaceLastName", "  ++space", "12/11/2014"},
                 {"DoubleSpaceBetweenLastName", "double  space", "12/11/2014"},

           });
    }
    
    @Before
    public void setUpTest() throws Exception
    {
        // Executed before each test
        identityManager = IdentityManager.getInstance();
    }
    @Test
    public void useSpacesInNameReturnNegetiveOne() throws Exception 
    {
        Long returnId = identityManager.createIdentity(TfirstName, TlastName, TbirthDate);
        assert(returnId == -1);
    }
    

}

