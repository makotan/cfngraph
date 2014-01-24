package com.makotan.tools;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * User: kuroeda.makoto
 * Date: 13/11/13
 * Time: 15:22
 */
public class ConvertMain {
    static Logger logger = LoggerFactory.getLogger(ConvertMain.class);

    public static void main(String[] args) {
        logger.debug("start");
        ConvertParams cp = new ConvertParams();
        CmdLineParser parser = new CmdLineParser(cp);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("-i templatefile.template [-o outputfile.jpg] [-pickup pickupRegex]");
            parser.printUsage(System.err);
            return;
        }
        String gvFileName = templateToGv(cp);
        String jpgFileName = cp.out != null ? cp.out.getAbsolutePath() : gvFileName.replace(".gv" , ".jpg");
        ProcessBuilder pb = new ProcessBuilder();
        logger.debug("gvToJpg");
        pb.command("dot", "-Tjpg", gvFileName, "-o", jpgFileName);
        int status = -1;
        try {
            status = pb.start().waitFor();
        } catch (IOException e) {
            logger.error("dot execution" , e);
        } catch (InterruptedException e) {
            logger.error("dot execution Interrupted" , e);
        }
        logger.info("complete {} status = {}", jpgFileName , status);
    }

    static String templateToGv(ConvertParams cp) {
        logger.debug("templateToGv");
        String inputName = cp.input.getAbsolutePath();
        String outputName = inputName.replace(".template" , ".gv");
        logger.info("templateToGv input={} , output={}", inputName, outputName);

        LoadFromTemplate loadFromTemplate = new LoadFromTemplate();
        Template template = loadFromTemplate.loadTemplate(inputName);

        AddColorFilter colorFilter = new AddColorFilter();
        template = colorFilter.process(template);

        if (cp.pickup != null &&  !cp.pickup.isEmpty() ) {
            ColorPickupFilter pickupFilter = new ColorPickupFilter();
            template = pickupFilter.process(template , cp);
        }

        TemplateToGv ttgv = new TemplateToGv();
        ttgv.printGv(template , outputName);
        logger.info("templateToGv complete");
        return outputName;
    }

}
