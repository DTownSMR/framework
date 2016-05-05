/**
 * (c) Copyright  Scott M. Russell.  2010
 *     All rights reserved.
 */

package net.srussell.framework.utility.collection;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import net.srussell.framework.singleton.Singleton;

/**
 * Least Recently Used (LRU) cache implementation.
 * 
 * This LRU is implemented as an extension of a LinkedHashMap which provides the vast majority of the implementation. 
 * 
 * This cache wrapper ensures capacity &amp; access order is maintained. The super class is initialized to a certain capacity 
 * and ordering mode is specified as access-order. 
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> implements Singleton
{
    // generated unique serial version ID
    private static final long serialVersionUID = -3578819126533760413L;

    /**
     * the configured max capacity of the cache
     */
    private int capacity;



    /**
     * base constructor
     * 
     * @param initialCapacity int - desired cache capacity
     */
    public LruCache( int initialCapacity )
    {
        super( initialCapacity + 1, 1.0f, true );

        setCapacity( initialCapacity );
    }



    /**
     * ensure we don't blow capacity
     * 
     * @param Entry oldestOne - entry being processed
     */
    @Override
    protected boolean removeEldestEntry( Entry oldestOne )
    {
        return ( size() > getCapacity() );
    }



    /**
     * @return the capacity
     */
    private int getCapacity()
    {
        return capacity;
    }



    /**
     * @param capacity the capacity to set
     */
    private void setCapacity( int capacity )
    {
        this.capacity = capacity;
    }
}
