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
public abstract class FileSystemList
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.io.FileSystemList" );

    /**
     * the File created for this abstract path
     */
    private File targetPath = null;

    /**
     * the File list for this abstract path
     */
    private File[] targetList = null;



    /**
     * Extender's of this class implement this abstract method to supply base code with the implementors desired FileFilter 
     *
     * @return FileFilter - desired FileFilter by extending class
     */
    protected abstract FileFilter getFileFilter();



    /**
     * base constructor
     * 
     * @param pathName String - an abstract path name
     */
    public FileSystemList( String pathName )
    {
        super();

        logger.debug( "DirectoryListing", "File object created for[" + pathName + "]" );
        File filePathName = new File( pathName );

        init( filePathName );
    }



    /**
     * constructor when a File object is already created
     * 
     * @param filePathName File - an abstract path
     */
    public FileSystemList( File filePathName )
    {
        super();

        init( filePathName );
    }



    /**
     * init this object at constructor time so it's ready to be used.
     *
     * @param filePathName File - File object to files system list target
     */
    private void init( File filePathName )
    {
        logger.debug( "DirectoryListing", "File object: exists[" + filePathName.exists() + "] isDirectory[" + filePathName.isDirectory() + "] " );

        this.setTargetPath( filePathName );
        this.setTargetList( getTargetPath().listFiles( this.getFileFilter() ) );
    }



    /**
     * return the size of the file system list
     *
     * @return int - the number of entries in this list
     */
    public int size()
    {
        return this.getTargetList().length;
    }



    /**
     * determine if a specified file name exists in the list of files.
     *
     * @param name String - file name to look for
     * @return boolean - true/false indicator if the filename matches a file in the list.
     */
    protected boolean contains( String name )
    {
        logger.entry( "contains" );

        boolean found = false;

        File[] list = this.getTargetList();

        if ( list != null && list.length > 0 )
        {
            for ( int i = 0; i < list.length; i++ )
            {
                if ( list[i].getName().equals( name ) )
                {
                    found = true;
                }
            }
        }
        else
        {
            logger.debug( "contains", "list empty...can't look for[" + name + "]" );
        }

        logger.exit( "contains" );
        return found;
    }



    /**
     * Returns the targetPath
     *
     * @return File - the targetPath property.
     */
    protected File getTargetPath()
    {
        return this.targetPath;
    }



    /**
     * Sets the targetPath
     *
     * @param targetPath File - new value for the targetPath property.
     */
    protected void setTargetPath( File targetPath )
    {
        logger.debug( "getTargetPath", "setting targetPath[" + targetPath.getName() + "]" );
        this.targetPath = targetPath;
    }



    /**
     * Returns the targetList
     *
     * @return File[] - the targetList property.
     */
    private File[] getTargetList()
    {

        return this.targetList;
    }



    /**
     * Sets the targetList
     *
     * @param targetList File[] - new value for the targetList property.
     */
    private void setTargetList( File[] targetList )
    {

        this.targetList = targetList;
    }



    /**
     * get the File list for this abstract path
     *
     * @return File[] - array of File objects
     */
    public File[] getList()
    {
        return this.targetList;
    }



    /**
     * obtain an iterator over a collection for this file system list
     *
     * @return FileSystemListIterator - an iterator for this file system list
     */
    public FileSystemListIterator iterator()
    {
        return new FileSystemListIterator( this );
    }

}
