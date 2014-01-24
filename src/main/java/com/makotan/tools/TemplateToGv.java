package com.makotan.tools;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * User: kuroeda.makoto
 * Date: 13/11/14
 * Time: 15:52
 */
public class TemplateToGv {
    private Logger logger = LoggerFactory.getLogger(getClass());


    public void printGv(Template template , String fileName) {
        Configuration cfg = new Configuration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setClassForTemplateLoading(getClass() , "/");

        Map<String , Object> root = new HashMap<>();
        root.put("template" , template);

        freemarker.template.Template ftl = null;
        try {
            ftl = cfg.getTemplate("graph.ftl");
            FileWriter fw = new FileWriter(fileName);
            ftl.process(root , fw);
            fw.close();
        } catch (IOException e) {
            logger.error("IO Exception" , e);
        } catch (TemplateException e) {
            logger.error("Template Exception" , e);
        }

    }
}
