package com.makotan.tools;

import java.awt.*;

/**
 * User: kuroeda.makoto
 * Date: 13/11/13
 * Time: 15:41
 */
public class Edge {
    private String fromName;
    private String toName;
    private String title;
    private Color color = new Color(Color.BLACK.getRGB());

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        if (!color.equals(edge.color)) return false;
        if (fromName != null ? !fromName.equals(edge.fromName) : edge.fromName != null) return false;
        if (title != null ? !title.equals(edge.title) : edge.title != null) return false;
        if (!toName.equals(edge.toName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fromName != null ? fromName.hashCode() : 0;
        result = 31 * result + toName.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + color.hashCode();
        return result;
    }

    @Override

    public String toString() {
        final StringBuilder sb = new StringBuilder("Edge{");
        sb.append("fromName='").append(fromName).append('\'');
        sb.append(", toName='").append(toName).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", color=").append(color);
        sb.append('}');
        return sb.toString();
    }

}
