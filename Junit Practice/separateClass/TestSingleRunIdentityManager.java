package com.pingidentity.mtfuji.tests;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pingidentity.mtfuji.core.IdentityManager;

public class TestSingleRunIdentityManager {
	
	private IdentityManager identityManager;

	@BeforeClass
    public static void setUpClass() throws Exception
    {
        // Executed before any tests are run
    }

    @Before
    public void setUpTest() throws Exception
    {
        // Executed before each test
        identityManager = IdentityManager.getInstance();
    }


    @Test 
    public void getIdentityReturnsCorrectIdentityObject() throws Exception 
    {
    	Long returnId = identityManager.createIdentity("Buster", "Dalinski", "12/11/2014");
    	assertEquals(returnId,identityManager.getIdentity(returnId).getUserId() );
    }
    
    @Test
    public void getAllIdentityReturnsAll() throws Exception 
    {	
    	identityManager.createIdentity("Buster", "Dalinski", "12/11/2014");
    	identityManager.createIdentity("Ruster", "Halinski", "12/11/2016");
        assert(identityManager.getAllIdentities().size() == 2);
    }
    
    @Test
    public void removeValidIdentity() throws Exception 
    {
        Long returnId = identityManager.createIdentity("Buster", "Dalinski", "12/11/2014");
        String statusMessage = identityManager.removeIdentity(returnId);
        assertEquals(statusMessage, "Identity successfully removed.");
    }
    
    @Test
    public void removeNonExistIdentityIsInvalid() throws Exception 
    {
        Long returnId = identityManager.createIdentity("Buster", "Dalinski", "12/11/2014");
        String statusMessage = identityManager.removeIdentity(returnId+1);
        assertEquals(statusMessage, "Could not remove Identity. The identity does not exist.");
    }
    
    @Test
    public void removeAllValidIdentityByFirstNameIsCaseSensetive() throws Exception 
    {
        identityManager.createIdentity("Buster", "Dalinski", "12/11/2014");
        identityManager.createIdentity("buster", "Dalinski", "12/11/2014");
        identityManager.createIdentity("buster", "Dalinski", "12/11/2014");
        identityManager.createIdentity("nuster", "Dalinski", "12/11/2014");        
        identityManager.removeAllIdentitiesByFirstName("buster");
        int remainSize = identityManager.getAllIdentities().size();
        assertEquals(remainSize, 2);
    }
    
    @Test
    public void removeIdentityFailsShouldNotReturnSuccessMessage() throws Exception 
    {
        identityManager.createIdentity("Buster", "Dalinski", "12/11/2014");
        String statusMessage = identityManager.removeAllIdentitiesByFirstName("cluster");
        assert(!statusMessage.equals("All Identities with First Name of cluster are successfully removed."));
    }
    
    @Test
    public void removeAllIdentitiesRemovesAll() throws Exception 
    {	
        identityManager.createIdentity("1", "Dalinski", "12/11/2014");
        identityManager.createIdentity("2", "Dalinski", "12/11/2014");
        identityManager.createIdentity("3", "Dalinski", "12/11/2014");
        identityManager.createIdentity("4", "Dalinski", "12/11/2014");  
        identityManager.removeAllIdentities();
        int remainSize = identityManager.getAllIdentities().size();
        assertEquals(remainSize,0);
    }
    
    @Test
    public void updateIdentityFailsDisplaysCorrectMessage() throws Exception
    {	Long returnId = identityManager.createIdentity("Buster", "Dalinski", "12/11/2014");
    	String statusMessage = identityManager.updateIdentity(returnId+1, "Wuster", "Walinski", "12/11/2013");
    	assertEquals(statusMessage, "Could not update Identity. The identity does not exist.");
    }
    
    @Test (expected = ParseException.class)
    public void updateWithWrongDateFormatIsNotAllowed() throws Exception
    {	Long returnId = identityManager.createIdentity("Buster", "Dalinski", "12/11/2014");
    	String statusMessage = identityManager.updateIdentity(returnId,"Buster", "Dalinski", "12/eleven/2014");
    	assert(statusMessage == "Identity successfully updated.");
    }
    
    @After
    public void cleanUp()
    {   
        // Executed after each test
    	identityManager.removeAllIdentities();
    }
}
