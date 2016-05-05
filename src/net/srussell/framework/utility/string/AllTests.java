/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.string;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.srussell.framework.logging.LogMgr;

/**
 */
public class AllTests
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.string.AllTests" );



    public static Test suite()
    {
        TestSuite suite = new TestSuite( "Test for net.srussell.framework.utility.string" );
        //$JUnit-BEGIN$
        suite.addTestSuite( ConversionTest.class );   // TODO uncomment when tests are written
        //$JUnit-END$
        return suite;
    }
}
