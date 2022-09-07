package rikkei.academy.controller;

import rikkei.academy.model.User;
import rikkei.academy.service.IUserService;
import rikkei.academy.service.UserServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    IUserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceIMPL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "permission":
                addUserPermission(request, response);
                break;
            case "test-without-tran":
                testWithoutTran(request, response);
                break;
            default:
                listUser(request, response);

        }
    }

    private void testWithoutTran(HttpServletRequest request, HttpServletResponse response) {
        this.userService.insertUpdateWithoutTransaction();
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userService.selectAllUsers();
        request.setAttribute("userList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }

    private void addUserPermission(HttpServletRequest request, HttpServletResponse response) {
        User user = new User("hung", "hung@gmail.com", "vn");
        int[] permission = {1, 2, 4};
        userService.addUserTransaction(user, permission);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
