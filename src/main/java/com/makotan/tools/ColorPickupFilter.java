package com.makotan.tools;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * User: kuroeda.makoto
 * Date: 13/11/20
 * Time: 15:55
 */
public class ColorPickupFilter {
    public Template process(Template template, ConvertParams params) {
        Set<String> names = getFirstNames(params.pickup  , template );
        for (int i=0 ; i < 10 ; i++) {
            names = getLinks(names , template);
        }
        return template;
    }

    Set<String> getFirstNames(String pickupRegex , Template template ) {
        Set<String> ret = new HashSet<>();
        ret.addAll( getRegexName(pickupRegex , template.getParameters()) );
        ret.addAll( getRegexName(pickupRegex , template.getMappings()) );
        ret.addAll( getRegexName(pickupRegex , template.getResources()) );
        ret.addAll( getRegexName(pickupRegex , template.getOutputs()) );
        return ret;
    }

    Set<String> getRegexName(String pickupRegex , List<Node> nodes) {
        Pattern pattern = Pattern.compile(pickupRegex);
        Set<String> ret = new HashSet<>();
        for (Node node : nodes) {
            if (pattern.matcher(node.getName()).matches()) {
                ret.add(node.getName());
            } else {
                nodeColorBrighter(node,ret);
            }
        }
        return ret;
    }

    Set<String> getLinks(Set<String> current , Template template) {
        Set<String> ret = new HashSet<>();
        ret.addAll( getLinkNames(current , template.getParameters()) );
        ret.addAll( getLinkNames(current , template.getMappings()) );
        ret.addAll( getLinkNames(current , template.getResources()) );
        ret.addAll( getLinkNames(current , template.getOutputs()) );
        ret.addAll(current);
        nodeColorBrighter(template.getParameters(),current);
        nodeColorBrighter(template.getMappings(),current);
        nodeColorBrighter(template.getResources(),current);
        nodeColorBrighter(template.getOutputs(),current);
        return ret;
    }

    Set<String> getLinkNames(Set<String> current , List<Node> nodes) {
        Set<String> ret = new HashSet<>();
        for (Node node : nodes) {
            if (current.contains(node.getName())) {
                Set<Edge> edgeList = node.getEdgeList();
                for (Edge e: edgeList) {
                    ret.add(e.getToName());
                    ret.add(e.getFromName());
                }
            } else {
                for (Edge e: node.getEdgeList()) {
                    if (current.contains(e.getToName())) {
                        ret.add(node.getName());
                    }
                    if (current.contains(e.getFromName())) {
                        ret.add(node.getName());
                    }
                }
            }
        }
        return ret;
    }

    void nodeColorBrighter(List<Node> nodes,Set<String> current) {
        for (Node node : nodes) {
            nodeColorBrighter(node,current);
        }
    }

    void nodeColorBrighter(Node node,Set<String> current) {
        if (current.contains(node.getName())) {
            return;
        }
        node.setColor(node.getColor().brighter());
        Set<Edge> edgeList = node.getEdgeList();
        for (Edge e: edgeList) {
            e.setColor(e.getColor().brighter().brighter().brighter());
        }
    }
}
