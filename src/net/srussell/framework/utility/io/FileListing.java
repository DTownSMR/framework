/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.io;

import java.io.File;
import java.io.FileFilter;

import net.srussell.framework.logging.LogMgr;

/**
 * FileListing is a specialized version of a directory list. One that contains ONLY files. 
 * 
 * Any sub-directories in the directory are ignored.  
 */
public class FileListing extends FileSystemList
{
    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.io.FileListing" );



    /**
     * base constructor that accepts a pathname to the desired directory 
     * 
     * @param pathname String - an abstract pathname
     */
    public FileListing( String pathname )
    {
        super( pathname );

    }



    /**
     * constructor that accepts a File object representing the desired directory 
     * 
     * @param filePath File - an abstract path
     */
    public FileListing( File filePath )
    {
        super( filePath );

    }



    /**
     * check for the existence of a file in this file listing
     *
     * @param fileName String - name of a file to check 
     * @return boolean - true/false indicator if the file present in the list 
     */
    @Override
    public boolean contains( String fileName )
    {
        return super.contains( fileName );
    }



    /* (non-Javadoc)
     * @see net.srussell.framework.utility.io.FileSystemList#getFileFilter()
     */
    @Override
    protected FileFilter getFileFilter()
    {
        logger.debug( "getFileFilter", "using new FileOnlyFilter()" );
        return new FileOnlyFilter();
    }

}
