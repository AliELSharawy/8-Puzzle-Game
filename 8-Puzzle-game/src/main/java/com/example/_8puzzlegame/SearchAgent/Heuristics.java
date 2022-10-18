package com.example._8puzzlegame.SearchAgent;

import java.awt.*;
import java.util.function.BiFunction;

public class Heuristics {
    public static BiFunction<Point, Point, Double> euclideanDistance =
            (Point p1, Point p2) -> Math.hypot(p1.x - p2.x, p1.y - p2.y);
    public static BiFunction<Point, Point, Double> manhattanDistance =
            (Point p1, Point p2) -> Double.valueOf(Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y));

}
