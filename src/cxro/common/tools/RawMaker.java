/*
 * This is a library designed to write .raw single files or .raw sequences.
 */
package cxro.common.tools;

import java.io.*;

/**
 *
 * @author wcork
 */
public class RawMaker
{
    OutputStream output = null;
    boolean sequenceStarted = false;

    public static void createSingleImage(String filename, byte[] buffer) throws FileNotFoundException, IOException
    {
        OutputStream output = new BufferedOutputStream(new FileOutputStream(filename));
        /*
         * write the frame to the file
         */
        for (int x = 0; x < buffer.length; x++)
        {
            output.write(buffer[x]);
        }
        output.close();
    }

    public void startImageSequence(String filename) throws FileNotFoundException
    {
        sequenceStarted = true;
        output = new BufferedOutputStream(new FileOutputStream(filename));
    }

    public void insertImageIntoSequence(byte[] buffer) throws IOException
    {
        if(!sequenceStarted)
        {
            throw new IOException("Image sequence not started");
        }
        for (int x = 0; x < buffer.length; x++)
        {
            output.write(buffer[x]);
        }
    }

    public void endImageSequence() throws IOException
    {
        sequenceStarted = false;
        output.close();
    }
}
