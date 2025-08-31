package com.gabriel.draw.service;

import com.gabriel.draw.model.Ellipse;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.RendererService;

import java.awt.*;

public class EllipseRendererService implements RendererService {
    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Ellipse ellipse = (Ellipse) shape;
        int x = Math.min(ellipse.getLocation().x, ellipse.getEnd().x);
        int y = Math.min(ellipse.getLocation().y, ellipse.getEnd().y);
        int w = Math.abs(ellipse.getEnd().x - ellipse.getLocation().x);
        int h = Math.abs(ellipse.getEnd().y - ellipse.getLocation().y);

        Graphics2D g2 = (Graphics2D) g;            // cast to Graphics2D
        g2.setColor(ellipse.getColor());           // set proper color
        g2.setStroke(new BasicStroke(3));          // set thickness, change 3 to whatever
        g2.drawOval(x, y, w, h);
    }
}
