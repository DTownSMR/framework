/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.io;

import java.io.File;
import java.io.FileFilter;

import net.srussell.framework.logging.LogMgr;

/**
 * DirectoryList is a specialized version of a directory list. One that contains ONLY sub-directories. 
 * 
 * Any normal files in the directory are ignored.  
 */
public class DirectoryListing extends FileSystemList
{
    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.io.DirectoryListing" );



    /**
     * base constructor that accepts a pathname to the desired directory 
     * 
     * @param pathName String - an abstract pathname
     */
    public DirectoryListing( String pathName )
    {
        super( pathName );
    }



    /**
     * constructor that accepts a File object representing the desired directory 
     * 
     * @param filePath File - an abstract path
     */
    public DirectoryListing( File filePath )
    {
        super( filePath );
    }



    /**
     * check for the existence of a directory in this file listing
     *
     * @param directoryName String - name of a directory to check 
     * @return boolean - true/false indicator if the file present in the list 
     */
    @Override
    public boolean contains( String directoryName )
    {
        return super.contains( directoryName );
    }



    /* (non-Javadoc)
     * @see net.srussell.framework.utility.io.FileSystemList#getFileFilter()
     */
    @Override
    protected FileFilter getFileFilter()
    {
        logger.debug( "getFileFilter", "using new DirectoryOnlyFilter()" );
        return new DirectoryOnlyFilter();
    }

}
