package com.ltc.btl_javafx.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) {
        try {
            // Kiểm tra xem đường dẫn FXML có chính xác không
            System.out.println(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/LoginDesign.fxml"));

            Parent root = FXMLLoader.load(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/LoginDesign.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);

            // Kiểm tra xem đường dẫn hình ảnh có chính xác không
            System.out.println(getClass().getResource("/com/ltc/btl_javafx/imageIcon/icon_miniapartment.png"));
            Support.icon = new Image("/com/ltc/btl_javafx/imageIcon/icon_miniapartment.png");
            primaryStage.getIcons().add(Support.icon);

            primaryStage.setTitle("Phần Mềm Quản Lý Chung Cư Mini - Đăng nhập");
            primaryStage.show();
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
