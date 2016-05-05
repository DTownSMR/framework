/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.string;

import net.srussell.framework.logging.LogMgr;
import net.srussell.framework.utility.string.vcs.subversion.SvnRevisionNumber;

/**
 * this class encapsulates various common String related conversion methods as static public methods 
 */
public class Conversion
{
    // revision number
    private static SvnRevisionNumber revisionNumber = new SvnRevisionNumber( "$Rev: 52 $" );
    // auto-generated logging object
    private static LogMgr logger = new LogMgr( "net.srussell.framework.utility.string.Conversion", revisionNumber );



    private Conversion()
    {
        super();
    }

    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };



    public static String toHexString( byte[] aByte )
    {
        StringBuffer hexString = new StringBuffer( aByte.length * 3 + 2 );
        for ( int i = 0; i < aByte.length; i++ )
        {
            if ( i != 0 )
            {
                hexString.append( ' ' );
            }
            hexString.append( hexDigits[ ( aByte[i] & 0xF0 ) >> 4] );
            hexString.append( hexDigits[aByte[i] & 0x0F] );
        }
        return hexString.toString();
    }



    public static String toHexString( int i )
    {
        StringBuffer hexString = new StringBuffer();
        hexString.append( hexDigits[ (( i & 0xF0 ) >> 4)] );
        hexString.append( hexDigits[i & 0x0F] );

        return hexString.toString();
    }
}
