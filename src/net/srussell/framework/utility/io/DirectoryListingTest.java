/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.io;

import junit.framework.TestCase;
import net.srussell.framework.logging.LogMgr;

/**
 */
public class DirectoryListingTest extends TestCase
{

    /**
     * path to directory to use for JUnit testing
     */
    private static final String JUNIT_TEST_PATH = "./src/net/srussell/framework/utility/io/junit";

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.io.DirectoryListingTest" );

    DirectoryListing dl = null;



    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        dl = new DirectoryListing( JUNIT_TEST_PATH );
    }



    /* test contains */
    public void testContains()
    {
        assertTrue( "expected directory missing", dl.contains( "subdirectory" ) );
    }

}
