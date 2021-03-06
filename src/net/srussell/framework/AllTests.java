/**
 * (c) Copyright  Scott M. Russell.  2010, 2016
 *     All rights reserved.
 */


package net.srussell.framework;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.srussell.framework.logging.LogMgr;

public class AllTests
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.AllTests" );



    public static Test suite()
    {
        TestSuite suite = new TestSuite( "Test for net.srussell.framework" );
        //$JUnit-BEGIN$
        suite.addTest( net.srussell.framework.assessment.AllTests.suite() );
        suite.addTest( net.srussell.framework.exception.handler.AllTests.suite() );
        suite.addTest( net.srussell.framework.logging.AllTests.suite() );
        suite.addTest( net.srussell.framework.utility.io.AllTests.suite() );
        suite.addTest( net.srussell.framework.utility.string.AllTests.suite() );
        suite.addTest( net.srussell.framework.utility.string.vcs.AllTests.suite() );
        suite.addTest( net.srussell.framework.utility.collection.AllTests.suite() );
        //$JUnit-END$
        return suite;
    }
}
