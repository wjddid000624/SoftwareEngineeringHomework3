package edu.ncsu.csc326.coffeemaker.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ncsu.csc326.coffeemaker.Inventory;
import edu.ncsu.csc326.coffeemaker.Recipe;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

public class InventoryDB {
    public static boolean initInventory(){
        // delete every elements in inventory

        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean InventoryInit = false;

        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("DELETE FROM inventory");
            int deleted = stmt.executeUpdate();
            if(deleted >= 0){
                stmt = conn.prepareStatement("INSERT INTO inventory VALUES (?, ?, ?, ?)");
                stmt.setInt(1, 15);
                stmt.setInt(2, 15);
                stmt.setInt(3, 15);
                stmt.setInt(4, 15);
                int updated = stmt.executeUpdate();
                if(updated == 1){
                    InventoryInit = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, stmt);
        }
        return InventoryInit;
    }

    public static int getInventory(String idx){
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int inventory = 0;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM inventory");
            rs = stmt.executeQuery();

            if(rs.next()){
                inventory = rs.getInt(idx);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            DBConnection.closeConnection(conn, stmt);
        }
        return inventory;
    }

    public static boolean setInventory(String idx, int target){
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean inventorySet = false;

        // set idx th column value to target
        // inventory has only one row

        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("UPDATE inventory SET " + (idx) + "= ?");
            stmt.setInt(1, target);
            int updated = stmt.executeUpdate();
            if(updated == 1) {
                inventorySet = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn, stmt);
        }
        return inventorySet;

    }

}