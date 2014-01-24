package com.makotan.tools;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: kuroeda.makoto
 * Date: 13/11/13
 * Time: 15:41
 */
public class Node {
    private String location;
    private String position;
    private String name;
    private String typeName;
    private Color color = new Color(Color.BLACK.getRGB());
    private Set<Edge> edgeList = new HashSet<>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Set<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(Set<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = new Color(color.getRGB());
    }

    public String getColorString() {
        String htmlColor = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return htmlColor;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("location='").append(location).append('\'');
        sb.append(", position='").append(position).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", typeName='").append(typeName).append('\'');
        sb.append(", color=").append(color);
        sb.append(", edgeList=").append(edgeList);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        if (!color.equals(node.color)) return false;
        if (edgeList != null ? !edgeList.equals(node.edgeList) : node.edgeList != null) return false;
        if (location != null ? !location.equals(node.location) : node.location != null) return false;
        if (!name.equals(node.name)) return false;
        if (!position.equals(node.position)) return false;
        if (typeName != null ? !typeName.equals(node.typeName) : node.typeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + position.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + color.hashCode();
        result = 31 * result + (edgeList != null ? edgeList.hashCode() : 0);
        return result;
    }
}
