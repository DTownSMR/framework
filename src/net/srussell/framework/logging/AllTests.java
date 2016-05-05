/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.logging;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.srussell.framework.logging.LogMgr;

/**
 */
public class AllTests
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.logging.AllTests" );



    public static Test suite()
    {
        TestSuite suite = new TestSuite( AllTests.class.getName() );
        //$JUnit-BEGIN$
        suite.addTestSuite( LogMgrTest.class );
        suite.addTestSuite( RevisionFormatterTest.class );
        suite.addTestSuite( StandardFormatterTest.class );
        //$JUnit-END$
        return suite;
    }
}

