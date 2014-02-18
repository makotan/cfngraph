package com.makotan.tools;

import org.kohsuke.args4j.Option;

import java.io.File;

/**
 * User: kuroeda.makoto
 * Date: 13/11/19
 * Time: 9:58
 */
public class ConvertParams {
    @Option(name = "-i" , required = true , usage = "input cloudformation template file")
    public File input;

    @Option(name = "-o" , required = false , usage = "output image file")
    public File out;

    @Option(name = "-pickup" , required = false , usage = "pickup regex")
    public String pickup;

    @Option(name = "-validate" , required = false , usage = "call aws cloudformation validate-template")
    public boolean awsValidate = false;
}
