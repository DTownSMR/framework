/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.io;

import java.io.File;
import java.io.FileFilter;

import net.srussell.framework.logging.LogMgr;

/**
 */
public class FileOnlyFilter implements FileFilter
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.io.FileOnlyFilter" );



    /* (non-Javadoc)
     * @see java.io.FileFilter#accept(java.io.File)
     */
    @Override
    public boolean accept( File pathname )
    {
        logger.entry( "accept" );
        boolean acceptIt = false;

        if ( pathname.isFile() )
        {
            logger.debug( "accept", "found file[" + pathname.getName() + "]...accepting" );
            acceptIt = true;
        }
        else
        {
            logger.debug( "accept", "[" + pathname.getName() + "] is not a file...skipping" );
        }
        logger.exit( "accept" );
        return acceptIt;
    }
}
