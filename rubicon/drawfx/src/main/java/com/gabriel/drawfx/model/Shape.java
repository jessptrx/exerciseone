package com.gabriel.drawfx.model;

import com.gabriel.drawfx.service.RendererService;
import lombok.Data;

import java.awt.*;
@Data
public abstract class Shape {
    private Point location;
    private Point end;
    private Color color;
    private Color fill;
    private RendererService rendererService;

    public Shape() {
    }

    public Shape(Point location){
        this.setLocation(location);
        this.setEnd(location);
    }

    public Point getLocation() { return location; }
    public void setLocation(Point location) { this.location = location; }

    public Point getEnd() { return end; }
    public void setEnd(Point end) { this.end = end; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public Color getFill() { return fill; }
    public void setFill(Color fill) { this.fill = fill; }

    public RendererService getRendererService() { return rendererService; }
    public void setRendererService(RendererService rendererService) { this.rendererService = rendererService; }

}
