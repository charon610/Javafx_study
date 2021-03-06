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
	체크박스 : adminCheckBox
	사용자 아이디 : useridTextField
	사용자 암호 : passwordPasswordField
	로그인 버튼 : loginButton
	회원가입 버튼 : registrationButton
	취소 버튼 : cancelButton
	창닫기 버튼 : closeButton
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
	// 사용자 로그인 체크
	boolean isUserLogin = false;
	
	// 창 닫기 버튼 클릭 이벤트
	public void closeButtonOnAction(ActionEvent e) {
		//System.out.println("창 닫기 버튼 클릭 이벤트 실행");
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
	public void loginButtonOnAction(ActionEvent e) {
		loginMessageLabel.setText("사용자 아이디와 암호를 검사합니다...");
		
		if(loginButton.getText().equals("로그인") == true) {
			if(
				useridTextField.getText().isBlank() == false
				&& passwordPasswordField.getText().isBlank() == false
					) { // 아이디와 암호가 입력되어 있는지 체크
				if(adminCheckBox.isSelected() == true) { // 관리자 로그인
					loginMessageLabel.setText("관리자 로그인...");
					validateAdminLogin();
				} 
			} else { // 아이디와 암호가 없음
				loginMessageLabel.setText("아이디와 암호를 입력해주세요!");
			}
		} else if(loginButton.getText().equals("로그아웃") == true) {
			loginMessageLabel.setText("로그아웃...");
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
		
		// 관리자 로그인 체크
		String sql = "SELECT admin_id, admin_password"
				+ " FROM admin_accounts"
				+ " WHERE admin_id=?"
				+ " AND admin_password=?";
		// 로그인 성공 여부
		boolean result = false;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, useridTextField.getText());
			pstmt.setString(2, passwordPasswordField.getText());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			
			// 검사
			// SELECT 문에서 관리자 아이디와 암호로 디비 ROW를 찾았는가?
			if(rs.next()) {
				result = true;
			}
			
			// 소멸자
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
		
		// 관리자 로그인 체크
		String sql = "SELECT user_id, user_password"
				+ " FROM member_accounts"
				+ " WHERE user_id=?"
				+ " AND user_password=?";
		// 로그인 성공 여부
		boolean result = false;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, useridTextField.getText());
			pstmt.setString(2, passwordPasswordField.getText());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			
			// 검사
			// SELECT 문에서 관리자 아이디와 암호로 디비 ROW를 찾았는가?
			if(rs.next()) {
				result = true;
				isUserLogin = true;
				Main.global_user_id = useridTextField.getText();
			}
			
			// 소멸자
			pstmt.close();
			rs.close();
			conn.close();
		} catch(Exception err) {
		}
		
		return result;
	}
	
	void validateAdminLogin() {
		if(isAdminLogin() == true) { // 관리자 로그인 성공
			loginMessageLabel.setText("관리자 로그인 성공!! 환영합니다^^");
			useridTextField.setText("");
			passwordPasswordField.setText("");
			registrationButton.setDisable(false);
			loginButton.setText("로그아웃");
		} else { // 관리자 로그인 실패
			loginMessageLabel.setText("관리자 아이디 또는 암호가 잘못됐습니다!");
		}
	}
	
	void logout() {
		loginButton.setText("로그인");
		registrationButton.setText("회원가입");
		adminCheckBox.setSelected(false);
		isUserLogin = false;
		Main.global_user_id = null;
	}
}























