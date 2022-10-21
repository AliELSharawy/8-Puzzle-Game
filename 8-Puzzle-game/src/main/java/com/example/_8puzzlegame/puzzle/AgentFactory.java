package com.example._8puzzlegame.puzzle;

import com.example._8puzzlegame.SearchAgent.AStar;
import com.example._8puzzlegame.SearchAgent.Agent;
import com.example._8puzzlegame.SearchAgent.BFS;
import com.example._8puzzlegame.SearchAgent.DFS;
import static com.example._8puzzlegame.SearchAgent.Heuristics.euclideanDistance;
import static com.example._8puzzlegame.SearchAgent.Heuristics.manhattanDistance;

public class AgentFactory {
    public static Agent agentMaker(String agentName) {
        switch (agentName) {
            case "DFS" -> {
                return new DFS();
            }
            case "A* using Euclidean" -> {
                return new AStar(euclideanDistance);
            }
            case "A* using Manhattan" -> {
                return new AStar(manhattanDistance);
            }
            default -> {
                return new BFS();
            }
        }
    }
}
