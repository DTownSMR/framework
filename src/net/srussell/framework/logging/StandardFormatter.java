/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * standard log formatter
 * 
 * [level] TID[thread-id] - class.name.methodName: message
 */
public class StandardFormatter extends Formatter implements FormatterI
{
    /**
     * the defined system property for line separation
     */
    private static final String LINE_SEPARATOR = System.getProperty( "line.separator" );



    /**
     * format the LogRecord data into the desired format 
     * 
     * @param record LogRecord - the log record data of the message being logged
     * @return String - the formatted data
     */
    @Override
    public String format( LogRecord record )
    {
        StringBuffer sb = new StringBuffer();
        sb.append( "[" );
        sb.append( record.getLevel() == null ? "null" : record.getLevel().getName() );
        sb.append( "] TID[" );
        sb.append( record.getThreadID() );
        sb.append( "] - " );
        sb.append( record.getSourceClassName() );
        sb.append( "." );
        sb.append( record.getSourceMethodName() );
        sb.append( ": " );
        sb.append( record.getMessage() );
        sb.append( LINE_SEPARATOR );

        return sb.toString();
    }



    /**
     * default (re: standard) behavior is to simply return the source class name from the log record
     * 
     * @param record LogRecord - the log record data of the message being logged
     * @return String - the source class name 
     */
    public String getDisplayClassName( LogRecord record )
    {
        return record.getSourceClassName();
    }

}
