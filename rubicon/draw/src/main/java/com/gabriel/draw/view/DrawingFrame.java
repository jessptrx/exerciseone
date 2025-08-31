package com.gabriel.draw.view;

import com.gabriel.draw.controller.DrawingController;
import com.gabriel.draw.controller.DrawingWindowController;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DrawingFrame extends JFrame {

    public DrawingFrame(AppService appService) {

        DrawingWindowController drawingWindowController = new DrawingWindowController(appService);
        this.addWindowListener(drawingWindowController);
        this.addWindowFocusListener(drawingWindowController);
        this.addWindowStateListener(drawingWindowController);


        // Shape buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0)); // 1 row, 3 columns, 10px gap

        JButton lineButton = new JButton("Line");
        JButton rectButton = new JButton("Rectangle");
        JButton ellipseButton = new JButton("Ellipse");

        lineButton.addActionListener(e -> appService.setShapeMode(ShapeMode.Line));
        rectButton.addActionListener(e -> appService.setShapeMode(ShapeMode.Rectangle));
        ellipseButton.addActionListener(e -> appService.setShapeMode(ShapeMode.Ellipse));

        buttonPanel.add(lineButton);
        buttonPanel.add(rectButton);
        buttonPanel.add(ellipseButton);

// center the panel in the north
        JPanel northWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northWrapper.add(buttonPanel);
        this.add(northWrapper, BorderLayout.NORTH);

        DrawingView drawingView = new DrawingView(appService);
        this.getContentPane().add(drawingView, BorderLayout.CENTER);

        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(1, 8, 10, 0)); // 1 row, 8 columns, 10px gap

// Array of colors
        Color[] colors = {
                new Color(0xFF0000), // Red
                new Color(0xFFA500), // Orange
                new Color(0xFFFF00), // Yellow
                new Color(0x00FF00), // Green
                new Color(0x0000FF), // Blue
                new Color(0x4B0082), // Indigo
                new Color(0xEE82EE), // Violet
                new Color(0x000000), // Black
                new Color(0xFFFFFF)  // White
        };

        for (Color color : colors) {
            JButton colorButton = new JButton();
            colorButton.setBackground(color);
            colorButton.setOpaque(true);
            colorButton.setBorderPainted(false);
            colorButton.setPreferredSize(new Dimension(30, 30));
            colorButton.addActionListener(e -> appService.setColor(color));
            colorPanel.add(colorButton);
        }

// center the panel in the south
        JPanel southWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southWrapper.add(colorPanel);
        this.add(southWrapper, BorderLayout.SOUTH);


        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(3, 1, 0, 10)); // Move, Resize, Fill

        JButton moveButton = new JButton("Move");
        JButton resizeButton = new JButton("Resize");
        JButton fillButton = new JButton("Fill");

        actionPanel.add(moveButton);
        actionPanel.add(resizeButton);
        actionPanel.add(fillButton);

// center the panel
        JPanel actionWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actionWrapper.add(actionPanel);
        this.add(actionWrapper, BorderLayout.WEST);

        DrawingController controller = (DrawingController) drawingView.getMouseListeners()[0];

// Move button - just select the shape to move it (dragging already handles movement)
        moveButton.addActionListener(e -> {
            controller.setMoveModeActive(true);
            controller.setResizeModeActive(false);
        });

// Resize button - activate resize mode
        resizeButton.addActionListener(e -> {
            controller.setResizeModeActive(true);
        });

// Fill button - apply fill color to selected shape
        fillButton.addActionListener(e -> {
            if (controller.getSelectedShape() != null) {
                controller.getSelectedShape().setFill(appService.getColor());
                controller.getSelectedShape().setFill(true);
                drawingView.repaint();
            }
        });
    }
    }
