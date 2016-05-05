/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.io;

import junit.framework.TestCase;
import net.srussell.framework.logging.LogMgr;

/*
 * Created by: scott on May 19, 2014 2:13:50 PM for framework::net.srussell.framework.utility.io
 */

/**
 * TODO CODE REVIEW FAILURE! -- must add description here
 *
 *    @author scott
 */
public class FileAndDirectoryListingTest extends TestCase
{
    /**
     * path to directory to use for JUnit testing
     */
    private static final String JUNIT_TEST_PATH = "./src/net/srussell/framework/utility/io/junit/subdirectory";

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.io.FileAndDirectoryListingTest" );

    FileAndDirectoryListing fdl = null;



    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        fdl = new FileAndDirectoryListing( JUNIT_TEST_PATH );
    }



    /* test contains */
    public void testContains()
    {
        fdl.dumpListing();
        assertTrue( "expected directory missing", fdl.contains( "subsubdirectory" ) );
        assertTrue( "expected file missing", fdl.contains( "junitTest.file" ) );
    }

}
