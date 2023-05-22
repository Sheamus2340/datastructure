package org.sheamus.model.game;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class LuckyDrawGame extends Application {

    private static final String[] PRIZES = {
            "奖品一",
            "奖品二",
            "奖品三",
            "奖品四",
            "谢谢惠顾"
    };

    private Rectangle prizeRect;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (row == 0 || row == 4 || col == 0 || col == 4) {
                    Rectangle rectangle = createPrizeRect();
                    gridPane.add(rectangle, col, row);
                } else if (row == 2 && col == 2) {
                    Button startButton = createStartButton();
                    gridPane.add(startButton, col, row);
                }
            }
        }

        StackPane root = new StackPane();
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("转盘抽奖游戏");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Rectangle createPrizeRect() {
        Rectangle rectangle = new Rectangle(80, 80);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);

        return rectangle;
    }

    private Button createStartButton() {
        Button button = new Button("开始");
        button.setPrefSize(80, 80);

        button.setOnAction(event -> {
            animatePrizeRect();
        });

        return button;
    }

    private void animatePrizeRect() {
        Random random = new Random();
        int index = random.nextInt(PRIZES.length);

        if (prizeRect != null) {
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), prizeRect);
            scaleOut.setToX(0);
            scaleOut.setToY(0);
            scaleOut.setOnFinished(event -> {
                prizeRect.setFill(Color.WHITE);
                prizeRect.setStroke(Color.BLACK);
                prizeRect.setScaleX(1);
                prizeRect.setScaleY(1);
                animatePrizeRect();
            });
            scaleOut.play();
        } else {
            prizeRect = new Rectangle(80, 80);
            prizeRect.setFill(Color.WHITE);
            prizeRect.setStroke(Color.BLACK);
            prizeRect.setStrokeWidth(2);

            GridPane.setColumnSpan(prizeRect, 5);
            GridPane.setRowSpan(prizeRect, 5);
            GridPane.setHalignment(prizeRect, HPos.CENTER);
            GridPane.setValignment(prizeRect, VPos.CENTER);

            GridPane gridPane = (GridPane) prizeRect.getParent();
            gridPane.getChildren().add(prizeRect);
        }

        FillTransition fillTransition = new FillTransition(Duration.millis(500), prizeRect);
        fillTransition.setToValue(Color.YELLOW);

        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(500), prizeRect);
        scaleIn.setToX(1.2);
        scaleIn.setToY(1.2);

        ParallelTransition parallelTransition = new ParallelTransition(fillTransition, scaleIn);
        parallelTransition.setOnFinished(event -> {
            prizeRect.setFill(Color.YELLOW);
            prizeRect.setStroke(Color.RED);
            prizeRect.setStrokeWidth(4);
            prizeRect.setScaleX(1);
            prizeRect.setScaleY(1);
        });
        parallelTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



