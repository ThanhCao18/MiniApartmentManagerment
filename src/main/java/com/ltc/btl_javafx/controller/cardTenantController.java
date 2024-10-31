package com.ltc.btl_javafx.controller;



import com.ltc.btl_javafx.DAO.DAO_Bill;
import com.ltc.btl_javafx.DAO.DAO_Member;
import com.ltc.btl_javafx.DAO.DAO_Room;
import com.ltc.btl_javafx.DAO.DAO_Tenant;
import com.ltc.btl_javafx.application.Support;
import com.ltc.btl_javafx.application.TitleForms;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.ltc.btl_javafx.model.Bill;
import com.ltc.btl_javafx.model.Member;
import com.ltc.btl_javafx.model.Tenant;

public class cardTenantController implements Initializable {
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
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button deleteAllButton;
    @FXML
    private Label getTownIDLabel1;
    @FXML
    private Label getRoomIDLabel1;
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
    private ObservableList<Tenant> tenant;
    private Tenant x;

    public cardTenantController() {
    }

    public Tenant getTenantInfor() {
        this.tenant = FXCollections.observableArrayList();
        this.tenant.addAll(DAO_Tenant.getInstance().selectByCondition(this.getRoomIDLabel1.getText(), this.getTownIDLabel1.getText(), ""));
        Tenant object = (Tenant)this.tenant.get(0);
        return object;
    }

    public void showTenantInfor() {
        this.x = this.getTenantInfor();
        this.TenantIDTextField.setText(this.x.getTenantID());
        this.TenantIDTextField.setEditable(false);
        this.NameTenantTextField.setText(this.x.getName());
        this.BirthTenantDatePicker.setValue(this.x.getBirthdate());
        if ("Nam".equals(this.x.getSex())) {
            this.MaleTenantRadioButton.setSelected(true);
        } else {
            this.FemaleTenantRadioButton.setSelected(true);
        }

        this.IDCitizenTenantTextField.setText(this.x.getCitizenID());
        this.phoneNumTenantTextField.setText(this.x.getPhoneNum());
        this.placeTenantTextField.setText(this.x.getPlaceOrigin());
        this.rentDatePicker.setValue(this.x.getRentDate());
    }

    public boolean checkTenantEmpty() {
        return this.TenantIDTextField.getText().isEmpty() || this.NameTenantTextField.getText().isEmpty() || !this.MaleTenantRadioButton.isSelected() && !this.FemaleTenantRadioButton.isSelected() || this.BirthTenantDatePicker.getValue() == null || this.IDCitizenTenantTextField.getText().isEmpty() || this.phoneNumTenantTextField.getText().isEmpty() || this.placeTenantTextField.getText().isEmpty() || this.rentDatePicker.getValue() == null;
    }

    public void updateTenant() {
        if (this.checkTenantEmpty()) {
            Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI SỬA THÔNG TIN", "Vui lòng điền đầy đủ thông tin!");
        } else {
            String sex = "";
            if (this.MaleTenantRadioButton.isSelected()) {
                sex = this.MaleTenantRadioButton.getText();
            } else {
                sex = this.FemaleTenantRadioButton.getText();
            }

            Tenant tenant = new Tenant(this.TenantIDTextField.getText(), this.NameTenantTextField.getText(), sex, (LocalDate)this.BirthTenantDatePicker.getValue(), this.IDCitizenTenantTextField.getText(), this.phoneNumTenantTextField.getText(), this.placeTenantTextField.getText(), (LocalDate)this.rentDatePicker.getValue());
            DAO_Tenant.getInstance().updateData(tenant, this.TenantIDTextField.getText(), "");
            Support.NoticeAlert(AlertType.INFORMATION, "THÔNG BÁO", (String)null, "Sửa thông tin thành công !");
        }

    }

