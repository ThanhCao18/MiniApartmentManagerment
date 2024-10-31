package com.ltc.btl_javafx.controller;


import com.ltc.btl_javafx.DAO.DAO_Member;
import com.ltc.btl_javafx.DAO.DAO_Room;
import com.ltc.btl_javafx.DAO.DAO_Tenant;
import com.ltc.btl_javafx.application.Support;
import com.ltc.btl_javafx.application.TitleForms;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.ltc.btl_javafx.model.Member;
import com.ltc.btl_javafx.model.Tenant;

public class addTenantController implements Initializable {
    @FXML
    private ToggleGroup sex1;
    @FXML
    private ToggleGroup sex;
    @FXML
    private Button HomeButton;
    @FXML
    private Button RoomButton;
    @FXML
    private Button TenantButton;
    @FXML
    private Button ServiceButton;
    @FXML
    private Button BillButton;
    @FXML
    private Button StatisticsButton;
    @FXML
    private Label accountNameLabel;
    @FXML
    private Button LogoutButton;
    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label getTownIDLabel;
    @FXML
    private Label getRoomIDLabel;
    @FXML
    private DatePicker BirthTenantDatePicker;
    @FXML
    private RadioButton FemaleTenantRadioButton;
    @FXML
    private TextField IDCitizenMemberTextField;
    @FXML
    private TextField IDCitizenTenantTextField;
    @FXML
    private RadioButton MaleTenantRadioButton;
    @FXML
    private RadioButton MemberFemaleRadionButton;
    @FXML
    private RadioButton MemberMaleRadioButton;
    @FXML
    private TextField MemberNameTextField;
    @FXML
    private TextField NameTenantTextField;
    @FXML
    private TextField TenantIDTextField;
    @FXML
    private DatePicker birthMemberDatePicker;
    @FXML
    private TextField phoneNumMemberTextField;
    @FXML
    private TextField phoneNumTenantTextField;
    @FXML
    private TextField placeMemberTextField;
    @FXML
    private TextField placeTenantTextField;
    @FXML
    private DatePicker rentDatePicker;
    @FXML
    private Tab TenantTab;
    @FXML
    private Tab MemberTab;
    @FXML
    private TableView<Member> MemberTableView;
    @FXML
    private TableColumn<Member, Integer> order;
    @FXML
    private TableColumn<Member, String> CitizenIDMember_col;
    @FXML
    private TableColumn<Member, String> MemberSex_col;
    @FXML
    private TableColumn<Member, String> NameMember_col;
    @FXML
    private TableColumn<Member, LocalDate> birthMember_col;
    @FXML
    private TableColumn<Member, String> phoneNumMember_col;
    @FXML
    private TableColumn<Member, String> placeMember_col;
    private boolean checkAddingCompleted;

    public addTenantController() {
    }

    public boolean checkTenantEmpty() {
        return this.TenantIDTextField.getText().isEmpty() || this.NameTenantTextField.getText().isEmpty() || !this.MaleTenantRadioButton.isSelected() && !this.FemaleTenantRadioButton.isSelected() || this.BirthTenantDatePicker.getValue() == null || this.IDCitizenTenantTextField.getText().isEmpty() || this.phoneNumTenantTextField.getText().isEmpty() || this.placeTenantTextField.getText().isEmpty() || this.rentDatePicker.getValue() == null;
    }

    public void lockInfor() {
        this.TenantIDTextField.setEditable(false);
        this.NameTenantTextField.setEditable(false);
        this.MaleTenantRadioButton.setDisable(true);
        this.FemaleTenantRadioButton.setDisable(true);
        this.BirthTenantDatePicker.setDisable(true);
        this.IDCitizenTenantTextField.setEditable(false);
        this.phoneNumTenantTextField.setEditable(false);
        this.placeTenantTextField.setEditable(false);
        this.rentDatePicker.setDisable(true);
    }

