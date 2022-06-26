package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	// 모든 모듈에서 공유하는 전역 변수(사용자 아이디)
	public static String global_user_id = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		primaryStage.setTitle("로그인 모듈");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}	
