/**
 * (c) Copyright  Scott M. Russell.  2016
 *     All rights reserved.
 */

package net.srussell.framework.utility.string.vcs;

import junit.framework.TestCase;

public class VcsRevisionNumberTest extends TestCase
{



    private static final String RELEASE_VERSION = "v1.0";

    public void testGetRevision()
    {
        VcsRevisionNumber rev = new VcsRevisionNumber(RELEASE_VERSION);
        assertEquals( "wrong value returned", RELEASE_VERSION, rev.getRevision() );
    }
}

