#Exception Handler

This exception handler code concept is based on work by Jalal Kiswani's [JK-Util](https://github.com/kiswanij/jk-util). The basic premise of this implementation is more stripped down and less functional. Just enough to provide the basic concept of the functionality for handling exceptions in an application controlable manner.

##usage

To set a handler for a given exception: 
	HandlersWrapper.instance().setHandler( Class<?> exceptionClass, ExceptionHandler thisHandler );
	
To handle an exception:
	HandlersWrapper.instance().handle( Throwable exception );