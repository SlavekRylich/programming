package film_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect 
{
	private Connection conn; 
	
	Database database = new Database();
	
	
	
	public Connect(Database database) {
		this.database=database;
	}
	
	public boolean connect() 
	{ 
       conn= null; 
       try 
       {
              conn = DriverManager.getConnection("jdbc:sqlite:myDB.db");                       
       } 
       catch (SQLException e) 
       { 
            System.out.println(e.getMessage());
            return false;
       }
       return true;
	}
	public void disconnect() 
	{ 
		if (conn != null) 
		{
	       
			try 
			{     
				conn.close();  
			} 
		
			catch (SQLException ex) 
			{ 
				System.out.println(ex.getMessage()); 
			}
		}
	}
	
	public boolean createTable()
	{
	    if (conn==null)
	           return false;
	    
	    String sql = "CREATE TABLE IF NOT EXISTS SQLDAtabase (" +
		           "id integer PRIMARY KEY," +"type varchar(255) NOT NULL,"+
		    		"name varchar(255) NOT NULL,"+"director varchar(255) NOT NULL,"+
		           "year int,"+ "recomAge int,"+"performers varchar(255),"+
		    		"feedback varchar(255)"+ ");";
//	    String sql = "CREATE TABLE IF NOT EXISTS zamestnanci (" +
//	           "id integer PRIMARY KEY," + "jmeno varchar(255) NOT NULL,"+
//	    		"rodneCislo bigint, " + "popis varchar(50), " + "plat real" + ");";
	    try
	    {
	            Statement stmt = conn.createStatement(); 
	            stmt.execute(sql);
	            return true;
	    } 
	    catch (SQLException e) 
	    {
	    	System.out.println(e.getMessage());
	    }
	    return false;
	}
	
	public String getPerformers(Integer item)
	{
		String performers = "";
			for (Human film : database.getDatabaseFilms().get(item).getActors()) 
			{
				performers += new String( film.getFullName() + "," );
			}
		return performers;		
	}
	
	public String getFeedback(Integer item)
	{
		String itemFeedback = "";
			for (Feedback feedback : database.getDatabaseFilms().get(item).getFeedback())
			{
				itemFeedback += new String(feedback.getNumber() + "-" + feedback.getComment()+",");
			}
		return itemFeedback;		
	}
	
	public void insertRecords()
	{
		for (Integer item : database.getDatabaseFilms().keySet()) 
		{
		String sql = "INSERT INTO SQLDAtabase(type,name,director,year,recomAge,performers,feedback) VALUES(?,?,?,?,?,?,?)";
        try 
        {
        	PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, database.getDatabaseFilms().get(item).getType());
			pstmt.setString(2, database.getDatabaseFilms().get(item).getName());
			pstmt.setString(3, database.getDatabaseFilms().get(item).getDirector());
			pstmt.setInt(4, database.getDatabaseFilms().get(item).getYearOfPublication());
			if (database.getDatabaseFilms().get(item).getClass() == Anime.class)
			{
				pstmt.setInt(5, database.getDatabaseFilms().get(item).getAge());
			}
			else
			{
				pstmt.setInt(5, 0);
			}
			pstmt.setString(6, getPerformers(item));
			pstmt.setString(7, getFeedback(item));
			pstmt.executeUpdate();
		} 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
	}
	
	
	public void loadRecordsFromDatabase()
	{
        String sql = "SELECT id, type, name, director, year, recomAge, performers, feedback FROM SQLDAtabase";
        try 
        {
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
             int ID = 0;
             while (rs.next()) 
             {            	
              		String name;
             		String director;
      				String directorName;
      				String directorSurname;
      				String performers;
      				short year;
      				byte age;
      				
      				name = rs.getString("name");
      				director = rs.getString("director");
      				String[] parts = director.split(" ");
      				directorName = parts[0];
      				directorSurname = parts[1];
      				year = rs.getShort("year");
      				if(rs.getString("type").equals("Film"))
      				{
      					ID = database.addFilm(name, year);
      				}
      				else
      				{
      					age = rs.getByte("recomAge");
      					ID = database.addAnime(name, year, age);
      				}
      					
      				performers = rs.getString("performers");     				
     				String[] parts2 = performers.split(",");     				
      				
      				for(int i = 0; i < parts2.length; i++)
     				{
     					String performer=parts2[i];
     					String performerName;
     					String performerSurname;
     					String[] parts3 = performer.split(" ");
     					performerName=parts3[0];
     					performerSurname=parts3[1];     					    					     					
     					database.getProduction(ID).addActor(database.addHuman(performerName, performerSurname), database.getProduction(ID));
     				}
      				database.getProduction(ID).setDirector(database.addHuman(directorName,directorSurname));
      				
      				Production insertFeedback = database.FindByName(rs.getString("name"));
      				
      				
      				String evaluation;
      				evaluation = rs.getString("feedback");
      				String[] parts4 = evaluation.split(",");
      				for(int i = 0; i < parts4.length; i++)
     				{
     					String evaluation2=parts4[i];
     					byte number;
          				String comment;
     					String[] parts5 = evaluation2.split("-");
     					try 
     					{
     					    number = Byte.parseByte(parts5[0]);
     					    comment = parts5[1];
     					    insertFeedback.setFeedback(number, comment);
     					} catch (NumberFormatException e) 
     					{
     					   
     					}
     				}     				     			      				     				
            	 }
            		
             
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
	}
	public void selectAll()
	{
		String sql = "SELECT id, type, name, director, year, recomAge, performers, feedback FROM SQLDAtabase";
        try 
        {
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
             while (rs.next()) 
             {
            		 System.out.println(rs.getInt("id")+  "\t"   +
                			rs.getString("type")+ "\t"+ 
                			rs.getString("name") + "\t" + 
                			rs.getString("director") + "\t" + 
                			rs.getInt("year") + "\t" +                 			
                			rs.getInt("recomAge") + "\t" + 
                			rs.getString("performers") + "\t" + 
                			rs.getString("feedback"));

             }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
	}
	
	public void deleteSQLDatabase() 
	{
        String sql = "DELETE FROM SQLDAtabase";
        
        try 
        {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }

}

