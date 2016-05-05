/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */


package net.srussell.framework.assessment;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite( "net.srussell.framework.assessment" );
        // $JUnit-BEGIN$
        suite.addTestSuite( AssessmentTest.class );
        
        // The next two tests require configuration in the EmailDeliveryTest class.
        // They're not really typical JUnit test but actually attempt to send email 
        // that can be manually verified. In order for it to work there needs to be configuration 
        // done. Once configured these tests can be uncommented.
        suite.addTestSuite( EmailDeliveryTest.class );
        suite.addTestSuite( ExceptionAssessmentTest.class );
        
        suite.addTestSuite( ExceptionAssessmentTest.class );
        // $JUnit-END$
        return suite;
    }
}
