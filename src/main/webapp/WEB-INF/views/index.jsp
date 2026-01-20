<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Shortly - URL Shortener</title>

    <!-- Bootstrap CSS -->
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        crossorigin="anonymous"
    >

    <style>
        body {
            min-height: 100vh;
            display: flex;
            align-items: center;
            background: linear-gradient(135deg, #f8f9fa, #e9ecef);
        }

        .brand {
            font-weight: 700;
            letter-spacing: -0.5px;
        }

        .subtitle {
            color: #6c757d;
            font-size: 0.95rem;
        }

        .result-box {
            word-break: break-all;
        }
    </style>
</head>

<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8">

            <div class="card shadow-sm border-0">
                <div class="card-body p-4">

                    <!-- Header -->
                    <div class="text-center mb-4">
                        <h2 class="brand">Shortly</h2>
                        <p class="subtitle">
                            Shorten long URLs into clean, shareable links
                        </p>
                    </div>

                    <!-- Form -->
                    <form method="post" action="<%= request.getContextPath() %>/shorten">
                        <div class="mb-3">
                            <label for="longUrl" class="form-label fw-semibold">
                                Long URL
                            </label>
                            <input
                                type="url"
                                class="form-control form-control-lg"
                                id="longUrl"
                                name="longUrl"
                                placeholder="https://example.com/some/very/long/url"
                                required
                            >
                        </div>

                        <button
                            type="submit"
                            class="btn btn-primary btn-lg w-100">
                            Shorten URL
                        </button>
                    </form>

                    <!-- Error -->
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger mt-4 mb-0">
                            <%= request.getAttribute("error") %>
                        </div>
                    <% } %>

                    <!-- Result -->
                    <% if (request.getAttribute("shortUrl") != null) { %>
                        <div class="alert alert-success mt-4 mb-0 result-box">
                            <div class="fw-semibold mb-1">Your short URL</div>
                            <a href="<%= request.getAttribute("shortUrl") %>"
                               target="_blank">
                                <%= request.getAttribute("shortUrl") %>
                            </a>
                        </div>
                    <% } %>

                </div>
            </div>

            <!-- Footer -->
            <div class="text-center mt-3 small text-muted">
                Designed and developed by <a href="https://www.linkedin.com/in/venkateshalampally" target="_blank">Venkatesh Alampally</a>
            </div>

        </div>
    </div>
</div>

</body>
</html>
