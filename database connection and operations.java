Program
import java.sql.*;
public class StudentJDBC {
 public static void main(String[] args) {
 String url = "jdbc:mysql://localhost:3306/college";
 String user = "root";
 String password = "your_password";
 Connection con = null;
 PreparedStatement insert = null;
 PreparedStatement select = null;
 PreparedStatement update = null;
 PreparedStatement delete = null;
 PreparedStatement display = null;
 try {
 // Load MySQL JDBC Driver
 Class.forName("com.mysql.cj.jdbc.Driver");
 // Establish Database Connection
 con = DriverManager.getConnection(url, user, password);
 System.out.println("Database Connected Successfully.");
 // =================================================
 // INSERT OPERATION
 // =================================================
 String insertQuery =
 "INSERT INTO student "
 + "(roll_no, name, department, marks) "
 + "VALUES (?, ?, ?, ?)";
 insert = con.prepareStatement(insertQuery);
 // Insert Student 1
 insert.setInt(1, 101);
 insert.setString(2, "Rahul");
 insert.setString(3, "CSE");
 insert.setInt(4, 90);
 insert.executeUpdate();
 // Insert Student 2
 insert.setInt(1, 102);
 insert.setString(2, "Sneha");
 insert.setString(3, "ISE");
 insert.setInt(4, 91);
 insert.executeUpdate();
 System.out.println("\nRecords Inserted");
 System.out.println("Successfully.");
 // =================================================
 // SELECT / SEARCH OPERATION
 // =================================================
 String selectQuery =
 "SELECT * FROM student WHERE roll_no = ?";
 select = con.prepareStatement(selectQuery);
 // Search student with Roll Number 101
 select.setInt(1, 101);
 ResultSet rs = select.executeQuery();
 System.out.println("\nStudent Details");
 if (rs.next()) {
 System.out.println("\nRoll No : "
 + rs.getInt("roll_no"));
 System.out.println("Name : "
 + rs.getString("name"));
 System.out.println("Department : "
 + rs.getString("department"));
 System.out.println("Marks : "
 + rs.getInt("marks"));
 }
 rs.close();
 // =================================================
 // UPDATE OPERATION
 // =================================================
 String updateQuery =
 "UPDATE student SET marks = ? "
 + "WHERE roll_no = ?";
 update = con.prepareStatement(updateQuery);
 // Update Rahul's marks
 update.setInt(1, 95);
 update.setInt(2, 101);
 int updatedRows = update.executeUpdate();
 if (updatedRows > 0) {
 System.out.println("\nRecord Updated Successfully.");
 }
 // =================================================
 // DELETE OPERATION
 // =================================================
 /*
 * Delete operation is included here.
 * It is commented so that the student record
 * remains available for the final display.
 */
 String deleteQuery =
 "DELETE FROM student WHERE roll_no = ?";
 delete = con.prepareStatement(deleteQuery);
 // Example:
 // delete.setInt(1, 102);
 // delete.executeUpdate();
 // System.out.println("\nRecord Deleted Successfully.");
 // =================================================
 // DISPLAY ALL STUDENT RECORDS
 // =================================================
 String displayQuery =
 "SELECT * FROM student";
 display = con.prepareStatement(displayQuery);
 ResultSet result = display.executeQuery();
 System.out.println("\nStudent Records");
 System.out.println("----------------------------------------");
 System.out.println(
 "Roll\tName\tDepartment\tMarks"
 );
 System.out.println("----------------------------------------");
 while (result.next()) {
 System.out.println(
 result.getInt("roll_no")
 + "\t"
 + result.getString("name")
 + "\t"
 + result.getString("department")
 + "\t\t"
 + result.getInt("marks")
 );
 }
 System.out.println("----------------------------------------");
 result.close();
 }
 catch (ClassNotFoundException e) {
 System.out.println(
 "MySQL JDBC Driver not found."
 );
 e.printStackTrace();
 }
 catch (SQLException e) {
 System.out.println(
 "Database Error."
 );
 e.printStackTrace();
 }
 finally {
 try {
 // Close PreparedStatements
 if (insert != null)
 insert.close();
 if (select != null)
 select.close();
 if (update != null)
