/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.io;

import java.io.File;

import junit.framework.TestCase;
import net.srussell.framework.logging.LogMgr;

/**
 */
public class FileSystemListIteratorTest extends TestCase
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.io.FileSystemListIteratorTest" );
    /**
     * path to directory to use for JUnit testing
     */
    private static final String JUNIT_TEST_PATH = "./src/net/srussell/framework/utility/io/junit/subdirectory";

    FileSystemListIterator fsli = null;



    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        FileListing fl = new FileListing( JUNIT_TEST_PATH );
        fsli = fl.iterator();
    }



    /**
     * Test method for {@link net.srussell.framework.utility.io.FileSystemListIterator#hasNext()}.
     */
    public void testHasNext()
    {
        assertTrue( "should have next", fsli.hasNext() );
    }



    /**
     * Test method for {@link net.srussell.framework.utility.io.FileSystemListIterator#next()}.
     */
    public void testNext()
    {
        File next = fsli.next();
        assertTrue( "should exist", next != null );
        assertEquals( "wrong file received", "junitTest.file", next.getName() );
        assertTrue( "should be done", !fsli.hasNext() );
    }



    /**
     * Test method for {@link net.srussell.framework.utility.io.FileSystemListIterator#remove()}.
     */
    public void testRemove()
    {
        assertEquals( "should have something", 1, fsli.size() );
        fsli.next();
        fsli.remove();
        assertEquals( "shouldn't have anything left", 0, fsli.size() );

    }
}
