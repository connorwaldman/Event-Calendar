package edu.louisville.cecs.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UsersController
{
    private Connection dbConnection = null;
    private String     fullName     = "";
    
    private Map usersIDMap = null;
    
    public Map getUsersIDMap()
    {
        return usersIDMap;
    }
    public void populateUsersIDMap()
    {
        ResultSet rs = null;
        usersIDMap = new HashMap();
        String template = "SELECT * FROM USERAUTH";
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
            while (rs.next())
            {
                usersIDMap.put(rs.getString("ID"), (rs.getString("FNAME") + " " + rs.getString("MNAME") + " " + rs.getString("LNAME")));
            } // end while
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
    /**
     * 
     */
    public UsersController(Connection dbConnection)
    {
        this.dbConnection = dbConnection;
    }
    public int insertUser(String username, String password)
    {
        int rc = 0;
        String template = "INSERT INTO USERAUTH VALUES(?, ?)";
        try
        {
            PreparedStatement ps = dbConnection.prepareStatement(template);
            ps.setString(1, username);
            ps.setString(2, password);
            rc = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rc);
    }
    public int updateUser(String username, String password)
    {
        int rc = 0;
        String template = "UPDATE USERAUTH SET username= ?, password = ? where username = ? and password = ?";
        try
        {
            PreparedStatement ps = dbConnection.prepareStatement(template);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, username);
            ps.setString(4, password);
            rc = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rc);
    }
    public int deleteUser(String username, String password)
    {
        int rc = 0;
        String template = "DELETE FROM USERAUTH WHERE username=? and password=?";
        try
        {
            System.out.println("About to delete user " + username + " with password " + password);
            PreparedStatement ps = dbConnection.prepareStatement(template);
            ps.setString(1, username);
            ps.setString(2, password);
            rc = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rc);
    }
    public boolean findUser(String username, String password)
    {
        // System.out.println("I will look for user");
        boolean rc = false;
        fullName     = "";
        String template = "SELECT * FROM USERAUTH WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'";
        // System.out.println(template);
        try
        {
            Statement s = dbConnection.createStatement();
            ResultSet rs = s.executeQuery(template);
            if (rs.next())
            {
                rc = true;
                setFullName((rs.getString("username")));
            } // end if
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rc);
    }
    public ResultSet getUsersList(String keyword)
    {
        ResultSet rs = null;
        String template = "SELECT * FROM USERAUTH" + " WHERE NAME LIKE '%" + StringUtil.fixSqlFieldValue(keyword) + "%'";
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rs);
    }
    public ResultSet getUsersList()
    {
        ResultSet rs = null;
        String template = "SELECT * FROM USERAUTH";
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rs);
    }
    public ResultSet getUserRecord(String username, String password)
    {
        ResultSet rs = null;
        String template = "SELECT * FROM USERAUTH WHERE ID= '" + username + "' AND PASSWORD = '" + password + "'";
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } // end try catch
        return (rs);
    }
    public String getFullName()
    {
        return fullName;
    }

}