    @FXML
    public void Back(ActionEvent event) {
        try {
            Support.root = (Parent)FXMLLoader.load(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/TenantDesign.fxml"));
            Support.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Support.scene = new Scene(Support.root);
            Support.stage.setScene(Support.scene);
            Support.stage.setTitle(TitleForms.TitleTenantForm);
            Support.stage.getIcons().add(Support.icon);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    public void Save(ActionEvent event) {
        if (this.TenantTab.isSelected()) {
            this.updateTenant();
        } else if (this.MemberTab.isSelected()) {
            this.updateMember();
        }

    }

    public void clearInformation() {
        this.addButton.setDisable(true);
        this.updateButton.setDisable(true);
        this.updateButton.setDisable(true);
        this.deleteButton.setDisable(true);
        this.deleteAllButton.setDisable(true);
        this.TenantIDTextField.setText("");
        this.TenantIDTextField.setEditable(false);
        this.NameTenantTextField.setText("");
        this.NameTenantTextField.setEditable(false);
        this.MaleTenantRadioButton.setSelected(false);
        this.MaleTenantRadioButton.setDisable(true);
        this.FemaleTenantRadioButton.setSelected(false);
        this.FemaleTenantRadioButton.setDisable(true);
        this.BirthTenantDatePicker.setValue((LocalDate) null);
        this.BirthTenantDatePicker.setDisable(true);
        this.IDCitizenTenantTextField.setText("");
        this.IDCitizenTenantTextField.setEditable(false);
        this.phoneNumTenantTextField.setText("");
        this.phoneNumTenantTextField.setEditable(false);
        this.placeTenantTextField.setText("");
        this.placeTenantTextField.setEditable(false);
        this.rentDatePicker.setValue((LocalDate) null);
        this.rentDatePicker.setDisable(true);
        this.MemberNameTextField.setEditable(false);
        this.MemberMaleRadioButton.setSelected(false);
        this.MemberMaleRadioButton.setDisable(true);
        this.MemberFemaleRadionButton.setSelected(false);
        this.MemberFemaleRadionButton.setDisable(true);
        this.birthMemberDatePicker.setValue((LocalDate) null);
        this.birthMemberDatePicker.setDisable(true);
        this.IDCitizenMemberTextField.setEditable(false);
        this.phoneNumMemberTextField.setEditable(false);
        this.placeMemberTextField.setEditable(false);
    }

    public boolean checkMemberEmpty() {
        return this.MemberNameTextField.getText().isEmpty() || !this.MemberMaleRadioButton.isSelected() && !this.MemberFemaleRadionButton.isSelected() || this.birthMemberDatePicker.getValue() == null || this.placeMemberTextField.getText().isEmpty();
    }

    public void setMemberToTable(ObservableList<Member> list) {
        this.order.setCellValueFactory((cellData) -> {
            return (new SimpleIntegerProperty(list.indexOf(cellData.getValue()) + 1)).asObject();
        });
        this.NameMember_col.setCellValueFactory(new PropertyValueFactory("name"));
        this.MemberSex_col.setCellValueFactory(new PropertyValueFactory("sex"));
        this.birthMember_col.setCellValueFactory(new PropertyValueFactory("birthdate"));
        this.CitizenIDMember_col.setCellValueFactory(new PropertyValueFactory("citizenID"));
        this.phoneNumMember_col.setCellValueFactory(new PropertyValueFactory("phoneNum"));
        this.placeMember_col.setCellValueFactory(new PropertyValueFactory("placeOrigin"));
        this.MemberTableView.setItems(list);
    }

    @FXML
    public void view() {
        int infoData = this.MemberTableView.getSelectionModel().getSelectedIndex();
        if (infoData > -1) {
            this.MemberNameTextField.setText((String)this.NameMember_col.getCellData(infoData));
            if ("Nam".equals(this.MemberSex_col.getCellData(infoData))) {
                this.MemberMaleRadioButton.setSelected(true);
            } else {
                this.MemberFemaleRadionButton.setSelected(true);
            }

            this.birthMemberDatePicker.setValue((LocalDate)this.birthMember_col.getCellData(infoData));
            this.IDCitizenMemberTextField.setText((String)this.CitizenIDMember_col.getCellData(infoData));
            this.phoneNumMemberTextField.setText((String)this.phoneNumMember_col.getCellData(infoData));
            this.placeMemberTextField.setText((String)this.placeMember_col.getCellData(infoData));
        }
    }

    @FXML
    public void addMember() {
        if (this.MemberTab.isSelected()) {
            if (this.checkMemberEmpty()) {
                Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI THÊM THÔNG TIN", "Vui lòng điền đầy đủ thông tin!");
            } else {
                boolean duplicatedMemberCitizenID = false;
                if (Support.currMemberList.size() > 0) {
                    Iterator var3 = Support.currMemberList.iterator();

                    while(var3.hasNext()) {
                        Member x = (Member)var3.next();
                        if (x.getCitizenID().equals(this.IDCitizenMemberTextField.getText()) && !x.getCitizenID().equals("") && !this.IDCitizenMemberTextField.getText().equals("")) {
                            duplicatedMemberCitizenID = true;
                            break;
                        }
                    }
                }

                if (!duplicatedMemberCitizenID) {
                    int count = Support.currMemberList.size() + 1;
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
                } else {
                    Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI THÊM THÔNG TIN", "Căn cước công dân bị trùng !");
                }
            }
        } else {
            Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI CHỨC NĂNG", "Chức năng chỉ dành cho thành viên phòng !");
        }

    }

    @FXML
    public void updateMember() {
        if (this.checkMemberEmpty()) {
            Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI SỬA THÔNG TIN", "Vui lòng chọn thông tin cần sửa trên bảng !");
        } else {
            boolean duplicatedMemberCitizenID = false;
            if (Support.currMemberList.size() > 0) {
                Iterator var3 = Support.currMemberList.iterator();

                Member selection;
                while(var3.hasNext()) {
                    selection = (Member)var3.next();
                    if (selection.getCitizenID().equals(this.IDCitizenMemberTextField.getText()) && !selection.getCitizenID().equals("") && !this.IDCitizenMemberTextField.getText().equals("")) {
                        duplicatedMemberCitizenID = true;
                        break;
                    }
                }

                if (!duplicatedMemberCitizenID) {
                    selection = (Member)this.MemberTableView.getSelectionModel().getSelectedItem();
                    String sex = "";
                    String idMember = selection.getMemberID();
                    if (this.MemberMaleRadioButton.isSelected()) {
                        sex = this.MemberMaleRadioButton.getText();
                    } else {
                        sex = this.MemberFemaleRadionButton.getText();
                    }

                    Member member = new Member(idMember, this.MemberNameTextField.getText(), sex, (LocalDate)this.birthMemberDatePicker.getValue(), this.IDCitizenMemberTextField.getText(), this.phoneNumMemberTextField.getText(), this.placeMemberTextField.getText());
                    DAO_Member.getInstance().updateData(member, idMember, "");
                    Support.currMemberList.clear();
                    Support.currMemberList.addAll(DAO_Member.getInstance().selectByCondition(this.TenantIDTextField.getText(), "", ""));
                    this.MemberTableView.setItems(Support.currMemberList);
                    Support.NoticeAlert(AlertType.INFORMATION, "THÔNG BÁO", (String)null, "Thêm thông tin thành công !");
                } else {
                    Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI SỬA THÔNG TIN", "Căn cước công dân bị trùng !");
                }
            } else {
                Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI SỬA THÔNG TIN", "Danh sách thành viên trống !\nVui lòng thêm thành viên !");
            }
        }

    }

    @FXML
    public void deleteMember() {
        Member selection = (Member)this.MemberTableView.getSelectionModel().getSelectedItem();
        if (selection == null) {
            Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "LỖI XÓA THÔNG TIN", "Không thể xóa thành viên !\nVui lòng chọn thành viên cần xóa trên bảng ");
        } else {
            Support.currMemberList.remove(selection);
            DAO_Member.getInstance().deleteData(selection, "");
            this.MemberTableView.setItems(Support.currMemberList);
            Support.NoticeAlert(AlertType.INFORMATION, "THÔNG BÁO", (String)null, "Xóa thông tin thành công !");
        }

    }

    @FXML
    public void deleteAll() {
        ArrayList<String> listTenantID = new ArrayList();
        Iterator var3 = Support.tenantList.iterator();

        while(var3.hasNext()) {
            Tenant x = (Tenant)var3.next();
            listTenantID.add(x.getTenantID());
        }

        var3 = Support.billList.iterator();

        while(var3.hasNext()) {
            Bill x = (Bill)var3.next();
            if (listTenantID.contains(x.getTenantID())) {
                Support.NoticeAlert(AlertType.ERROR, "THÔNG BÁO", "Không thể xóa phòng", "Phòng đang có hóa đơn");
                return;
            }
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("THÔNG BÁO");
        alert.setHeaderText((String)null);
        alert.setContentText("Bạn có muốn xóa tất cả thông tin của khách thuê phòng ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            DAO_Member.getInstance().deleteAllData(this.TenantIDTextField.getText());
            Support.currMemberList.clear();
            this.MemberTableView.setItems(Support.currMemberList);
            DAO_Tenant.getInstance().deleteData(this.x, this.getRoomIDLabel1.getText());
            Support.tenantList.remove(this.x);
            this.clearInformation();
            Support.NoticeAlert(AlertType.INFORMATION, "THÔNG BÁO", (String)null, "Xóa thông tin thành công !");
        }

    }

    @FXML
    public void OpenHomePageForm(ActionEvent event) {
        try {
            Support.root = (Parent)FXMLLoader.load(this.getClass().getResource("/com/ltc/btl_javafx/designFXML/HomePageDesign.fxml"));
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
        this.getRoomIDLabel1.setText(cardRoomController.idRoom);
        this.getTownIDLabel1.setText(DAO_Room.getInstance().selectID(this.getRoomIDLabel1.getText(), ""));
        this.showTenantInfor();
        Support.currMemberList = FXCollections.observableArrayList();
        Support.currMemberList.addAll(DAO_Member.getInstance().selectByCondition(this.TenantIDTextField.getText(), "", ""));
        Support.billList = FXCollections.observableArrayList();
        Support.billList.addAll(DAO_Bill.getInstance().selectALL());
        this.setMemberToTable(Support.currMemberList);
    }
}

