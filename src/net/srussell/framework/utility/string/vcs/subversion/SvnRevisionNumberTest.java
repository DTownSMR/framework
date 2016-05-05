/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.string.vcs.subversion;

import junit.framework.TestCase;

/**
 * test the SvnRevisionNumber class
 */
public class SvnRevisionNumberTest extends TestCase
{

    /**
     * working revision number
     */
    private SvnRevisionNumber srn = null;



    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        srn = new SvnRevisionNumber( "$Rev: 01 $" );
    }



    /**
     * Test method for {@link net.srussell.framework.utility.string.vcs.subversion.SvnRevisionNumber#SvnRevisionNumber(java.lang.String)}.
     */
    public void testSvnRevisionNumberString()
    {
        // make sure a null value is handled correctly
        try
        {
            srn = new SvnRevisionNumber( null );
            fail( "exception wasn't thrown for null value" );
        }
        catch( IllegalArgumentException e )
        {
            // this is correct, null isn't valid for the constructor
        }

    }



    /**
     * Test method for {@link net.srussell.framework.utility.string.vcs.subversion.SvnRevisionNumber#getRevision()}.
     */
    public void testGetRevision()
    {
        assertEquals( "wrong value returned", "01", srn.getRevision() );
    }



    /**
     * Test method for {@link net.srussell.framework.utility.string.vcs.subversion.SvnRevisionNumber#parseSvnRevisionProperty(java.lang.String)}.
     */
    public void testParseSvnRevision()
    {
        // make sure invalid (re: non-parsable) values is handled correctly
        // assumed format is:   "$Rev: 46 $"
        //      where Rev can be either "Rev", "Revision", or "LastChangedRevision"  
        try
        {
            srn.parseSvnRevisionProperty( "will-not-work" );
            fail( "exception wasn't thrown for non-parseable value" );
        }
        catch( IllegalArgumentException e )
        {
            // this is correct, null isn't valid for the constructor
        }

        // make sure non-numeric revision numbers are detected
        try
        {
            srn.parseSvnRevisionProperty( "$Rev: nn $" );
            fail( "didn't detect a non-numberic revsion number" );
        }
        catch( IllegalArgumentException e )
        {
            // this is correct, it's non number
        }
    }



    /**
     * Test method for {@link net.srussell.framework.utility.string.vcs.subversion.SvnRevisionNumber#doesNotAppearValid(java.lang.String)}.
     */
    public void testDoesNotAppearValid()
    {
        assertTrue( "didn't detect invalid entry", srn.doesNotAppearValid( "$Rev: 46 " ) );
        assertTrue( "didn't detect invalid entry", srn.doesNotAppearValid( "Rev: 46 $" ) );
        assertTrue( "didn't detect invalid entry", srn.doesNotAppearValid( "$Rev 46 $" ) );
        assertTrue( "didn't detect invalid entry", srn.doesNotAppearValid( "$Rev:46$" ) );
        assertTrue( "didn't detect invalid entry", srn.doesNotAppearValid( "$Rev: 46$" ) );
        assertTrue( "didn't detect invalid entry", srn.doesNotAppearValid( "$Rev:46 $" ) );

    }

}