    public void addTenant() {
        if (this.checkAddingCompleted) {
            Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", (String)null, "Bạn đã thêm chủ phòng rồi !\nHay sang phần Thành Viên để sử dụng chức năng này !");
        } else if (this.checkTenantEmpty()) {
            Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI THÊM THÔNG TIN", "Vui lòng điền đầy đủ thông tin!");
        } else {
            boolean duplicatedTenantID = false;
            boolean duplicatedTenantCitizenID = false;
            Iterator var4;
            var4 = Support.tenantList.iterator();

            Tenant x1;
            while(var4.hasNext()) {
                x1 = (Tenant)var4.next();
                if (x1.getTenantID().equalsIgnoreCase(this.TenantIDTextField.getText())) {
                    duplicatedTenantID = true;
                    break;
                }
            }

            var4 = Support.tenantList.iterator();

            while(var4.hasNext()) {
                x1 = (Tenant)var4.next();
                if (!x1.getCitizenID().isEmpty() && this.IDCitizenMemberTextField.getText().isEmpty() && x1.getCitizenID().equals(this.IDCitizenTenantTextField.getText())) {
                    duplicatedTenantCitizenID = true;
                    break;
                }
            }

            if (!duplicatedTenantID && !duplicatedTenantCitizenID) {
                String sex = "";
                if (this.MaleTenantRadioButton.isSelected()) {
                    sex = this.MaleTenantRadioButton.getText();
                } else {
                    sex = this.FemaleTenantRadioButton.getText();
                }

                Tenant tenant = new Tenant(this.TenantIDTextField.getText(), this.NameTenantTextField.getText(), sex, (LocalDate)this.BirthTenantDatePicker.getValue(), this.IDCitizenTenantTextField.getText(), this.phoneNumTenantTextField.getText(), this.placeTenantTextField.getText(), (LocalDate)this.rentDatePicker.getValue());
                Support.tenantList.add(tenant);
                DAO_Tenant.getInstance().insertData(tenant, this.getRoomIDLabel.getText(), this.getTownIDLabel.getText());
                this.lockInfor();
                Support.NoticeAlert(AlertType.INFORMATION, "THÔNG BÁO", (String)null, "Thêm thông tin thành công !");
                this.checkAddingCompleted = true;
            } else if (duplicatedTenantID) {
                Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI THÊM THÔNG TIN", "Mã khách bị trùng !\nMã khách không phân biệt chữ hoa và chữ thường\nVui lòng nhập mã khách khác !");
            } else if (duplicatedTenantCitizenID) {
                Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI THÊM THÔNG TIN", "Căn cước công dân bị trùng !");
            }
        }

    }

    public boolean checkMemberEmpty() {
        return this.MemberNameTextField.getText().isEmpty() || !this.MemberMaleRadioButton.isSelected() && !this.MemberFemaleRadionButton.isSelected() || this.birthMemberDatePicker.getValue() == null || this.placeMemberTextField.getText().isEmpty();
    }

    public void addMember() {
        if (this.checkMemberEmpty()) {
            Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI THÊM THÔNG TIN", "Vui lòng điền đầy đủ thông tin!");
        } else {
            int count = 1;
            String sex = "";
            String idMember = "";
            if (this.MemberMaleRadioButton.isSelected()) {
                sex = this.MemberMaleRadioButton.getText();
            } else {
                sex = this.MemberFemaleRadionButton.getText();
            }

            String var10000 = this.TenantIDTextField.getText();
            idMember = var10000 + "TV" + String.valueOf(count++);
            Member member = new Member(idMember, this.MemberNameTextField.getText(), sex, (LocalDate)this.birthMemberDatePicker.getValue(), this.IDCitizenMemberTextField.getText(), this.phoneNumMemberTextField.getText(), this.placeMemberTextField.getText());
            Support.currMemberList.add(member);
            DAO_Member.getInstance().insertData(member, idMember, this.TenantIDTextField.getText());
            this.MemberTableView.setItems(Support.currMemberList);
            Support.NoticeAlert(AlertType.INFORMATION, "THÔNG BÁO", (String)null, "Thêm thông tin thành công !");
        }

    }

    @FXML
    public void Save() {
        if (this.TenantTab.isSelected()) {
            this.addTenant();
            if (this.checkAddingCompleted) {
                this.MemberTab.setDisable(false);
            }
        } else if (this.MemberTab.isSelected()) {
            this.addMember();
        }

    }

