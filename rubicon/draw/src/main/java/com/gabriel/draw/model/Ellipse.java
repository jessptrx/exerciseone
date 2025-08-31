package com.gabriel.draw.model;

import com.gabriel.draw.service.EllipseRendererService;
import lombok.Data;
import com.gabriel.drawfx.model.Shape;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class Ellipse extends Shape {
    private Color fill;

    public Ellipse(Point start, Point end) {
        super(start);
        this.setEnd(end);
        this.setRendererService(new EllipseRendererService());
    }
}
