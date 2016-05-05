/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.string.vcs.subversion;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.srussell.framework.logging.LogMgr;

/**
 */
public class AllTests
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.string.subversion.AllTests" );



    public static Test suite()
    {
        TestSuite suite = new TestSuite( "Test for net.srussell.framework.utility.string.subversion" );
        //$JUnit-BEGIN$
        suite.addTestSuite( SvnRevisionNumberTest.class );
        //$JUnit-END$
        return suite;
    }
}

