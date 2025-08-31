package com.gabriel.drawfx.model;

import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Data
public class Drawing {
    private Color color;
    private Color fill;
    private ShapeMode shapeMode = ShapeMode.Line;
    private DrawMode drawMode = DrawMode.Idle;
    private List<Shape> shapes;

    public Drawing() {
        shapes = new ArrayList<>();
    }

    public List<Shape> getShapes() {
        return shapes;
    }
    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }


    public ShapeMode getShapeMode() {
        return shapeMode;
    }
    public void setShapeMode(ShapeMode shapeMode) {
        this.shapeMode = shapeMode;
    }


    public DrawMode getDrawMode() {
        return drawMode;
    }
    public void setDrawMode(DrawMode drawMode) {
        this.drawMode = drawMode;
    }


    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }


    public Color getFill() {
        return fill;
    }
    public void setFill(Color color) {
        this.fill = color;
    }

}
