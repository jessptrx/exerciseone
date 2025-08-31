package com.gabriel.draw.controller;

import com.gabriel.draw.model.Ellipse;
import com.gabriel.draw.model.Line;
import com.gabriel.draw.model.Rectangle;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingController implements MouseListener, MouseMotionListener {

    private final DrawingView drawingView;
    private final AppService appService;
    private Shape currentShape;
    private Shape selectedShape;
    private boolean isDrawing;
    private boolean resizeModeActive;
    private boolean moveModeActive;
    private Point dragStartPoint;

    public DrawingController(AppService appService, DrawingView drawingView) {
        this.appService = appService;
        this.drawingView = drawingView;
        this.drawingView.addMouseListener(this);
        this.drawingView.addMouseMotionListener(this);

        initializeAppService();
    }

    private void initializeAppService() {
        appService.setDrawMode(DrawMode.Idle);
        appService.setShapeMode(ShapeMode.Line);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point mousePoint = e.getPoint();
        dragStartPoint = mousePoint;

        // Check if user clicked an existing shape
        selectedShape = findShapeAtPoint(mousePoint);

        if (selectedShape != null) {
            // Selected existing shape for moving or resizing
            currentShape = selectedShape;
            isDrawing = false;
        } else {
            // Drawing a new shape
            currentShape = createShape(mousePoint);
            if (currentShape != null) {
                isDrawing = true;
            }
        }

        appService.setDrawMode(DrawMode.MousePressed);
        drawingView.repaint();
    }

    private Shape createShape(Point start) {
        Shape shape = switch (appService.getShapeMode()) {
            case Line -> new Line(start, start);
            case Rectangle -> new Rectangle(start, start);
            case Ellipse -> new Ellipse(start, start);
            default -> null;
        };

        if (shape != null) {
            shape.setColor(appService.getColor());
        }

        return shape;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point mousePoint = e.getPoint();

        if (isDrawing && currentShape != null) {
            // Finished drawing a new shape - save it
            appService.scale(currentShape, mousePoint);
            appService.create(currentShape);
        } else if (selectedShape != null) {
            if (resizeModeActive) {
                // Finished resizing
                appService.scale(selectedShape, mousePoint);
            } else if (moveModeActive) {
                // Finished moving
                Point offset = new Point(
                        mousePoint.x - dragStartPoint.x,
                        mousePoint.y - dragStartPoint.y
                );
                Point newLocation = new Point(
                        selectedShape.getLocation().x + offset.x,
                        selectedShape.getLocation().y + offset.y
                );
                appService.move(selectedShape, newLocation);
            }
        }

        // Reset state for next action
        resetDrawingState();
        appService.setDrawMode(DrawMode.MouseReleased);
        drawingView.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point mousePoint = e.getPoint();

        if (selectedShape != null && !isDrawing) {
            if (resizeModeActive) {
                // Resize the selected shape
                appService.scale(selectedShape, mousePoint);
            } else if (moveModeActive) {
                // Move the selected shape temporarily (visual feedback only)
                Point offset = new Point(
                        mousePoint.x - dragStartPoint.x,
                        mousePoint.y - dragStartPoint.y
                );
                selectedShape.setLocation(new Point(
                        dragStartPoint.x + offset.x,
                        dragStartPoint.y + offset.y
                ));
                // Update end point to maintain shape size
                int width = selectedShape.getEnd().x - selectedShape.getLocation().x;
                int height = selectedShape.getEnd().y - selectedShape.getLocation().y;
                selectedShape.setEnd(new Point(
                        selectedShape.getLocation().x + width,
                        selectedShape.getLocation().y + height
                ));
            }
            drawingView.repaint();
        } else if (isDrawing && currentShape != null) {
            // Scale the new shape being drawn
            appService.scale(currentShape, mousePoint);
            drawingView.repaint();
        }
    }

    private void resetDrawingState() {
        currentShape = null;
        selectedShape = null;
        isDrawing = false;
        resizeModeActive = false;
        moveModeActive = false;
        dragStartPoint = null;
        appService.setDrawMode(DrawMode.Idle);
    }

    // Helper method to find shape under a point
    private Shape findShapeAtPoint(Point p) {
        Drawing model = (Drawing) appService.getModel();
        for (Shape shape : model.getShapes()) {
            if (shapeContainsPoint(shape, p)) {
                return shape;
            }
        }
        return null;
    }

    private boolean shapeContainsPoint(Shape shape, Point p) {
        int x1 = Math.min(shape.getLocation().x, shape.getEnd().x);
        int y1 = Math.min(shape.getLocation().y, shape.getEnd().y);
        int x2 = Math.max(shape.getLocation().x, shape.getEnd().x);
        int y2 = Math.max(shape.getLocation().y, shape.getEnd().y);

        return (p.x >= x1 && p.x <= x2) && (p.y >= y1 && p.y <= y2);
    }

    // Unused event handlers
    @Override public void mouseClicked(MouseEvent e) { }
    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { }
    @Override public void mouseMoved(MouseEvent e) { }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void setResizeModeActive(boolean resizeModeActive) {
        this.resizeModeActive = resizeModeActive;
        this.moveModeActive = !resizeModeActive;
    }

    public void setMoveModeActive(boolean moveModeActive) {
        this.moveModeActive = moveModeActive;
        this.resizeModeActive = !moveModeActive;
    }

    public boolean isResizeModeActive() {
        return resizeModeActive;
    }

    public boolean isMoveModeActive() {
        return moveModeActive;
    }
}