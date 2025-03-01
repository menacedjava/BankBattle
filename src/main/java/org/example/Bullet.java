package org.example;

public class Bullet {
    int x, y, speed = 5;
    boolean enemyBullet;
    String direction;

    public Bullet(int x, int y, String direction, boolean enemyBullet) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.enemyBullet = enemyBullet;
    }

    public void move() {
        switch (direction) {
            case "UP" -> y -= speed;
            case "DOWN" -> y += speed;
            case "LEFT" -> x -= speed;
            case "RIGHT" -> x += speed;
        }
    }

    public boolean isOutOfBounds() {
        return x < 0 || x > 800 || y < 0 || y > 600;
    }
}
