/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.logging;

import java.util.logging.LogRecord;

/**
 * interface for LogMgr formatter classes
 */
public interface FormatterI
{
    public String getDisplayClassName( LogRecord record );
}
