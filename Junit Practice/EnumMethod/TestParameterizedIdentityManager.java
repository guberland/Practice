package com.pingidentity.mtfuji.tests;

import static org.junit.Assert.*;

import java.awt.Window.Type;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assume;
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
    private Type Ttype;

    public TestParameterizedIdentityManager (Type type, String firstName, String lastName, String birthDate){
    	Ttype = type;
    	TfirstName = firstName;
    	TlastName = lastName;
    	TbirthDate = birthDate;
    }
    enum Type {VALIDINPUT, INVALIDNAME,INVALIDDATE};
    @Parameters (name = "Input {index} : {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 {Type.VALIDINPUT, "NormalInput", "Dalinski", "12/11/2014"},
                 {Type.VALIDINPUT, "Björk", "Präs", "12/11/2014"},
                 {Type.VALIDINPUT, "这是中文", "これは日本語です", "12/11/2014"},
                 {Type.VALIDINPUT, "", "", "12/11/2014"},
                 {Type.INVALIDNAME, "Space+ ", "Dalinski", "12/11/2014"},
                 {Type.INVALIDNAME, " +Space", "Dalinski", "12/11/2014"},
                 {Type.INVALIDNAME, "Space Space", "Dalinski", "12/11/2014"},
                 {Type.INVALIDNAME, "LastNamespace+", "space+ ", "12/11/2014"},
                 {Type.INVALIDNAME, "+spaceLastName", " +space", "12/11/2014"},
                 {Type.INVALIDNAME, "SpaceBetweenLastName", "space space", "12/11/2014"},
                 {Type.INVALIDNAME, "LastNamespace++", "space++  ", "12/11/2014"},
                 {Type.INVALIDNAME, "++spaceLastName", "  ++space", "12/11/2014"},
                 {Type.INVALIDNAME, "DoubleSpaceBetweenLastName", "double  space", "12/11/2014"},
                 {Type.INVALIDDATE, "DifferentDataFormat", "Dalinski", "2014.12.5"},
                 {Type.INVALIDDATE, "InvalidDateNumber", "Dalinski", "-25/110000/1429980"},
                 {Type.INVALIDDATE, "DeciPointInMonth", "Dalinski", "-25.5/110000/1429980"},
                 {Type.INVALIDDATE, "DeciPointInDay", "Dalinski", "-25/11.5/142"},
                 {Type.INVALIDDATE, "AllZerosInDate", "Dalinski", "0/0/0"},
                 {Type.INVALIDDATE, "EmptyDay", "Dalinski", "/5/2014"},
                 {Type.INVALIDDATE, "EmptyMonth", "Dalinski", "12//2014"},
                 {Type.INVALIDDATE, "EmptyYear", "Dalinski", "12/5/"},
                 {Type.INVALIDDATE, "DeciPointInYear", "Dalinski", "-25/110000/14.2"},

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
    {   Assume.assumeTrue(Ttype == Type.VALIDINPUT);
        Long returnId = identityManager.createIdentity(TfirstName, TlastName, TbirthDate);
        assert(returnId != -1);
    }
    
    @Test
    public void createIdentityWithSpaceInNameReturnsNegetiveOne() throws Exception 
    {   Assume.assumeTrue(Ttype == Type.INVALIDNAME);
        Long returnId = identityManager.createIdentity(TfirstName, TlastName, TbirthDate);
        assert(returnId == -1);
    }
    
    @Test
    public void createdBirthdateShouldEqualToInputDate() throws Exception
    {	Assume.assumeTrue(Ttype == Type.VALIDINPUT);
    	Long returnId = identityManager.createIdentity(TfirstName, TlastName, TbirthDate);
    	String storedDate = identityManager.getIdentity(returnId).getBirthDate();
    	assert(TbirthDate.equals(storedDate));
    }
    
    @Test (expected = ParseException.class)
    public void createWithWrongDateFormatIsNotAllowed() throws Exception 
    {	
    	Assume.assumeTrue(Ttype == Type.INVALIDDATE);
    	Long returnId = identityManager.createIdentity(TfirstName, TlastName, TbirthDate);
    	assert(returnId != -1);
    }
    
    @Test
    public void updateValidIdentity() throws Exception
    {	Assume.assumeTrue(Ttype == Type.VALIDINPUT);
    	Long returnId = identityManager.createIdentity("Buster", "Dalinski", "12/11/2014");
    	String statusMessage = identityManager.updateIdentity(returnId, TfirstName, TlastName, TbirthDate);
    	assertEquals(statusMessage, "Identity successfully updated.");
    }
}

