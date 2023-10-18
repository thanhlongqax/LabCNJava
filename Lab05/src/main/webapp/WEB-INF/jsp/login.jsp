<%@ page import="java.util.Arrays"%>
<%
    String value = Arrays.stream(request.getCookies())
            .filter(c -> c.getName().equals("username"))
            .map(Cookie::getValue).findFirst().orElse(null);
    if(value != null){
        response.sendRedirect("/HomeServlet");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <h3 class="text-center text-secondary mt-5 mb-3">User Login</h3>
            <form action="/LoginServlet" method="post" class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input name="username" id="username" type="text" class="form-control" placeholder="Username">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input name="password" id="password" type="password" class="form-control" placeholder="Password">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success px-5">Login</button>
                </div>
                <div class="form-group">
                    <p>Forgot password? <a href="#">Click here</a></p>
                </div>
                <%-- Thông báo cho trường hợp người dùng đã đăng nhập --%>
                <% if (value != null) { %>
                <div class="alert alert-success" role="alert">
                    You are already logged in.
                </div>
                <% } %>
                <%-- Thông báo cho trường hợp mật khẩu không đủ dài hoặc email không hợp lệ --%>
                <% if (request.getAttribute("message") != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= request.getAttribute("message").toString() %>
                </div>
                <% } %>
            </form>
        </div>
    </div>
</div>

</body>
</html>
