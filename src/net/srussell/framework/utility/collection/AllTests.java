/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.collection;
/*
 * Created by: scottrussell on Apr 29, 2016 3:44:21 AM for framework::net.srussell.framework.utility.collection
 */

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite( AllTests.class.getName() );
        //$JUnit-BEGIN$
        suite.addTestSuite( LruCacheTest.class );
        //$JUnit-END$
        return suite;
    }
}

