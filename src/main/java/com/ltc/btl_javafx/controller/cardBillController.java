package com.ltc.btl_javafx.controller;



import com.ltc.btl_javafx.DAO.DAO_Bill;
import com.ltc.btl_javafx.application.BillReport;
import com.ltc.btl_javafx.application.Support;
import com.ltc.btl_javafx.database.ConnectionToDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import com.ltc.btl_javafx.model.Bill;

public class cardBillController implements Initializable {
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label billPriceLabel;
    @FXML
    private Label HomeTownIDLabel;
    @FXML
    private GridPane formGridPane;
    @FXML
    private DatePicker invoiceDatePicker;
    @FXML
    private Label RoomIDLabel;
    private Map<String, Object> map;
    private ObservableList<Bill> StateBill;
    Connection connection;
    public static boolean checkdeleteBill;

    public cardBillController() {
    }

    public void setData(Bill tmp) {
        this.HomeTownIDLabel.setText(tmp.getHomeTownID());
        this.RoomIDLabel.setText(tmp.getRoomID());
        this.invoiceDatePicker.setValue(tmp.getInvoiceDate());
        BigDecimal num = new BigDecimal(tmp.getBillPrice());
        this.billPriceLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(num));
    }

    public void deleteBillForm(ActionEvent event) {
        this.StateBill.addAll(DAO_Bill.getInstance().selectByCondition(this.HomeTownIDLabel.getText(), this.RoomIDLabel.getText(), (String)null));
        DAO_Bill.getInstance().deleteData((Bill)this.StateBill.get(0), (String)null);
        BillController.lst.remove(this.anchorpane);
        Support.billList.clear();
        Support.billList.addAll(DAO_Bill.getInstance().selectALL());
        checkdeleteBill = true;
    }

    public void PrintPDF(ActionEvent event) throws ClassNotFoundException {
        DAO_Bill.getInstance().deleteDataFromTempTable((Bill)null);
        Bill bill = DAO_Bill.getInstance().selectObject(this.HomeTownIDLabel.getText(), this.RoomIDLabel.getText());
        DAO_Bill.getInstance().insertDataToTempTable(bill);
        this.connection = ConnectionToDatabase.getConnectionDB();
        this.map = new HashMap();
        try {
            InputStream filejasper = new FileInputStream("D:\\BTL_JavaNC\\btl_JavaFX\\src\\main\\java\\com\\ltc\\btl_javafx\\application\\Bill.jasper");
            //BillReport.createReport(this.connection, this.map, DAO_Bill.getInstance().getReport("bill_report"));
            BillReport.createReport(this.connection, this.map, filejasper);
            BillReport.showReport();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void initialize(URL arg0, ResourceBundle arg1) {
        this.StateBill = FXCollections.observableArrayList();
        checkdeleteBill = false;
    }
}

