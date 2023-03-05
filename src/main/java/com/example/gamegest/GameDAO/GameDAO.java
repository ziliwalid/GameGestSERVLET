package com.example.gamegest.GameDAO;
import com.example.gamegest.Model.Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {
    private String jdbcURL ="jdbc:mysql://localhost:3306/games";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_GAMES_SQL = "INSERT INTO games" + "  (name, genre, platform,completion_status) VALUES " +
            " (?, ?, ?,?);";;
    private static final String SELECT_ALL_GAMES = "select * from games";
    private static final String DELETE_GAMES_SQL = "delete from games where id = ?;";
    private static final String UPDATE_GAMES_SQL = "UPDATE games SET name= ? , genre= ? ,platform = ? ,completion_status = ? WHERE id = ?;";

    private static final String SELECT_GAME_BY_ID = "select * from games where id =?";




    public GameDAO(){}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    //INSERT
    public void insertGame(Game game) throws SQLException {
        System.out.println(INSERT_GAMES_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GAMES_SQL)) {
            preparedStatement.setString(1, game.getName());
            preparedStatement.setString(2, game.getGenre());
            preparedStatement.setString(3, game.getPlatform());
            preparedStatement.setBoolean(4, game.getCompletion_status());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    //READ

    public List< Game > selectALLGAMES(){
        List<Game> games = new ArrayList<>();

        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GAMES);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String genre = rs.getString("genre");
                String platform = rs.getString("platform");
                boolean completion_status = rs.getBoolean("completion_status");
                games.add(new Game(id,name,genre,platform,completion_status));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return games;

    }
    // SELECT BY ID:
    public Game selectGame(int id) {
        Game game = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GAME_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String genre = rs.getString("genre");
                String platform = rs.getString("platform");
                boolean completion_status = rs.getBoolean("completion_status");
                game=new Game(id,name,genre,platform,completion_status);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return game;
    }


    //UPDATE:
    public boolean updateGame(Game game) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_GAMES_SQL);) {

            statement.setString(1, game.getName());
            statement.setString(2, game.getGenre());
            statement.setString(3, game.getPlatform());
            statement.setBoolean(4, game.getCompletion_status());

            statement.setInt(5, game.getId());

            rowUpdated = statement.executeUpdate() > 0;
            System.out.println(statement);
            System.out.println(rowUpdated);
        }
        return rowUpdated;
    }

    //DELETE
    public boolean deleteGame(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_GAMES_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }




    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}
