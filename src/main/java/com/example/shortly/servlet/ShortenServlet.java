package com.example.shortly.servlet;

import com.example.shortly.service.UrlService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles URL shortening requests.
 */
@WebServlet(name = "ShortenServlet", urlPatterns = "/shorten")
public class ShortenServlet extends HttpServlet {

    private final UrlService urlService = new UrlService();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String longUrl = request.getParameter("longUrl");

        try {
            String shortCode = urlService.shortenUrl(longUrl);

            String shortUrl = request.getRequestURL()
                    .toString()
                    .replace("/shorten", "/r/" + shortCode);

            request.setAttribute("shortUrl", shortUrl);

        } catch (IllegalArgumentException ex) {
            request.setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
        dispatcher.forward(request, response);
    }
}
