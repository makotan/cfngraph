package com.makotan.tools;

import org.junit.Test;

/**
 * User: kuroeda.makoto
 * Date: 13/11/13
 * Time: 16:19
 */
public class LoadFromTemplateTest {
    @Test
    public void LoadTest() {
        LoadFromTemplate loadFromTemplate = new LoadFromTemplate();
        loadFromTemplate.loadTemplate(getClass().getResourceAsStream("/EC2_Instance_With_Block_Device_Mapping.template"));
        System.out.println("complete!");
    }

    void printGv(String templateName) {
        LoadFromTemplate loadFromTemplate = new LoadFromTemplate();
        Template template = loadFromTemplate.loadTemplate(getClass().getResourceAsStream("/" + templateName + ".template"));

        AddColorFilter colorFilter = new AddColorFilter();
        template = colorFilter.process(template);

        TemplateToGv ttgv = new TemplateToGv();
        ttgv.printGv(template , "logs/" + templateName + ".gv");
    }

    @Test
    public void pringGv() {
        printGv("EC2_Instance_With_Block_Device_Mapping");
    }

    @Test
    public void pringGv2() {
        printGv("Drupal_Multi_AZ");
    }

    @Test
    public void pringGv3() {
        printGv("cassandra");
    }

    @Test
    public void pringGv4() {
        printGv("deepsecurity-cloudformation");
    }
}
