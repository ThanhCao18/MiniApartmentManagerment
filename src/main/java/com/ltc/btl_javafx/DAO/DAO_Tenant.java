package com.ltc.btl_javafx.DAO;

import com.ltc.btl_javafx.database.ConnectionToDatabase;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.ltc.btl_javafx.model.Tenant;

public class DAO_Tenant implements DAOInterface<Tenant> {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private static volatile DAO_Tenant instance;

    private DAO_Tenant() {
    }

    public static DAO_Tenant getInstance() {
        if (instance == null) {
            Class var0 = DAO_Tenant.class;
            synchronized(DAO_Tenant.class) {
                if (instance == null) {
                    instance = new DAO_Tenant();
                }
            }
        }

        return instance;
    }

    public void insertData(Tenant t, String s, String s1) {
        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query1 = "INSERT INTO khachthue (MaK, MaP, MaT, TenK, GioiTinh, NgaySinh, CCCD, SDT, Que, NgayThue) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String query2 = "UPDATE phong SET TrangThai = 1 WHERE MaP = ? AND MaT = ?";

            try {
                this.preparedStatement = this.connection.prepareStatement(query1);
                this.preparedStatement.setString(1, t.getTenantID());
                this.preparedStatement.setString(2, s);
                this.preparedStatement.setString(3, s1);
                this.preparedStatement.setString(4, t.getName());
                this.preparedStatement.setString(5, t.getSex());
                this.preparedStatement.setDate(6, Date.valueOf(t.getBirthdate()));
                this.preparedStatement.setString(7, t.getCitizenID());
                this.preparedStatement.setString(8, t.getPhoneNum());
                this.preparedStatement.setString(9, t.getPlaceOrigin());
                this.preparedStatement.setDate(10, Date.valueOf(t.getRentDate()));
                this.preparedStatement.execute();
                this.preparedStatement = this.connection.prepareStatement(query2);
                this.preparedStatement.setString(1, s);
                this.preparedStatement.setString(2, s1);
                this.preparedStatement.execute();
                ConnectionToDatabase.closeConnection(this.connection);
            } catch (SQLException var20) {
                var20.printStackTrace();
            }
        } catch (ClassNotFoundException var21) {
            var21.printStackTrace();
        } finally {
            if (this.resultSet != null) {
                try {
                    this.resultSet.close();
                } catch (SQLException var19) {
                    var19.printStackTrace();
                }
            }

            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException var18) {
                    var18.printStackTrace();
                }
            }

        }

    }

    public void deleteData(Tenant t, String s) {
        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query1 = "DELETE FROM khachthue WHERE MaK = ?";
            String query2 = "UPDATE phong SET TrangThai = 0 WHERE MaP = ?";

            try {
                this.preparedStatement = this.connection.prepareStatement(query1);
                this.preparedStatement.setString(1, t.getTenantID());
                this.preparedStatement.execute();
                this.preparedStatement = this.connection.prepareStatement(query2);
                this.preparedStatement.setString(1, s);
                this.preparedStatement.execute();
                ConnectionToDatabase.closeConnection(this.connection);
            } catch (SQLException var19) {
                var19.printStackTrace();
            }
        } catch (ClassNotFoundException var20) {
            var20.printStackTrace();
        } finally {
            if (this.resultSet != null) {
                try {
                    this.resultSet.close();
                } catch (SQLException var18) {
                    var18.printStackTrace();
                }
            }

            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException var17) {
                    var17.printStackTrace();
                }
            }

        }

    }

    public void updateData(Tenant t, String s, String s1) {
        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query = "UPDATE khachthue SET MaK = ?, TenK = ?, GioiTinh = ?, NgaySinh = ?, CCCD = ?, SDT = ?, Que = ?, NgayThue = ? WHERE MaK = ?";

            try {
                this.preparedStatement = this.connection.prepareStatement(query);
                this.preparedStatement.setString(1, t.getTenantID());
                this.preparedStatement.setString(2, t.getName());
                this.preparedStatement.setString(3, t.getSex());
                this.preparedStatement.setDate(4, Date.valueOf(t.getBirthdate()));
                this.preparedStatement.setString(5, t.getCitizenID());
                this.preparedStatement.setString(6, t.getPhoneNum());
                this.preparedStatement.setString(7, t.getPlaceOrigin());
                this.preparedStatement.setDate(8, Date.valueOf(t.getRentDate()));
                this.preparedStatement.setString(9, s);
                this.preparedStatement.execute();
                ConnectionToDatabase.closeConnection(this.connection);
            } catch (SQLException var19) {
                var19.printStackTrace();
            }
        } catch (ClassNotFoundException var20) {
            var20.printStackTrace();
        } finally {
            if (this.resultSet != null) {
                try {
                    this.resultSet.close();
                } catch (SQLException var18) {
                    var18.printStackTrace();
                }
            }

            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException var17) {
                    var17.printStackTrace();
                }
            }

        }

    }

    public ObservableList<Tenant> selectALL() {
        ObservableList<Tenant> listTenant = FXCollections.observableArrayList();

        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query = "SELECT * FROM khachthue";

            try {
                this.preparedStatement = this.connection.prepareStatement(query);
                this.resultSet = this.preparedStatement.executeQuery();

                while(this.resultSet.next()) {
                    Tenant x = new Tenant(this.resultSet.getString("MaK"), this.resultSet.getString("TenK"), this.resultSet.getString("GioiTinh"), this.resultSet.getDate("NgaySinh").toLocalDate(), this.resultSet.getString("CCCD"), this.resultSet.getString("SDT"), this.resultSet.getString("Que"), this.resultSet.getDate("NgayThue").toLocalDate());
                    listTenant.add(x);
                }

                ConnectionToDatabase.closeConnection(this.connection);
            } catch (SQLException var18) {
                var18.printStackTrace();
            }
        } catch (ClassNotFoundException var19) {
            var19.printStackTrace();
        } finally {
            if (this.resultSet != null) {
                try {
                    this.resultSet.close();
                } catch (SQLException var17) {
                    var17.printStackTrace();
                }
            }

            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                }
            }

        }

        return listTenant;
    }

    public ObservableList<Tenant> selectByCondition(String condition1, String condition2, String condition3) {
        ObservableList<Tenant> listTenant = FXCollections.observableArrayList();

        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query = "SELECT * FROM khachthue WHERE MaP = ? AND MaT = ?";

            try {
                this.preparedStatement = this.connection.prepareStatement(query);
                this.preparedStatement.setString(1, condition1);
                this.preparedStatement.setString(2, condition2);
                this.resultSet = this.preparedStatement.executeQuery();

                while(this.resultSet.next()) {
                    Tenant x = new Tenant(this.resultSet.getString("MaK"), this.resultSet.getString("TenK"), this.resultSet.getString("GioiTinh"), this.resultSet.getDate("NgaySinh").toLocalDate(), this.resultSet.getString("CCCD"), this.resultSet.getString("SDT"), this.resultSet.getString("Que"), this.resultSet.getDate("NgayThue").toLocalDate());
                    listTenant.add(x);
                }

                ConnectionToDatabase.closeConnection(this.connection);
            } catch (SQLException var21) {
                var21.printStackTrace();
            }
        } catch (ClassNotFoundException var22) {
            var22.printStackTrace();
        } finally {
            if (this.resultSet != null) {
                try {
                    this.resultSet.close();
                } catch (SQLException var20) {
                    var20.printStackTrace();
                }
            }

            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException var19) {
                    var19.printStackTrace();
                }
            }

        }

        return listTenant;
    }

    public int selectCount(String condition1) {
        int num = 0;

        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query = "SELECT COUNT(*) AS Totals FROM (SELECT MaK FROM khachthue UNION ALL SELECT MaK FROM thanhvien) as CM ";

            try {
                this.preparedStatement = this.connection.prepareStatement(query);
                this.resultSet = this.preparedStatement.executeQuery();
                if (this.resultSet.next()) {
                    num = this.resultSet.getInt("Totals");
                }

                ConnectionToDatabase.closeConnection(this.connection);
            } catch (SQLException var18) {
                var18.printStackTrace();
            }
        } catch (ClassNotFoundException var19) {
            var19.printStackTrace();
        } finally {
            if (this.resultSet != null) {
                try {
                    this.resultSet.close();
                } catch (SQLException var17) {
                    var17.printStackTrace();
                }
            }

            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                }
            }

        }

        return num;
    }

    public ObservableList<Tenant> searching(String text, String condition1, String condition2) {
        ObservableList<Tenant> list = FXCollections.observableArrayList();

        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query = "SELECT * FROM khachthue WHERE MaK LIKE ? OR TenK LIKE ? OR GioiTinh LIKE ? OR NgaySinh = ? OR CCCD LIKE ? OR SDT LIKE ? OR Que LIKE ? OR NgayThue = ?";

            try {
                this.preparedStatement = this.connection.prepareStatement(query);
                this.preparedStatement.setString(1, "%" + text + "%");
                this.preparedStatement.setString(2, "%" + text + "%");
                this.preparedStatement.setString(3, "%" + text + "%");
                this.preparedStatement.setString(4, text);
                this.preparedStatement.setString(5, "%" + text + "%");
                this.preparedStatement.setString(6, "%" + text + "%");
                this.preparedStatement.setString(7, "%" + text + "%");
                this.preparedStatement.setString(8, text);
                this.resultSet = this.preparedStatement.executeQuery();
                if (this.resultSet.next()) {
                    Tenant x = new Tenant(this.resultSet.getString("MaK"), this.resultSet.getString("TenK"), this.resultSet.getString("GioiTinh"), this.resultSet.getDate("NgaySinh").toLocalDate(), this.resultSet.getString("CCCD"), this.resultSet.getString("SDT"), this.resultSet.getString("Que"), this.resultSet.getDate("NgayThue").toLocalDate());
                    list.add(x);
                }

                ConnectionToDatabase.closeConnection(this.connection);
            } catch (SQLException var21) {
                var21.printStackTrace();
            }
        } catch (ClassNotFoundException var22) {
            var22.printStackTrace();
        } finally {
            if (this.resultSet != null) {
                try {
                    this.resultSet.close();
                } catch (SQLException var20) {
                    var20.printStackTrace();
                }
            }

            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException var19) {
                    var19.printStackTrace();
                }
            }

        }

        return list;
    }

    public String selectID(String s1, String s2) {
        String ID = "";
        String query = "SELECT MaK FROM khachthue WHERE MaT = ? AND MaP = ?";

        try {
            this.connection = ConnectionToDatabase.getConnectionDB();

            try {
                this.preparedStatement = this.connection.prepareStatement(query);
                this.preparedStatement.setString(1, s1);
                this.preparedStatement.setString(2, s2);
                this.resultSet = this.preparedStatement.executeQuery();
                if (this.resultSet.next()) {
                    ID = this.resultSet.getString("MaK");
                }

                ConnectionToDatabase.closeConnection(this.connection);
            } catch (SQLException var19) {
                var19.printStackTrace();
            }
        } catch (ClassNotFoundException var20) {
            var20.printStackTrace();
        } finally {
            if (this.resultSet != null) {
                try {
                    this.resultSet.close();
                } catch (SQLException var18) {
                    var18.printStackTrace();
                }
            }

            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException var17) {
                    var17.printStackTrace();
                }
            }

        }

        return ID;
    }

    public void deleteAllData(String s1) {
    }

    public String sumPrice(String condition1, String condition2) {
        return null;
    }

    public Tenant selectObject(String s, String s1) {
        return null;
    }

    public void insertDataToTempTable(Tenant t) {
    }

    public void deleteDataFromTempTable(Tenant t) {
    }

    public InputStream getReport(String column_name) {
        return null;
    }

    public String selectTurnover(int month, int year) {
        return null;
    }
}