    @FXML
    public void Back(ActionEvent event) {
        try {
            Support.root = (Parent)FXMLLoader.load(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/TenantDesign.fxml"));
            Support.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Support.scene = new Scene(Support.root);
            Support.stage.setScene(Support.scene);
            Support.stage.setTitle(TitleForms.TitleHomePageForm);
            Support.stage.show();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    public void OpenHomePageForm(ActionEvent event) {
        try {
            Support.root = (Parent)FXMLLoader.load(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/HomePageDesign.fxml"));
            Support.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Support.scene = new Scene(Support.root);
            Support.stage.setScene(Support.scene);
            Support.stage.show();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    public void OpenRoomForm(ActionEvent event) {
        try {
            Support.root = (Parent)FXMLLoader.load(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/RoomDesign.fxml"));
            Support.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Support.scene = new Scene(Support.root);
            Support.stage.setScene(Support.scene);
            Support.stage.setTitle(TitleForms.TitleRoomForm);
            Support.stage.show();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    public void OpenTenantForm(ActionEvent event) {
        try {
            this.TenantButton.setStyle("-fx-background-color:#137b9e");
            Support.root = (Parent)FXMLLoader.load(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/TenantDesign.fxml"));
            Support.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Support.scene = new Scene(Support.root);
            Support.stage.setScene(Support.scene);
            Support.stage.setTitle(TitleForms.TitleTenantForm);
            Support.stage.show();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    public void OpenServiceForm(ActionEvent event) {
        try {
            Support.root = (Parent)FXMLLoader.load(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/ServiceDesign.fxml"));
            Support.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Support.scene = new Scene(Support.root);
            Support.stage.setScene(Support.scene);
            Support.stage.setTitle(TitleForms.TitleServiceForm);
            Support.stage.show();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    public void OpenBillForm(ActionEvent event) {
        try {
            Support.root = (Parent)FXMLLoader.load(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/BillDesign.fxml"));
            Support.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Support.scene = new Scene(Support.root);
            Support.stage.setScene(Support.scene);
            Support.stage.setTitle(TitleForms.TitleBillForm);
            Support.stage.show();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    public void OpenStatisticsForm(ActionEvent event) {
        try {
            Support.root = (Parent)FXMLLoader.load(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/StatisticsDesign.fxml"));
            Support.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Support.scene = new Scene(Support.root);
            Support.stage.setScene(Support.scene);
            Support.stage.setTitle(TitleForms.TitleStatisticsForm);
            Support.stage.show();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    public void Logout(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("THÔNG BÁO");
        alert.setHeaderText((String)null);
        alert.setContentText("Bạn có muốn đăng xuất ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            ((Stage)this.LogoutButton.getScene().getWindow()).close();

            try {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/LoginDesign.fxml"));
                Parent root = (Parent)loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.getIcons().add(Support.icon);
                stage.setTitle(TitleForms.TitleLoginForm);
                stage.show();
            } catch (IOException var7) {
                var7.printStackTrace();
            }
        }

    }

    public void initialize(URL arg0, ResourceBundle arg1) {
        this.accountNameLabel.setText(Support.NameAccount);
        this.TenantButton.setStyle(Support.colorButton);
        this.checkAddingCompleted = false;
        this.MemberTab.setDisable(true);
        Support.currMemberList = FXCollections.observableArrayList();
        this.getRoomIDLabel.setText(cardRoomController.idRoom);
        this.getTownIDLabel.setText(DAO_Room.getInstance().selectID(this.getRoomIDLabel.getText(), ""));
        this.order.setCellValueFactory((cellData) -> {
            return (new SimpleIntegerProperty(Support.currMemberList.indexOf(cellData.getValue()) + 1)).asObject();
        });
        this.MaleTenantRadioButton.setToggleGroup(this.sex);
        this.FemaleTenantRadioButton.setToggleGroup(this.sex);
        this.MemberFemaleRadionButton.setToggleGroup(this.sex1);
        this.MemberMaleRadioButton.setToggleGroup(this.sex1);
        this.NameMember_col.setCellValueFactory(new PropertyValueFactory("name"));
        this.MemberSex_col.setCellValueFactory(new PropertyValueFactory("sex"));
        this.birthMember_col.setCellValueFactory(new PropertyValueFactory("birthdate"));
        this.CitizenIDMember_col.setCellValueFactory(new PropertyValueFactory("citizenID"));
        this.phoneNumMember_col.setCellValueFactory(new PropertyValueFactory("phoneNum"));
        this.placeMember_col.setCellValueFactory(new PropertyValueFactory("placeOrigin"));
    }
}

