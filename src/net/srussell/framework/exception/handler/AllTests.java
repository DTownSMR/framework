/**
 * (c) Copyright Scott M. Russell 2016
 */
package net.srussell.framework.exception.handler;
/*
 * Created by: Scott Russell on Jul 28, 2016 3:50:04 PM for GIT-dtownsmr-framework::net.srussell.framework.exception.handler
 */

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite( AllTests.class.getName() );
        //$JUnit-BEGIN$
        suite.addTestSuite( DefaultHandlerTest.class );
        suite.addTestSuite( HandlersTest.class );
        suite.addTestSuite( HandlersWrapperTest.class );
        //$JUnit-END$
        return suite;
    }

}

