package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP REQUEST LINE
        printStartLine(request);
        // HTTP HEADER LINE
        printHeaderLine(request);
        // HTTP HEADER UTILS
        printHeaderUtil(request);

    }

    /** HTTP REQUEST LINE **/
    private void printStartLine(HttpServletRequest request){
        System.out.println("::::REQUEST-LINE-START:::::");

        // 1. 메서드 정보 가져오기
        System.out.println("request.getMethod() = " + request.getMethod());
        // 2. 프로토콜 정보 가져오기: HTTP/1.1
        System.out.println("request.getProtocol() = " + request.getProtocol());
        // 3. 스키마 정보 가져오기: HTTP
        System.out.println("request.getScheme() = " + request.getScheme());
        // 4. URL 가져오기: http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // 5. URI 가져오기 request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        // 6. 쿼리스트링 가져오기
        System.out.println("request.getQueryString() = " + request.getQueryString());
        // 7. 안전한지?
        System.out.println("request.isSecure() = " + request.isSecure());
        System.out.println("::::REQUEST-LINE-END:::::");
    }

    /** HTTP HEADER LINE **/
    private void printHeaderLine(HttpServletRequest request){
        System.out.println("::::HEADER-LINE-START:::::");

        /* 옛날 방식
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + headerName);
        }
        */

        request.getHeaderNames().asIterator()
                        .forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));

        System.out.println("::::HEADER-LINE-END:::::");
    }

    /** HTTP HEADER UTILS */
    private void printHeaderUtil(HttpServletRequest request){
        System.out.println("::::HEADER-UTIL-START:::::");

        System.out.println("request.getServerName() = " + request.getServerName());
        System.out.println("request.getServePort() = " + request.getServerPort());

        System.out.println("[언어 정보]");
        request.getLocales().asIterator()
                        .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());

        System.out.println("[쿠키 정보]");
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                System.out.println(cookie.getName() + cookie.getValue());
            }
        }

        System.out.println("[컨탠츠 정보]");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("::::HEADER-UTIL-END:::::");
    }
}
