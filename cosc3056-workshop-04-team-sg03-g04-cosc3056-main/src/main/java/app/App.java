package app;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Main Application Class.
 * <p>
 * Running this class as regular java application will start the 
 * Javalin HTTP Server and our web application.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class App {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/Movies.db";

    public static void main(String[] args) {
        // We will start looking at JDBC by calling a method
        //   to get an ArrayList of titles of all movies in the Databse
        // ArrayList<String> moviesTitles = getMovieTitles();

        // // We can print out all of the movie titles
        // System.out.println("Movie titles:");
        // for (String movie : moviesTitles) {
        //     System.out.println("\t" + movie);
        // }
        // System.out.println("\n");

        // --------------------------------------------------------------------

        // Now as an exercise write the method below getMovieStars()
        //   to get an ArrayList of the names of all movie stars in the database
        ArrayList<String> moviesStars = getMovieStars();
        System.out.println("Movie Stars:");
        for (String starNames : moviesStars) {
            System.out.println("\t" + starNames);
        }
        System.out.println("\n");

        // --------------------------------------------------------------------

        // ** We can encapsulate all of our SQL code in an object!
        // ** To do this, we need to create a JDBCConnection object to talk to the database
        JDBCConnection jdbc = new JDBCConnection();

        // ** With the object we can use it to get the titles of all movies in the database
        // System.out.println("Movie titles (from JDBCConnection):");
        // ArrayList<String> moviesTitlesObject = jdbc.getMovieTitles();
        // for (String movie : moviesTitlesObject) {
        //     System.out.println("\t" + movie);
        // }
        // System.out.println("\n");

        // ** With the power of objects we can use objects to represent a whole movie!
        // ArrayList<Movie> movies = jdbc.getMovies();
        // System.out.println("Movies are (using JDBCConnection and objects):");
        // for (Movie movie : movies) {
        //     System.out.println("\t" + movie.name + " was made in " + movie.year);
        // }
        // System.out.println("\n");


        // --------------------------------------------------------------------


        // TODO: Add your code here for the other queries 
            int a = jdbc.countMovies();
            System.out.println("There are " + a + " movies");

            
        //   Use the (static method or JDBCCOnnection) that you are most comfortable with
        // - Count the number of movies in the database
        // - Get all of the HORROR movies

        // Continue here with the other SQL queries
    }



    /**
     * Get all of the Movie Titles in the database.
     * This methods needs to be "static" since we are calling it from main()
     */
    public static ArrayList<String> getMovieTitles() {
        // Create the ArrayList to return - of Strings for the movie titles
        ArrayList<String> movies = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC database
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM movie";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String movieName     = results.getString("mvtitle");

                // Store the movieName in the ArrayList to return
                movies.add(movieName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movie titles
        return movies;
    }


    /**
     * TODO: Fill in this method as an exercise
     * 
     * Get the names of all Movie Stars in the database
     * This methods needs to be "static" since we are calling it from main()
     */
    public static ArrayList<String> getMovieStars() {
        // Create the ArrayList to return - of Strings for the movie titles
        ArrayList<String> starNames = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC database
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query - TODO: complete this query
            String query = "";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // TODO: Complete this
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movie titles
        return starNames;
    }
}
