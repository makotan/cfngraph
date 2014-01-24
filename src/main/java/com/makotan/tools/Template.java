package com.makotan.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * User: kuroeda.makoto
 * Date: 13/11/13
 * Time: 16:33
 */
public class Template {
    private List<Node> parameters = new ArrayList<>();
    private List<Node> mappings = new ArrayList<>();
    private List<Node> conditions = new ArrayList<>();
    private List<Node> resources = new ArrayList<>();
    private List<Node> outputs = new ArrayList<>();

    public List<Node> getParameters() {
        return parameters;
    }

    public void setParameters(List<Node> parameters) {
        this.parameters = parameters;
    }

    public List<Node> getMappings() {
        return mappings;
    }

    public void setMappings(List<Node> mappings) {
        this.mappings = mappings;
    }

    public List<Node> getConditions() {
        return conditions;
    }

    public void setConditions(List<Node> conditions) {
        this.conditions = conditions;
    }

    public List<Node> getResources() {
        return resources;
    }

    public void setResources(List<Node> resources) {
        this.resources = resources;
    }

    public List<Node> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Node> outputs) {
        this.outputs = outputs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Template{");
        sb.append("parameters=").append(parameters);
        sb.append(", mappings=").append(mappings);
        sb.append(", conditions=").append(conditions);
        sb.append(", resources=").append(resources);
        sb.append(", outputs=").append(outputs);
        sb.append('}');
        return sb.toString();
    }
}
