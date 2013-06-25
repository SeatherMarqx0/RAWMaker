// RawMaker : This is a library designed to write .raw single files or .raw sequences.
//
///////////////////////////////////////////////////////////////////////////////
/// @mainpage
/// Methods for writing a single or sequence of images to a .raw file
///
/// Written June 2013 by William Cork based on original ipvcam library from Bob Gunion.
///
///
/// Copyright (c) 2013 Lawrence Berkeley National Laboratory
///////////////////////////////////////////////////////////////////////////////

/**
 *
 * @author wcork
 */

package cxro.common.tools;

import java.io.*;

public class RawMaker
{
    OutputStream output = null;
    boolean sequenceStarted = false;

    /**
     * Writes a .raw file with the given filename of the given buffer.
     * 
     * Does not append '.raw' to the end of filename.
     * 
     * @param filename
     * @param buffer
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void createSingleImage(String filename, byte[] buffer) throws FileNotFoundException, IOException
    {
        OutputStream output = new BufferedOutputStream(new FileOutputStream(filename));
        for (int x = 0; x < buffer.length; x++)
        {
            output.write(buffer[x]);
        }
        output.close();
    }

    /**
     * Begins an output buffer stream to a file called filename.
     * 
     * Does not append '.raw' to the end of filename.
     * 
     * @param filename
     * @throws FileNotFoundException 
     */
    public void startImageSequence(String filename) throws FileNotFoundException
    {
        sequenceStarted = true;
        output = new BufferedOutputStream(new FileOutputStream(filename));
    }

    /**
     * Adds an image from the param buffer to the image sequence.
     * 
     * Must call startImageSequence before this method
     * otherwise, an IOException is thrown.
     * 
     * @param buffer
     * @throws IOException 
     */
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

    /**
     * Completes the image sequence and closes the file.
     * 
     * 
     * @throws IOException 
     */
    public void endImageSequence() throws IOException
    {
        sequenceStarted = false;
        output.close();
    }
}
