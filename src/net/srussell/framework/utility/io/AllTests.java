/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.io;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.srussell.framework.logging.LogMgr;

/**
 */
public class AllTests
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.io.AllTests" );



    public static Test suite()
    {
        TestSuite suite = new TestSuite( AllTests.class.getName() );
        //$JUnit-BEGIN$
        suite.addTestSuite( FileListingTest.class );
        suite.addTestSuite( DirectoryListingTest.class );
        suite.addTestSuite( FileAndDirectoryListingTest.class );
        suite.addTestSuite( FileSystemListIteratorTest.class );
        //$JUnit-END$
        return suite;
    }
}
