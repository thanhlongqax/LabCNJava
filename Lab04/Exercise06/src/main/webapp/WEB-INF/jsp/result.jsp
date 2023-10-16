<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        td{
            padding: 10px;
            border: 1px solid black;
            text-align: center;
        }

        table{
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <table>
        <tbody>
            <%
                if((boolean) request.getAttribute("status")){
                %>
                    <tr>
                        <td>Họ Và Tên</td>
                        <td>
                            <%=request.getAttribute("name")%>
                        </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>
                            <%=request.getAttribute("email")%>
                        </td>
                    </tr>
                    <tr>
                        <td>Ngày sinh</td>
                        <td>
                            <%=request.getAttribute("birthDay")%>
                        </td>
                    </tr>
                    <tr>
                        <td>Giờ sinh</td>
                        <td>
                            <%=request.getAttribute("birthTime")%>
                        </td>
                    </tr>
                    <tr>
                        <td>Giới tính</td>
                        <td>
                            <%=request.getAttribute("gender")%>
                        </td>
                    </tr>
                    <tr>
                        <td>Quốc gia</td>
                        <td>
                            <%=request.getAttribute("country")%>
                        </td>
                    </tr>
                    <tr>
                        <td>IDE yêu thích</td>
                        <td>
                            <%
                                String[] favoriteIde = (String[]) request.getAttribute("favouriteIde");
                                if (favoriteIde != null) {
                                    for (String ide : favoriteIde) {
                                        out.println(ide + "<br>");
                                    }
                                }
                            %>
                        </td>
                    </tr>
                    <tr>
                        <td>Điểm TOEIC</td>
                        <td>
                            <%=request.getAttribute("toeic")%>
                        </td>
                    </tr>
                    <tr>
                        <td>Giới thiệu bản thân</td>
                        <td>
                            <%=request.getAttribute("message")%>
                        </td>
                    </tr>
                <%
                }else{
                    %>
                        <h3>Vui lòng nhập đầy đủ thông tin</h3>
                    <%
                }
            %>
        </tbody>
    </table>
</body>
</html>