package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController {
	/*
	üũ�ڽ� : adminCheckBox
	����� ���̵� : useridTextField
	����� ��ȣ : passwordPasswordField
	�α��� ��ư : loginButton
	ȸ������ ��ư : registrationButton
	��� ��ư : cancelButton
	â�ݱ� ��ư : closeButton
	*/
	
	@FXML
	private CheckBox adminCheckBox;
	@FXML
	private TextField useridTextField;
	@FXML
	private PasswordField passwordPasswordField;
	@FXML
	private Button loginButton;
	@FXML
	private Button registrationButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Button closeButton;
	@FXML
	private Label loginMessageLabel;
	// ����� �α��� üũ
	boolean isUserLogin = false;
	
	// â �ݱ� ��ư Ŭ�� �̺�Ʈ
	public void closeButtonOnAction(ActionEvent e) {
		//System.out.println("â �ݱ� ��ư Ŭ�� �̺�Ʈ ����");
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
	public void loginButtonOnAction(ActionEvent e) {
		loginMessageLabel.setText("����� ���̵�� ��ȣ�� �˻��մϴ�...");
		
		if(loginButton.getText().equals("�α���") == true) {
			if(
				useridTextField.getText().isBlank() == false
				&& passwordPasswordField.getText().isBlank() == false
					) { // ���̵�� ��ȣ�� �ԷµǾ� �ִ��� üũ
				if(adminCheckBox.isSelected() == true) { // ������ �α���
					loginMessageLabel.setText("������ �α���...");
					validateAdminLogin();
				} 
			} else { // ���̵�� ��ȣ�� ����
				loginMessageLabel.setText("���̵�� ��ȣ�� �Է����ּ���!");
			}
		} else if(loginButton.getText().equals("�α׾ƿ�") == true) {
			loginMessageLabel.setText("�α׾ƿ�...");
			logout();
		}
	}
	
	public void cancelButtonOnAction(ActionEvent e) {
		adminCheckBox.setSelected(false);
		useridTextField.setText("");
		passwordPasswordField.setText("");
	}
	
	public boolean isAdminLogin() {
		DBConnection connDB = new DBConnection();
		Connection conn = connDB.getConnection();
		
		// ������ �α��� üũ
		String sql = "SELECT admin_id, admin_password"
				+ " FROM admin_accounts"
				+ " WHERE admin_id=?"
				+ " AND admin_password=?";
		// �α��� ���� ����
		boolean result = false;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, useridTextField.getText());
			pstmt.setString(2, passwordPasswordField.getText());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			
			// �˻�
			// SELECT ������ ������ ���̵�� ��ȣ�� ��� ROW�� ã�Ҵ°�?
			if(rs.next()) {
				result = true;
			}
			
			// �Ҹ���
			pstmt.close();
			rs.close();
			conn.close();
		} catch(Exception err) {
		}
		
		return result;
	}
	
	public boolean isMemberLogin() {
		DBConnection connDB = new DBConnection();
		Connection conn = connDB.getConnection();
		
		// ������ �α��� üũ
		String sql = "SELECT user_id, user_password"
				+ " FROM member_accounts"
				+ " WHERE user_id=?"
				+ " AND user_password=?";
		// �α��� ���� ����
		boolean result = false;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, useridTextField.getText());
			pstmt.setString(2, passwordPasswordField.getText());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			
			// �˻�
			// SELECT ������ ������ ���̵�� ��ȣ�� ��� ROW�� ã�Ҵ°�?
			if(rs.next()) {
				result = true;
				isUserLogin = true;
				Main.global_user_id = useridTextField.getText();
			}
			
			// �Ҹ���
			pstmt.close();
			rs.close();
			conn.close();
		} catch(Exception err) {
		}
		
		return result;
	}
	
	void validateAdminLogin() {
		if(isAdminLogin() == true) { // ������ �α��� ����
			loginMessageLabel.setText("������ �α��� ����!! ȯ���մϴ�^^");
			useridTextField.setText("");
			passwordPasswordField.setText("");
			registrationButton.setDisable(false);
			loginButton.setText("�α׾ƿ�");
		} else { // ������ �α��� ����
			loginMessageLabel.setText("������ ���̵� �Ǵ� ��ȣ�� �߸��ƽ��ϴ�!");
		}
	}
	
	void logout() {
		loginButton.setText("�α���");
		registrationButton.setText("ȸ������");
		adminCheckBox.setSelected(false);
		isUserLogin = false;
		Main.global_user_id = null;
	}
}























