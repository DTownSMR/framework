/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */


package net.srussell.framework.utility.io;

import java.io.File;
import java.io.FileFilter;
/*
 * Created by: scott on May 19, 2014 2:09:10 PM for framework::net.srussell.framework.utility.io
 */

import net.srussell.framework.logging.LogMgr;

/**
 * Listing for both files and directories
 *
 *    @author scott
 */
public class FileAndDirectoryListing extends FileSystemList
{
    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.io.FileAndDirectoryListing" );



    /**
     * base constructor that accepts a pathname to the desired directory 
     * 
     * @param pathName String - an abstract pathname
     */
    public FileAndDirectoryListing( String pathName )
    {
        super( pathName );
    }



    /**
     * * constructor that accepts a File object representing the desired directory 
     * 
     * @param filePath File - an abstract path
     */
    public FileAndDirectoryListing( File filePath )
    {
        super( filePath );
    }



    /**
     * check for the existence of a file in this file listing
     *
     * @param fileSpec String - name of a file to check 
     * @return boolean - true/false indicator if the file present in the list 
     */
    @Override
    public boolean contains( String fileSpec )
    {
        return super.contains( fileSpec );
    }



    /* (non-Javadoc)
     * @see net.srussell.framework.utility.io.FileSystemList#getFileFilter()
     */
    @Override
    protected FileFilter getFileFilter()
    {
        logger.debug( "getFileFilter", "using new FileAndDirectoryFilter()" );
        return new FileAndDirectoryFilter();
    }

    /*
     * used for debugging
     */
    protected void dumpListing()
    {
        FileSystemListIterator fsli = this.iterator();
        File thisEntry;
        while(fsli.hasNext())
        {
            thisEntry = fsli.next();
            System.out.println(thisEntry.getAbsolutePath()+" "+thisEntry.getName()+" isDirectory:"+thisEntry.isDirectory());
        }
    }
}
