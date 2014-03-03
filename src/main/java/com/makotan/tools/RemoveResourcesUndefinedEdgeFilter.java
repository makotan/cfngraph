package com.makotan.tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: kuroeda.makoto
 * Date: 14/03/03
 * Time: 14:13
 */
public class RemoveResourcesUndefinedEdgeFilter {
    public Template process(Template template) {
        Set<Node> allNode
                = collectNode(new HashSet<Node>(), template.getParameters());
        allNode = collectNode(allNode, template.getConditions());
        allNode = collectNode(allNode, template.getMappings());
        allNode = collectNode(allNode, template.getResources());
        allNode = collectNode(allNode, template.getOutputs());
        Set<String> allName = toNameSet(allNode);
        template.setParameters(removeUndefinedEdge(template.getParameters(), allNode, allName));
        template.setConditions(removeUndefinedEdge(template.getConditions(), allNode, allName));
        template.setMappings(removeUndefinedEdge(template.getMappings(), allNode, allName));
        template.setResources(removeUndefinedEdge(template.getResources(), allNode, allName));
        template.setOutputs(removeUndefinedEdge(template.getOutputs(), allNode, allName));
        return template;
    }
     protected Set<Node> collectNode(Set<Node> current, List<Node> nodeList) {
        for (Node node : nodeList) {
            current.add(node);
        }
        return current;
    }

    protected Set<String> toNameSet(Set<Node> allNode) {
        Set<String> garbegeName = new HashSet<>();
        for (Node node : allNode) {
            garbegeName.add(node.getName());
        }
        return garbegeName;
    }

    protected List<Node> removeUndefinedEdge(List<Node> nodeList, Set<Node> allNode, Set<String> allNodeName) {
        List<Node> current = new ArrayList<>();

        for (Node node : nodeList) {
            if (allNode.contains(node)) {
                current.add(node);
            }
        }

        for (Node node : current) {
            Set<Edge> newEdgeList = new HashSet<>();
            for (Edge edge : node.getEdgeList()) {
                if ( ! allNodeName.contains(edge.getToName())) {
                    continue;
                }
                newEdgeList.add(edge);
            }
            node.setEdgeList(newEdgeList);
        }

        return current;
    }


}
