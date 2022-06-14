package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			HBox hbox = new HBox();     // HBox �����̳� ����
			hbox.setPadding(new Insets(10));  // ���� ���� ����
			hbox.setSpacing(10);  // ��Ʈ�� ���� ���� ���� ����

			TextField textField = new TextField();  // TextField ��Ʈ�� ����
			textField.setPrefWidth(200);  // TextField�� �� ����

			Button button = new Button();  // Button ��Ʈ�� ����
			button.setText("Ȯ��");  // Button ���� ����

			ObservableList list = hbox.getChildren();  // HBox�� ObservableList ���
			list.add(textField);
			list.add(button);

			Scene scene = new Scene(hbox);

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