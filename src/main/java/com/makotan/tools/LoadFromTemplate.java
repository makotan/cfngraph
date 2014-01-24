package com.makotan.tools;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * User: kuroeda.makoto
 * Date: 13/11/13
 * Time: 15:54
 */
public class LoadFromTemplate {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public Template loadTemplate(String fileName) {
        try(
                FileInputStream fis = new FileInputStream(fileName)
        ) {
            return loadTemplate(fis);
        } catch (FileNotFoundException e) {
            logger.error("file not found " + fileName , e);
        } catch (IOException e) {
            logger.error("io error " + fileName , e);
        }
        return null;
    }

    public Template loadTemplate(InputStream template) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(template);
            return processNode(jsonNode);
        } catch (IOException e) {
            logger.error("format error " , e);
        }

        return null;

    }

    Template processNode(JsonNode node) {
        Template template = new Template();
        processNodeList(node , template.getParameters() , "Parameters");
        logger.debug("Parameters {}" , template.getParameters());

        JsonNode mappings = node.findPath("Mappings");
        processNodeList(node, template.getMappings(), "Mappings");
        logger.debug("Mappings {}" , template.getMappings());

        JsonNode conditions = node.findPath("Conditions");
        processNodeList(node, template.getConditions(), "Conditions");
        logger.debug("Conditions {}" , template.getConditions());

        JsonNode resources = node.findPath("Resources");
        processNodeList(node, template.getResources(), "Resources");
        logger.debug("Resources {}" , template.getResources());

        JsonNode outputs = node.findPath("Outputs");
        processNodeList(node, template.getOutputs(), "Outputs");
        logger.debug("Outputs {}" , template.getOutputs());

        return template;
    }

    void processNodeList(JsonNode node , List<Node> nodeList , String typeName) {
        JsonNode parameters = node.findPath(typeName);
        //logger.debug("{} {}" , typeName , parameters.toString());
        Iterator<Map.Entry<String,JsonNode>> fields = parameters.fields();
        while (fields.hasNext()) {
            Map.Entry<String,JsonNode> entry = fields.next();
            entry.getKey();
            JsonNode value = entry.getValue();
            Node pnode = new Node();
            pnode.setName(entry.getKey());
            pnode.setPosition(typeName);
            if (value.findValue("Type") != null) {
                pnode.setTypeName(value.findValue("Type").asText());
            }
            nodeList.add(pnode);
            List<Edge> edgeList = traverseFindRefs(value);
            for (Edge e : edgeList) {
                e.setFromName(entry.getKey());
                pnode.getEdgeList().add(e);
            }
        }
    }

    List<Edge> traverseFindRefs(JsonNode node) {
        List<Edge> edgeList = new ArrayList<>();
        Iterator<Map.Entry<String,JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String,JsonNode> entry = fields.next();
            JsonNode child = entry.getValue();
            //logger.debug("child {} value {}" , entry.getKey() , child);
            if (child.asToken() == JsonToken.VALUE_STRING) {
                logger.debug("child {} value {}" , entry.getKey() , child.asText());
                addEdge(edgeList , entry.getKey() , child.asText());
            }
            edgeList.addAll(traverseFindRefs(child));
            if (child.isArray()) {
                Iterator<JsonNode> elements = child.elements();
                while (elements.hasNext()) {
                    JsonNode n = elements.next();
                    if (n.asToken() == JsonToken.VALUE_STRING) {
                        logger.debug("child {} value {}" , entry.getKey() , n.asText());
                        addEdge(edgeList , entry.getKey() , n.asText());
                    }
                    edgeList.addAll(traverseFindRefs(n));
                }
            }
        }
        Iterator<JsonNode> elements = node.elements();
        while (elements.hasNext()) {
            JsonNode n = elements.next();
            edgeList.addAll(traverseFindRefs(n));
        }
        return edgeList;
    }

    void addEdge(List<Edge> edgeList , String key , String txt) {
        switch (key) {
            case "Fn::Base64":
            case "Fn::And":
            case "Fn::Equals":
            case "Fn::If":
            case "Fn::Not":
            case "Fn::Or":
            case "Fn::FindInMap":
            case "Fn::GetAZs":
            case "Fn::Join":
            case "Fn::GetAtt":
            case "Fn::Select":
            case "DependsOn":
            case "Ref":
                Edge e = new Edge();
                e.setToName(txt);
                e.setTitle(key);
                edgeList.add(e);
            default:
        }
    }

}
