package de.breitenbach;

import java.sql.*;

/**
 * Created by nerix on 29.01.17.
 */
public class iHealthBloodPressureToDB
{

    static void insertBP (iHealthBloodPressureData bp)
    {
        try
        {
            Connection con = DriverManager.getConnection
                    ("jdbc:mariadb://localhost:3306/ehealth", "root", "mar44br89");
            PreparedStatement psInsertBP = con.prepareStatement(
                    "INSERT INTO `blutdruck` (`User-ID`, `Timestamp`, `Systole`,`Diastole`, `Puls`) VALUES (?, FROM_UNIXTIME(?), ?, ?,?);");
            psInsertBP.setInt(1,3);
            psInsertBP.setLong(2,bp.MDate);
            psInsertBP.setInt(3,bp.HP);
            psInsertBP.setInt(4, bp.LP);
            psInsertBP.setInt(5, bp.HR);
            psInsertBP.execute();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    static void selectBP()
    {
        try
        {
            Connection con = DriverManager.getConnection
                    ("jdbc:mariadb://localhost:3306/ehealth", "root", "mar44br89");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `blutdruck`.`ID`, `user`.`Nickname`, `Timestamp`, `Systole`, " +
                    "`Diastole`, `Puls` FROM `blutdruck` join `user` on `blutdruck`.`User-ID` = `user`.`id`");

            while (rs.next())
            {
                System.out.printf( "ID: %s; User: %s; Timestamp: %s; Systole: %s; Diastole: %s; Puls: %s\n",
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs
                                .getString(6));

            }
            rs.close();
            stmt.close();


        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
