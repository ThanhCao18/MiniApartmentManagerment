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
import com.ltc.btl_javafx.model.Member;

public class DAO_Member implements DAOInterface<Member> {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private static volatile DAO_Member instance;

    private DAO_Member() {
    }

    public static DAO_Member getInstance() {
        if (instance == null) {
            Class var0 = DAO_Tenant.class;
            synchronized(DAO_Tenant.class) {
                if (instance == null) {
                    instance = new DAO_Member();
                }
            }
        }

        return instance;
    }

    public void insertData(Member t, String s, String s1) {
        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query = "INSERT INTO thanhvien (MaTV, MaK, TenTV, GioiTinh, NgaySinh, CCCD, SDT, Que) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            try {
                this.preparedStatement = this.connection.prepareStatement(query);
                this.preparedStatement.setString(1, s);
                this.preparedStatement.setString(2, s1);
                this.preparedStatement.setString(3, t.getName());
                this.preparedStatement.setString(4, t.getSex());
                this.preparedStatement.setDate(5, Date.valueOf(t.getBirthdate()));
                this.preparedStatement.setString(6, t.getCitizenID());
                this.preparedStatement.setString(7, t.getPhoneNum());
                this.preparedStatement.setString(8, t.getPlaceOrigin());
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

    public void deleteData(Member t, String s) {
        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query = "DELETE FROM thanhvien WHERE MaTV = ?";

            try {
                this.preparedStatement = this.connection.prepareStatement(query);
                this.preparedStatement.setString(1, t.getMemberID());
                this.preparedStatement.execute();
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

    }

    public void updateData(Member t, String s, String s1) {
        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query = "UPDATE thanhvien SET TenTV = ?, GioiTinh = ?, NgaySinh = ?, CCCD = ?, SDT = ?, Que = ? WHERE MaTV = ?";

            try {
                this.preparedStatement = this.connection.prepareStatement(query);
                this.preparedStatement.setString(1, t.getName());
                this.preparedStatement.setString(2, t.getSex());
                this.preparedStatement.setDate(3, Date.valueOf(t.getBirthdate()));
                this.preparedStatement.setString(4, t.getCitizenID());
                this.preparedStatement.setString(5, t.getPhoneNum());
                this.preparedStatement.setString(6, t.getPlaceOrigin());
                this.preparedStatement.setString(7, s);
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

    public ObservableList<Member> selectALL() {
        return null;
    }

    public ObservableList<Member> selectByCondition(String condition1, String condition2, String condition3) {
        ObservableList<Member> listMem = FXCollections.observableArrayList();

        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query = "SELECT * FROM thanhvien WHERE MaK = ?";

            try {
                this.preparedStatement = this.connection.prepareStatement(query);
                this.preparedStatement.setString(1, condition1);
                this.resultSet = this.preparedStatement.executeQuery();

                while(this.resultSet.next()) {
                    Member x = new Member(this.resultSet.getString("MaTV"), this.resultSet.getString("TenTV"), this.resultSet.getString("GioiTinh"), this.resultSet.getDate("NgaySinh").toLocalDate(), this.resultSet.getString("CCCD"), this.resultSet.getString("SDT"), this.resultSet.getString("Que"));
                    listMem.add(x);
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

        return listMem;
    }

    public ObservableList<Member> searching(String text, String condition1, String condition2) {
        return null;
    }

    public int selectCount(String condition1) {
        return 0;
    }

    public void deleteAllData(String s1) {
        try {
            this.connection = ConnectionToDatabase.getConnectionDB();
            String query = "DELETE FROM thanhvien WHERE MaK = ?";

            try {
                this.preparedStatement = this.connection.prepareStatement(query);
                this.preparedStatement.setString(1, s1);
                this.preparedStatement.execute();
                ConnectionToDatabase.closeConnection(this.connection);
            } catch (SQLException var17) {
                var17.printStackTrace();
            }
        } catch (ClassNotFoundException var18) {
            var18.printStackTrace();
        } finally {
            if (this.resultSet != null) {
                try {
                    this.resultSet.close();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                }
            }

            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException var15) {
                    var15.printStackTrace();
                }
            }

        }

    }

    public String sumPrice(String condition1, String condition2) {
        return null;
    }

    public String selectID(String s1, String s2) {
        return null;
    }

    public Member selectObject(String s, String s1) {
        return null;
    }

    public void insertDataToTempTable(Member t) {
    }

    public void deleteDataFromTempTable(Member t) {
    }

    public InputStream getReport(String column_name) {
        return null;
    }

    public String selectTurnover(int month, int year) {
        return null;
    }
}

