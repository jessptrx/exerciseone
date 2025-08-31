package com.gabriel.draw.service;

import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.service.MoverService;
import com.gabriel.drawfx.service.ScalerService;

import java.awt.*;

public class DrawingAppService implements AppService {
    private final Drawing drawing;
    private final MoverService moverService;
    private final ScalerService scalerService;

    public DrawingAppService() {
        drawing = new Drawing();
        moverService = new MoverService();
        scalerService = new ScalerService();
    }

    // Color & Fill
    @Override
    public Color getColor() {
        return drawing.getColor();
    }

    @Override
    public void setColor(Color color) {
        drawing.setColor(color);
    }

    @Override
    public Color getFill() {
        return drawing.getFill();
    }

    @Override
    public void setFill(Color fill) {
        drawing.setFill(fill);
    }

    // ShapeMode & DrawMode
    @Override
    public ShapeMode getShapeMode() {
        return drawing.getShapeMode();
    }

    @Override
    public void setShapeMode(ShapeMode shapeMode) {
        drawing.setShapeMode(shapeMode);
    }

    @Override
    public DrawMode getDrawMode() {
        return drawing.getDrawMode();
    }

    @Override
    public void setDrawMode(DrawMode drawMode) {
        drawing.setDrawMode(drawMode);
    }

    // Shape operations
    @Override
    public void move(Shape shape, Point newLoc) {
        moverService.move(shape, newLoc);
    }

    @Override
    public void scale(Shape shape, Point newEnd) {
        scalerService.scale(shape, newEnd);
    }

    @Override
    public void create(Shape shape) {
        drawing.getShapes().add(shape);
    }

    @Override
    public void close() {
        System.exit(0);
    }

    @Override
    public Object getModel() {
        return drawing;
    }
}
