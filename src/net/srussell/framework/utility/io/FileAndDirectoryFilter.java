/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.io;

import java.io.File;
import java.io.FileFilter;

/*
 * Created by: scott on May 19, 2014 2:11:26 PM for framework::net.srussell.framework.utility.io
 */
import net.srussell.framework.logging.LogMgr;

/**
 * file filter that accepts both directories &amp; files
 *
 *    @author scott
 */
public class FileAndDirectoryFilter implements FileFilter
{
    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.io.FileAndDirectoryFilter" );



    /**
     * @see java.io.FileFilter#accept(java.io.File)
     */
    @Override
    public boolean accept( File arg0 )
    {
        logger.info( "accept", "files and directory...accepting[" + arg0.getName() + "]" );
        return true;
    }

}
