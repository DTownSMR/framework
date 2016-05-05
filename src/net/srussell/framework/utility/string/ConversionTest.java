/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.string;

import junit.framework.TestCase;
import net.srussell.framework.logging.LogMgr;

/**
 */
public class ConversionTest extends TestCase
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.string.ConversionTest" );



    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }



    /**
     * Test method for {@link net.srussell.framework.utility.string.Conversion#toHexString(byte[])}.
     */
    public void testToHexStringByteArray()
    {
        byte[] byteArray = {'a', 'b', 'c'};
        String result = Conversion.toHexString( byteArray );
        assertEquals("byte array conversion failed!", "61 62 63", result);
    }



    /**
     * Test method for {@link net.srussell.framework.utility.string.Conversion#toHexString(int)}.
     */
    public void testToHexStringInt()
    {
        String result = Conversion.toHexString( 57 );
        assertEquals("byte array conversion failed!", "39", result);
    }
}

