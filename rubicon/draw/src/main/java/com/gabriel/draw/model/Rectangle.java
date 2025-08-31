package com.gabriel.draw.model;

import com.gabriel.draw.service.RectangleRendererService;
import com.gabriel.drawfx.model.Shape;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class Rectangle extends Shape {
    private Color fill;

    public Rectangle(Point start, Point end) {
        super(start);
        this.setEnd(end);
        this.setRendererService(new RectangleRendererService());
    }
}
