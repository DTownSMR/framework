/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.collection;
/*
 * Created by: scottrussell on Apr 29, 2016 3:37:09 AM for framework::net.srussell.framework.utility.collection
 */

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import junit.framework.TestCase;
import net.srussell.framework.singleton.Singleton;
import net.srussell.framework.singleton.SingletonFactoryFunctor;
import net.srussell.framework.utility.collection.LruCacheTest.CacheTestObject;
import net.srussell.framework.utility.collection.LruCacheTest.TestObjectLruCache;

public class LruCacheTest extends TestCase
{
    
    TestObjectLruCache cache;



    public void setUp() throws Exception
    {
        cache = new TestObjectLruCache();
    }



    public void test()
    {
        addCacheEntry( "blue" );
        addCacheEntry( "Jupiter" );
        addCacheEntry( "Bob the hobo" );
        printCache();

        addCacheEntry( "yellow" );
        LruCacheWrapper.instance().get( "blue" );
        LruCacheWrapper.instance().get( "Bob the hobo" );
        addCacheEntry( "green" );
        LruCacheWrapper.instance().get( "blue" );
        LruCacheWrapper.instance().get( "Bob the hobo" );
        addCacheEntry( "black" );
        printCache();

        LruCacheWrapper.instance().get( "blue" );
        LruCacheWrapper.instance().get( "yellow" );
        LruCacheWrapper.instance().get( "green" );
        LruCacheWrapper.instance().get( "Bob the hobo" );
        addCacheEntry( "grey" );
        printCache();

        LruCacheWrapper.instance().get( "Bob the hobo" );
        printCache();

        LruCacheWrapper.instance().get( "yellow" );
        printCache();

        addCacheEntry( "purple" );
        printCache();
    }



    private void printCache()
    {
        System.out.println( "cache..." );
        Set<String> keySet = cache.keySet();
        Object[] keys = keySet.toArray();
        CacheTestObject thisObject;
        for ( int i = 0; i < keys.length; i++ )
        {
            thisObject = (CacheTestObject)LruCacheWrapper.instance().get( keys[i] );
            System.out.println( thisObject.display() );
        }
        System.out.println( "...end of cache\n\n" );
    }



    private CacheTestObject addCacheEntry( String cacheValue )
    {
        CacheTestObject newEntry = new CacheTestObject( cacheValue );
        cache.put( cacheValue, newEntry );

        return newEntry;
    }

    public class CacheTestObject
    {
        private String value;



        public CacheTestObject( String value )
        {
            super();
            this.value = value;
        }



        @Override
        public String toString()
        {
            return value;
        }



        public String display()
        {
            return "String value is[" + value + "]";
        }
    }

    /**
     * convenience wrapper access to the singleton cache and provide factory method for instantiating the LruCacheWrapper with the correct type
     */
    final public class TestObjectLruCache implements SingletonFactoryFunctor
    {
        {
            LruCacheWrapper.setFactory( this ); // we'll make the singleton instance
        }



        /**
         * instantiate the correct LRU cache object
         * @see net.srussell.framework.singleton.SingletonFactoryFunctor#makeInstance()
         */
        @Override
        public Singleton makeInstance()
        {
            return new LruCache<String, CacheTestObject>( 5 );
        }



        public boolean containsValue( Object value )
        {
            return LruCacheWrapper.instance().containsValue( value );
        }



        public boolean isEmpty()
        {
            return LruCacheWrapper.instance().isEmpty();
        }



        public CacheTestObject get( Object key )
        {
            return (CacheTestObject)LruCacheWrapper.instance().get( key );
        }



        public boolean containsKey( Object key )
        {
            return LruCacheWrapper.instance().containsKey( key );
        }



        public CacheTestObject put( String key, CacheTestObject value )
        {
            return (CacheTestObject)LruCacheWrapper.instance().put( key, value );
        }



        public void putAll( Map<? extends String, ? extends CacheTestObject> m )
        {
            LruCacheWrapper.instance().putAll( m );
        }



        public CacheTestObject remove( Object key )
        {
            return (CacheTestObject)LruCacheWrapper.instance().remove( key );
        }



        public Set<String> keySet()
        {
            return LruCacheWrapper.instance().keySet();
        }



        public Collection<CacheTestObject> values()
        {
            return LruCacheWrapper.instance().values();
        }



        public Set<Entry<String, CacheTestObject>> entrySet()
        {
            return LruCacheWrapper.instance().entrySet();
        }

    }
    
}

