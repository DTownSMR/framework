/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.io;

import java.io.File;
import java.util.Iterator;

import net.srussell.framework.exception.ApplicationException;
import net.srussell.framework.logging.LogMgr;

/**
 */
public class FileSystemListIterator implements Iterator<File>
{

    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.io.FileSystemListIterator" );

    /**
     * the list
     */
    private File[] fileList = null;

    /**
     * the index into the list
     */
    private int index = -1;



    public FileSystemListIterator( FileSystemList iteratorTarget )
    {
        super();

        fileList = iteratorTarget.getList();
        logger.debug( "FileSystemListIterator", "iterator instantiated with [" + fileList.length + "] entries" );
    }



    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext()
    {
        logger.debug( "hasNext", "fileList.length[" + fileList.length + "] index[" + index + "]" );
        return ( ( fileList.length > 0 && fileList.length > ( index + 1 ) ) ? true : false );
    }



    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    @Override
    public File next()
    {
        if ( hasNext() )
        {
            ++index;
            logger.debug( "next", "hasNext() was true, index now[" + index + "]" );
        }
        else
        {
            throw new ApplicationException( "no next available" );
        }
        return fileList[index];

    }



    /* (non-Javadoc)
     * @see java.util.Iterator#remove()
     */
    @Override
    public void remove()
    {
        logger.entry( "remove" );
        logger.debug( "remove", "removing entry[" + fileList[index].getName() + "]" );
        File[] newFileList = new File[fileList.length - 1];
        int i = 0;
        for ( i = 0; i < index; i++ )
        {
            newFileList[i] = fileList[i];
        }
        for ( int x = i + 1; x < fileList.length; x++ )
        {
            ++i;
            newFileList[x] = fileList[i];
        }
        fileList = newFileList;
        logger.exit( "remove" );
    }



    public int size()
    {
        return fileList.length;
    }
}
