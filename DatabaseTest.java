package DatabasePackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

public class DatabaseTest {
	
//  @Test
  public void selectDBdata() throws ClassNotFoundException, SQLException {
	  String query="select * from user";
	  //now we need to call database class in current class. So we need to create object of that class
	  DataBaseClass database=new DataBaseClass();
	  ResultSet data=database.getData(query);
	  System.out.println(data);
	  while(data.next()){
		  /*
		   you can pass database table values for data driven testing using database:-
		   Example of sample login fetching values from login table
		   String username=data.getString("firstname"); //for fetching value of firstname table 
		   String passwd=data.getString("password");  //for fetching value of password table
		    driver.findElement(By.xpath("userfield")).sendKeys(username);
		    driver.findElement(By.xpath("passwordfield")).sendKeys(passwd);
		    driver.findElement(By.xpath("login")).click();
		    or just fetching all values of table data use below code
		   */
		  System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3)
		  +" "+data.getString(4));
	  }
  }
  
  //@Test
  public void insertDBdata() throws ClassNotFoundException, SQLException{ 
	  String query="insert into user values('3','Jenna','Doe','4561')";
	  DataBaseClass database=new DataBaseClass();
	  database.insertData(query);
  }
  
  @Test
  public void updateDBdata() throws ClassNotFoundException, SQLException{ 
	 // String query="update user set salary='1500' where salary='100'";
	  //or  you use below query or you can use user_id in place of salary as reference column
	  String query="update user set firstname='John123' where salary='1500'";
	  DataBaseClass database=new DataBaseClass();
	  database.updateData(query);
  }
}
