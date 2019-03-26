package application;
	

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	// размер игрового пол€
	private static final int height = 600;
	private static final int width = 800;
	
	// размер ракетки и м€чика
	
	private static final int RACKET_WIDTH = 10;
	private static final int RACKET_HEIGHT = 90;
	
	private static final int BALL_R = 30;
	
	double compX = width - RACKET_WIDTH ;
	double compY = height/2 ;
	
	double playerX = 0;
	double playerY = height/2;
	
	// координаты м€ча
	
	double ballX = width/2;
	double ballY= height/2;
	
	GraphicsContext gc;
	boolean gameStarted;
	
	double speedX = 3;
	double speedY = 4;
	
	public void drawField() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0,0,width,height);
			
		gc.setFill(Color.YELLOW);
		gc.fillRect(width/2 - 1 ,0, 2, height);
		
		if( gameStarted) {
			ballX += speedX;
			ballY += speedY;
			
			
			if  (ballY > 600)  
				
		   speedY = speedY* (-1);
				
			if  (ballY < 0 )  
				speedY = speedY* (-1);
			
            if  (ballX < 0 )  
            	speedX = speedX* (-1);
				
            if ( (ballX > 800 ) ) 
            	speedX = speedX* (-1);
				
			if (ballX > ( width - width/4)) {
				
				if (ballY <= 90) 
					compY = 45;	
				if (ballY >= 540) 
					compY = 555;
				else
				compY = ballY ;
				
				
			}
			
			gc.fillOval(ballX - BALL_R/2, ballY - BALL_R/2, BALL_R, BALL_R);
		}
		
		
		
		gc.fillRect(playerX, playerY - RACKET_HEIGHT/2 ,RACKET_WIDTH , RACKET_HEIGHT );
		gc.fillRect( compX, compY- RACKET_HEIGHT/2, RACKET_WIDTH , RACKET_HEIGHT );
		
	}
	
	@Override
	
	
	public void start(Stage primaryStage) {
		
		Canvas canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		drawField();
		
		//Duration.millis(10), e -> drawField()
		
		Timeline t1 = new Timeline(new KeyFrame(Duration.millis(10), e -> drawField()));
		t1.setCycleCount(Timeline.INDEFINITE);
		
		
		canvas.setOnMouseClicked( e -> gameStarted = true);
		canvas.setOnMouseMoved( e -> playerY = e.getY());
		
		primaryStage.setScene(new Scene(new StackPane(canvas)));
		primaryStage.setTitle("Ping-pong");
		primaryStage.show();
		t1.play();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
