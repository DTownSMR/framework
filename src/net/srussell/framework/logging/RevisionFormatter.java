/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.logging;

import java.util.logging.LogRecord;

import net.srussell.framework.utility.string.vcs.RevisionNumberI;

/**
 */
public class RevisionFormatter extends StandardFormatter implements FormatterI
{
    /**
     * the revision number from instantiation
     */
    private String revNumber = "";



    /**
     * base constructor for a revision tagged formatter
     * 
     * @param revisionNumber RevisionNumberI - source of the revision number
     */
    public RevisionFormatter( RevisionNumberI revisionNumber )
    {
        super();

        this.setRevNumber( "[" + revisionNumber.getRevision() + "]" );
    }



    @Override
    public String getDisplayClassName( LogRecord record )
    {
        return record.getSourceClassName() + this.getRevNumber();
    }



    /**
     * Returns the revNumber
     *
     * @return String - the revNumber property.
     */
    private String getRevNumber()
    {
        return this.revNumber;
    }



    /**
     * Sets the revNumber
     *
     * @param revNumber String - new value for the revNumber property.
     */
    private void setRevNumber( String revNumber )
    {
        this.revNumber = revNumber;
    }

}
