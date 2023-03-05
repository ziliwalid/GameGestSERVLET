package com.example.gamegest.GameServlet;

import com.example.gamegest.GameDAO.GameDAO;
import com.example.gamegest.Model.Game;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class GameServlet extends HttpServlet{

    private GameDAO gameDAO;
    public void init() {
        gameDAO = new GameDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertGame(request, response);
                    break;
                case "/delete":
                    deleteGame(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateGame(request, response);
                    break;
                default:
                    listGame(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listGame(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List <Game> listGame = gameDAO.selectALLGAMES();
        request.setAttribute("listGame", listGame);
        RequestDispatcher dispatcher = request.getRequestDispatcher("game-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("game-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Game existinggame = gameDAO.selectGame(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("game-form.jsp");
        request.setAttribute("game", existinggame);
        dispatcher.forward(request, response);
    }

    private void insertGame(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String genre = request.getParameter("genre");
        String platform= request.getParameter("platform");
        boolean completion_status= Boolean.parseBoolean(request.getParameter("completion_status"));

        Game newGame = new Game(name,genre,platform,completion_status);
        gameDAO.insertGame(newGame);
        response.sendRedirect("list");
    }

    private void updateGame(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String genre = request.getParameter("genre");
        String platform= request.getParameter("platform");
        boolean completion_status= Boolean.parseBoolean(request.getParameter("completion_status"));

        Game game = new Game(id, name, genre,platform,completion_status);
        gameDAO.updateGame(game);
        response.sendRedirect("list");
    }

    private void deleteGame(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        gameDAO.deleteGame(id);
        response.sendRedirect("list");

    }





}
