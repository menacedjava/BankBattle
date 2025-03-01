package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class TankBattle extends JPanel implements ActionListener {
    private int playerX = 400, playerY = 500, playerSpeed = 5;
    private String playerDirection = "UP";
    private Timer timer;
    private ArrayList<Bullet> bullets;
    private ArrayList<EnemyTank> enemies;
    private boolean gameOver = false;

    public TankBattle() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemies.add(new EnemyTank(400, 100));
        enemies.add(new EnemyTank(200, 200));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
                    resetGame();
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> playerDirection = "UP";
                    case KeyEvent.VK_S -> playerDirection = "DOWN";
                    case KeyEvent.VK_A -> playerDirection = "LEFT";
                    case KeyEvent.VK_D -> playerDirection = "RIGHT";
                    case KeyEvent.VK_SPACE ->
                            bullets.add(new Bullet(playerX + 20, playerY + 20, playerDirection, false));
                }
            }
        });

        timer = new Timer(20, this);
        timer.start();
    }

    private void resetGame() {
        playerX = 400;
        playerY = 500;
        bullets.clear();
        enemies.clear();
        enemies.add(new EnemyTank(400, 100));
        enemies.add(new EnemyTank(200, 200));
        gameOver = false;
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER!", 300, 250);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press 'R' to Restart", 320, 300);
            return;
        }


        g.setColor(Color.GREEN);
        g.fillRect(playerX, playerY, 40, 40);


        g.setColor(Color.YELLOW);
        for (Bullet bullet : bullets) {
            g.fillRect(bullet.x, bullet.y, 5, 5);
        }


        g.setColor(Color.RED);
        for (EnemyTank enemy : enemies) {
            g.fillRect(enemy.x, enemy.y, 40, 40);
        }
    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (playerDirection.equals("UP")) playerY -= playerSpeed;
//        if (playerDirection.equals("DOWN")) playerY += playerSpeed;
//        if (playerDirection.equals("LEFT")) playerX -= playerSpeed;
//        if (playerDirection.equals("RIGHT")) playerX += playerSpeed;
//
//        playerX = Math.max(0, Math.min(760, playerX));
//        playerY = Math.max(0, Math.min(560, playerY));
//
//        // O‘qlarni harakatlantirish
//        bullets.removeIf(bullet -> {
//            bullet.move();
//            return bullet.isOutOfBounds();
//        });
//
//        // Dushman tanklarini harakatlantirish
//        for (EnemyTank enemy : enemies) {
//            enemy.move();
//        }
//
//        // O‘qlarni tekshirish
//        for (int i = 0; i < bullets.size(); i++) {
//            Bullet bullet = bullets.get(i);
//            for (int j = 0; j < enemies.size(); j++) {
//                EnemyTank enemy = enemies.get(j);
//                if (new Rectangle(bullet.x, bullet.y, 5, 5).intersects(new Rectangle(enemy.x, enemy.y, 40, 40))) {
//                    bullets.remove(i);
//                    enemies.remove(j);
//                    break;
//                }
//            }
//        }
//
//        // Agar barcha dushman tanklari yo‘q qilinsa
//        if (enemies.isEmpty()) {
//            gameOver = true;
//            timer.stop();
//        }
//
//        repaint();
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Tank Battle");
//        TankBattle game = new TankBattle();
//        frame.add(game);
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
    }
}