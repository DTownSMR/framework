/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.string.vcs;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.srussell.framework.logging.LogMgr;
import net.srussell.framework.utility.string.vcs.subversion.SvnRevisionNumberTest;

/**
 */
public class AllTests
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.string.vcs" );



    public static Test suite()
    {
        TestSuite suite = new TestSuite( "Test for net.srussell.framework.utility.string.vcs and specific implementations" );
        //$JUnit-BEGIN$
        suite.addTestSuite( VcsRevisionNumberTest.class );
        suite.addTest( net.srussell.framework.utility.string.vcs.subversion.AllTests.suite() );
        //$JUnit-END$
        return suite;
    }
}
