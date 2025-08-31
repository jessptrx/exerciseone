package com.gabriel.draw.service;

import com.gabriel.draw.model.Rectangle;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.RendererService;

import java.awt.*;

public class RectangleRendererService implements RendererService {
    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Rectangle rect = (Rectangle) shape;
        int x = Math.min(rect.getLocation().x, rect.getEnd().x);
        int y = Math.min(rect.getLocation().y, rect.getEnd().y);
        int w = Math.abs(rect.getEnd().x - rect.getLocation().x);
        int h = Math.abs(rect.getEnd().y - rect.getLocation().y);

        Graphics2D g2 = (Graphics2D) g;             // cast to Graphics2D
        g2.setColor(rect.getColor());               // set the line color
        g2.setStroke(new BasicStroke(3));          // line thickness

        g2.drawRect(x, y, w, h);
    }
}
