<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Shortly - URL Shortener</title>

    <!-- Bootstrap CSS (CDN) -->
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-VkTXQbN4Z1cdq9VHtV6nRvWmvMRGsiE232zraFMvx6bMpiKFF9volG/GpGhawl+5"
        crossorigin="anonymous"
    >
</head>

<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">

            <div class="card shadow-sm">
                <div class="card-body">

                    <h2 class="text-center mb-4">Shortly</h2>

                    <form method="post" action="<%= request.getContextPath() %>/shorten">
                        <div class="mb-3">
                            <label for="longUrl" class="form-label">Enter a long URL</label>
                            <input type="url"
                                   class="form-control"
                                   id="longUrl"
                                   name="longUrl"
                                   placeholder="https://example.com/some/very/long/url"
                                   required>
                        </div>

                        <button type="submit" class="btn btn-primary w-100">
                            Shorten URL
                        </button>
                    </form>

                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger mt-3">
                            <%= request.getAttribute("error") %>
                        </div>
                    <% } %>

                    <% if (request.getAttribute("shortUrl") != null) { %>
                        <div class="alert alert-success mt-3">
                            Short URL:
                            <a href="<%= request.getAttribute("shortUrl") %>" target="_blank">
                                <%= request.getAttribute("shortUrl") %>
                            </a>
                        </div>
                    <% } %>

                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>
