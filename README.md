RAWMaker
========

Allows the use of creating '.raw' images from Java.

Instructions
  This library was created using Netbeans 7.3.1
  It can be built and included into other projects.
  The current build is in ./dist
  
Usage:
  To save a single image (example):
    RawMaker.createSingleImage("test.raw", buffer);
  
  To save a data stream (example):
    RawMaker rawMaker = new RawMaker();
    rawMaker.startImageSequence("imageSequence.raw");

    //Some Kind of capture loop or implement each image individually.
        rawMaker.insertImageIntoSequence(buffer);
    
    rawMaker.endImageSequence();