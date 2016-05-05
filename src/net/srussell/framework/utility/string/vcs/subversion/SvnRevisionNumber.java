/**
 * (c) Copyright  Scott M. Russell.  2010,2016
 *     All rights reserved.
 */

package net.srussell.framework.utility.string.vcs.subversion;

import net.srussell.framework.logging.LogMgr;
import net.srussell.framework.utility.string.vcs.RevisionNumberI;
import net.srussell.framework.utility.string.vcs.VcsRevisionNumber;

/**
 */
public class SvnRevisionNumber extends VcsRevisionNumber implements RevisionNumberI
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.string.subversion.SvnRevisionNumber" );

    /** 
     * hide the empty constructor since it doesn't make sense to have a non-initialized SVN revision number
     */
    protected SvnRevisionNumber()
    {
        super();
    }



    /**
     * base constructor
     *
     * @param svnRevisionString - the subversion revision string
     */
    public SvnRevisionNumber( String svnRevisionString )
    {
        super();

        if ( svnRevisionString == null )
        {
            throw new IllegalArgumentException( "null SVN revision string not allowed" );
        }

        this.setRevisionNumber( parseSvnRevisionProperty( svnRevisionString ) );
    }



    /**
     * pull the actual SVN revision number from the $Rev$ formatted field.
     *
     * @param svnRevisionString String  - a SVN revision String
     * @return String - the parsed out revision number
     */
    protected String parseSvnRevisionProperty( String svnRevisionString )
    {
        logger.entry( "parseSvnRevisionProperty" );

        if ( doesNotAppearValid( svnRevisionString ) )
        {
            throw new IllegalArgumentException( "SVN revision string[" + svnRevisionString + "] does not appear to be valid" );
        }

        String[] bits = svnRevisionString.split( " " );
        if ( logger.isDebugEnabled() )
        {
            for ( int i = 0; i < bits.length; i++ )
            {
                System.out.println( "bit [" + i + "] = [" + bits[i] + "]" );
            }
        }

        String oRevision = null;
        try
        {
            oRevision = bits[1];
        }
        catch( ArrayIndexOutOfBoundsException e )
        {
            throw new IllegalArgumentException( "non-parseable[" + svnRevisionString + "] SVN revision string" );
        }

        // the SVN revision number must be...ummm...A NUMBER!
        try
        {
            Integer.parseInt( oRevision );
        }
        catch( NumberFormatException e )
        {
            throw new IllegalArgumentException( "revision number part of [" + svnRevisionString + "] is non-numeric" );
        }

        logger.debug( "parseSvnRevisionProperty", "using [", oRevision, "] from[", svnRevisionString, "]" );

        logger.exit( "getRevision" );
        return oRevision;
    }



    /**
     * try and do some basic validity testing that a string appears to be a valid SVN revision String conforming to the format: "$Rev: nn $"
     *
     * @param svnRevisionString String - an alleged SVN revision string
     * @return boolean - true/false indicator
     */
    protected boolean doesNotAppearValid( String svnRevisionString )
    {
        logger.entry( "doesNotAppearValid" );
        boolean oFlag = false;

        logger.debug( "doesNotAppearValid", "validating[", svnRevisionString, "]..." );

        // must start and end with a dollar sign
        if ( !svnRevisionString.startsWith( "$" ) || !svnRevisionString.endsWith( "$" ) )
        {
            logger.debug( "doesNotAppearValid", "does not start or end with dollar sign" );
            oFlag = true;
        }

        // must have a colon
        if ( svnRevisionString.indexOf( ":" ) == -1 )
        {
            logger.debug( "doesNotAppearValid", "does not contain a colon" );
            oFlag = true;
        }

        // must have two spaces 
        int idx = svnRevisionString.indexOf( " " );
        if ( idx == -1 || ( svnRevisionString.indexOf( " ", idx + 1 ) == -1 ) )
        {
            logger.debug( "doesNotAppearValid", "does not have two spaces" );
            oFlag = true;
        }

        logger.exit( "doesNotAppearValid" );
        return oFlag;
    }
}
