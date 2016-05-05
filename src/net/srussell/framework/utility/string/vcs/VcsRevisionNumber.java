/**
 * (c) Copyright  Scott M. Russell.  2016
 *     All rights reserved.
 */
package net.srussell.framework.utility.string.vcs;


import net.srussell.framework.logging.LogMgr;

public class VcsRevisionNumber implements RevisionNumberI
{

    private String revisionNumber = null;

    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.string.subversion.SvnRevisionNumber" );

    /** 
     * hide the empty constructor since it doesn't make sense to have a non-initialized revision number
     */
    protected VcsRevisionNumber()
    {
        super();
    }



    /**
     * base constructor
     *
     * @param vcsRevisionString - the version control system revision string
     */
    public VcsRevisionNumber( String vcsRevisionString )
    {
        super();

        if ( vcsRevisionString == null )
        {
            throw new IllegalArgumentException( "null VCS revision string not allowed" );
        }

        this.setRevisionNumber( vcsRevisionString );
    }

    @Override
    public String getRevision()
    {
        return this.getRevisionNumber();
    }

    /**
     * Returns the revisionNumber
     *
     * @return String - the revisionNumber property.
     */
    protected String getRevisionNumber()
    {
        return this.revisionNumber;
    }

    /**
     * Sets the revisionNumber
     *
     * @param revisionNumber String - new value for the revisionNumber property.
     */
    protected void setRevisionNumber( String revisionNumber )
    {
        this.revisionNumber = revisionNumber;
        if(logger.isDebugEnabled())
        {
            logger.debug( "setRevisionNumber", "setting revision as["+revisionNumber+"]" );
        }
    }

}
