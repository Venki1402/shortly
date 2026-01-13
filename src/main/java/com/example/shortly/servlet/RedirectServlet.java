package com.example.shortly.servlet;

import com.example.shortly.service.UrlService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Handles redirection from short URL to original URL.
 */
@WebServlet(name = "RedirectServlet", urlPatterns = "/*")
public class RedirectServlet extends HttpServlet {

    private final UrlService urlService = new UrlService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path == null || path.length() <= 1) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String shortCode = path.substring(1);

        Optional<String> originalUrl = urlService.resolveUrl(shortCode);

        if (originalUrl.isPresent()) {
            response.sendRedirect(originalUrl.get());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid short URL");
        }
    }
}
