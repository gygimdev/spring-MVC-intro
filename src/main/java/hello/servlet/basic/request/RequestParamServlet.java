package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=gim&age=18
 * a. GET방식으로 보내거나
 * b. POST form 을 통해서 보내거나 둘다 꺼낼 수 있다.
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체파라미터 조회]");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + ": " + request.getParameter(paramName)));
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("[중복 파라미터 조회]");
        String[] parameterValues = request.getParameterValues("username");
        for(String name : parameterValues){
            System.out.println("username: " + name);
        }
    }
}
