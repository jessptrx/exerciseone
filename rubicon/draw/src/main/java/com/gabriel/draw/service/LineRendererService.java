package com.gabriel.draw.service;

import com.gabriel.draw.model.Line;
import com.gabriel.drawfx.service.RendererService;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;


public class LineRendererService implements RendererService {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Line line = (Line) shape;
        g.setColor(shape.getColor());
        g.drawLine(line.getLocation().x, line.getLocation().y, line.getEnd().x, line.getEnd().y);
        Graphics2D g2 = (Graphics2D) g;                 // cast to Graphics2D
        g2.setColor(line.getColor());                   // set the line color
        g2.setStroke(new BasicStroke(3));
    }
}