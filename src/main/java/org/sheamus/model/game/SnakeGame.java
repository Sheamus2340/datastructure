package org.sheamus.model.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends Application {

    private static final int TILE_SIZE = 20;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final double INITIAL_SPEED = 0.1;
    private static final double SPEED_INCREMENT = 0.01;

    private Direction currentDirection = Direction.RIGHT;
    private boolean gameOver = false;
    private boolean gamePaused = false;
    private int score = 0;
    private double speed = INITIAL_SPEED;
    private long lastUpdate = 0;

    private List<Position> snake = new ArrayList<>();
    private Position food;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private enum GameMode {
        EASY(0.1), NORMAL(0.08), HARD(0.05);

        private double speed;

        GameMode(double speed) {
            this.speed = speed;
        }

        public double getSpeed() {
            return speed;
        }
    }

    private GameMode currentMode = GameMode.EASY;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Label scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(Font.font(20));
        Label modeLabel = new Label("Mode: " + currentMode.name());
        modeLabel.setFont(Font.font(20));
        HBox infoBox = new HBox(20, scoreLabel, modeLabel);
        infoBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setBottom(infoBox);

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.UP && currentDirection != Direction.DOWN) {
                currentDirection = Direction.UP;
            } else if (keyCode == KeyCode.DOWN && currentDirection != Direction.UP) {
                currentDirection = Direction.DOWN;
            } else if (keyCode == KeyCode.LEFT && currentDirection != Direction.RIGHT) {
                currentDirection = Direction.LEFT;
            } else if (keyCode == KeyCode.RIGHT && currentDirection != Direction.LEFT) {
                currentDirection = Direction.RIGHT;
            } else if (keyCode == KeyCode.SPACE) {
                if (gameOver) {
                    initializeGame();
                    gamePaused = false;
                    gameOver = false;
                    draw(gc);
                } else {
                    gamePaused = !gamePaused;
                }
            } else if (keyCode == KeyCode.DIGIT1) {
                currentMode = GameMode.EASY;
                modeLabel.setText("Mode: " + currentMode.name());
            } else if (keyCode == KeyCode.DIGIT2) {
                currentMode = GameMode.NORMAL;
                modeLabel.setText("Mode: " + currentMode.name());
            } else if (keyCode == KeyCode.DIGIT3) {
                currentMode = GameMode.HARD;
                modeLabel.setText("Mode: " + currentMode.name());
            }
        });


        primaryStage.setTitle("贪吃蛇游戏");
        primaryStage.setScene(scene);
        primaryStage.show();

        initializeGame();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameOver || gamePaused) {
                    return;
                }

                long elapsedNanoSeconds = now - lastUpdate;
                double elapsedSeconds = elapsedNanoSeconds / 1_000_000_000.0;

                if (elapsedSeconds >= currentMode.getSpeed()) {
                    update();
                    draw(gc);
                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();
    }

    private void initializeGame() {
        snake.clear();
        snake.add(new Position(WIDTH / 2, HEIGHT / 2));
        snake.add(new Position(WIDTH / 2 - 1, HEIGHT / 2));
        snake.add(new Position(WIDTH / 2 - 2, HEIGHT / 2));
        generateFood();
        gameOver = false;
        score = 0;
        speed = INITIAL_SPEED;
        lastUpdate = 0;
    }

    private void generateFood() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(WIDTH);
            y = random.nextInt(HEIGHT);
        } while (isSnakeCollision(x, y));
        food = new Position(x, y);
    }

    private boolean isSnakeCollision(int x, int y) {
        for (Position position : snake) {
            if (position.x == x && position.y == y) {
                return true;
            }
        }
        return false;
    }

    private void update() {
        Position head = snake.get(0);

        int dx = 0, dy = 0;
        switch (currentDirection) {
            case UP:
                dy = -1;
                break;
            case DOWN:
                dy = 1;
                break;
            case LEFT:
                dx = -1;
                break;
            case RIGHT:
                dx = 1;
                break;
        }

        int newHeadX = (head.x + dx + WIDTH) % WIDTH;
        int newHeadY = (head.y + dy + HEIGHT) % HEIGHT;
        Position newHead = new Position(newHeadX, newHeadY);

        if (isSnakeCollision(newHead.x, newHead.y)) {
            gameOver = true;
            return;
        }

        snake.add(0, newHead);

        if (newHead.x == food.x && newHead.y == food.y) {
            score++;
            generateFood();
            speed -= SPEED_INCREMENT;
            if (speed < 0.05) {
                speed = 0.05;
            }
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    private void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

        gc.setFill(Color.GREEN);
        for (int i = 0; i < snake.size(); i++) {
            Position position = snake.get(i);
            if (i == 0) {
                gc.setFill(Color.YELLOW);
            } else {
                gc.setFill(Color.GREEN);
            }
            gc.fillRect(position.x * TILE_SIZE, position.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        gc.setFill(Color.RED);
        gc.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        Label scoreLabel = (Label) ((HBox) ((BorderPane) gc.getCanvas().getParent()).getBottom()).getChildren().get(0);
        scoreLabel.setText("Score: " + score);

        if (gameOver) {
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font(30));
            gc.fillText("Game Over", WIDTH * TILE_SIZE / 2 - 80, HEIGHT * TILE_SIZE / 2);
        }
    }

    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
