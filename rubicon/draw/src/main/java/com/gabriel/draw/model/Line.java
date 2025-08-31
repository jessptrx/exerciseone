package com.gabriel.draw.model;


import com.gabriel.draw.service.LineRendererService;
import lombok.Data;
import com.gabriel.drawfx.model.Shape;
import lombok.EqualsAndHashCode;

import java.awt.*;


@EqualsAndHashCode(callSuper = true)
@Data
public class Line extends Shape {

    public Line(Point start, Point end){
        super(start);
        this.setEnd(end);
        this.setRendererService(new LineRendererService());
    }
}
