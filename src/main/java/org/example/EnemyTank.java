package org.example;

import java.util.Random;

public class EnemyTank{
    int x, y, speed = 2;
    String direction;
    Random rand = new Random();

    public EnemyTank(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = "DOWN";
    }

    public void move() {
        if (rand.nextInt(100) < 5) {
            String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
            direction = directions[rand.nextInt(4)];
        }

        switch (direction) {
            case "UP" -> y -= speed;
            case "DOWN" -> y += speed;
            case "LEFT" -> x -= speed;
            case "RIGHT" -> x += speed;
        }

        x = Math.max(0, Math.min(750, x));
        y = Math.max(0, Math.min(550, y));
    }}