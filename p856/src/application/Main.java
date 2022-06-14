package application;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();                 // Parent 하위 객체인 VBox 생
			root.setPrefWidth(350);                 // VBox의 폭 설정
			root.setPrefHeight(150);                 // VBox의 높이 설정
			root.setAlignment(Pos.CENTER);          // 컨트롤을 중앙으로 정렬
			root.setSpacing(20);                    // 컨트롤의 수직 간격
			
			Label label = new Label();
			label.setText("Hello, JavaFX");
			label.setFont(new Font(50));
			
			Button button = new Button();
			button.setText("확인");
			button.setOnAction(event -> Platform.exit());
			
			root.getChildren().add(label);
			root.getChildren().add(button);
			
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("AppMain");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
