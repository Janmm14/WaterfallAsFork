package net.md_5.bungee;

import com.google.common.base.Joiner;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * Series of utility classes to perform various operations.
 */
public class Util
{

    public static final int DEFAULT_PORT = 25565;

    /**
     * Method to transform human readable addresses into usable address objects.
     *
     * @param hostline in the format of 'host:port'
     * @return the constructed hostname + port.
     */
    public static InetSocketAddress getAddr(String hostline)
    {
        URI uri;
        try
        {
            uri = new URI( "tcp://" + hostline );
        } catch ( URISyntaxException ex )
        {
            throw new IllegalArgumentException( "Bad hostline", ex );
        }

        return new InetSocketAddress( uri.getHost(), ( uri.getPort() ) == -1 ? DEFAULT_PORT : uri.getPort() );
    }

    /**
     * Formats an integer as a hex value.
     *
     * @param i the integer to format
     * @return the hex representation of the integer
     */
    public static String hex(int i)
    {
        return String.format( "0x%02X", i );
    }

    /**
     * Constructs a pretty one line version of a {@link Throwable}. Useful for
     * debugging.
     *
     * @param t the {@link Throwable} to format.
     * @return a string representing information about the {@link Throwable}
     */
    public static String exception(Throwable t)
    {
        // TODO: We should use clear manually written exceptions
        StackTraceElement[] trace = t.getStackTrace();
        return t.getClass().getSimpleName() + " : " + t.getMessage()
                + ( ( trace.length > 0 ) ? " @ " + t.getStackTrace()[0].getClassName() + ":" + t.getStackTrace()[0].getLineNumber() : "" );
    }

    public static String csv(Iterable<?> objects)
    {
        return format( objects, ", " );
    }

    public static String format(Iterable<?> objects, String separators)
    {
        return Joiner.on( separators ).join( objects );
    }

    /**
     * Converts a String to a UUID
     *
     * @param uuid The string to be converted
     * @return The result
     */
    public static UUID getUUID(String uuid)
    {
        return UUID.fromString(new StringBuilder(36).append(uuid, 0, 8).append('-').append(uuid, 8, 12).append('-')
                .append(uuid, 12, 16).append('-').append(uuid, 16, 20).append('-').append(uuid, 20, 32).toString());
    }

    /**
     * Converts a UUID to a Mojang UUID
     *
     * @param uuid The string to be converted
     * @return The result
     */
    public static String getMojangUUID(UUID uuid)
    {
        String id = uuid.toString();
        return new StringBuilder(32).append(id, 0, 8).append(id, 9, 13).append(id, 14, 18).append(id, 19, 23).append(id, 24, 36).toString();
    }
}
