<%@ page import="java.util.Arrays" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Models.Product" %>

<%
    String value = Arrays.stream(request.getCookies())
                    .filter(c -> c.getName().equals("username"))
                    .map(Cookie::getValue).findFirst().orElse(null);
    if(value == null){
        response.sendRedirect("/LoginServlet");
    }

    String decodedValue = "";
    if (value != null) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            decodedValue = new String(decoder.decode(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Danh sách sản phẩm</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f8f8f8">

<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Product Management</h3>
        </div>
        <div class="col-md-6 text-right">
            Xin chào <span class="text-danger"><%=decodedValue%></span> | <a href="/LogoutServlet">Logout</a>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Thêm sản phẩm mới</h4>
            <form class="mt-3" method="post" action="/ProductServlet">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input class="form-control" type="text" placeholder="Nhập tên sản phẩm" id="product-name" name="name">
                </div>
                <div class="form-group">
                    <label for="price">Giá sản phẩm</label>
                    <input class="form-control" type="number" placeholder="Nhập giá bán" id="price" name="price">
                </div>
                <div class="form-group">
                    <button class="btn btn-success mr-2" type="submit">Thêm sản phẩm</button>
                </div>

                <div class="alert alert-danger fade">
                    Vui lòng nhập tên sản phẩm
                </div>
            </form>
        </div>
        <div class="col-md-8">
            <h4 class="text-success" >Danh sách sản phẩm</h4>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(int i = 0; i < products.size(); i++){
                        %>
                        <tr>
                            <td><%=(i+1)%></td>
                            <td><a href="#"><%=products.get(i).getName()%></a></td>
                            <td>$<%=products.get(i).getPrice()%></td>
                            <td>
                                <a href="#"  data-toggle="modal" data-target="#editModal" onclick='changeInputValue(<%=products.get(i).getId()%>, `<%=products.get(i).getName()%>`, `<%=products.get(i).getPrice()%>`)'>Chỉnh sửa</a> |
                                <a href="#" onclick='changeDeleteModalId(<%=products.get(i).getId()%>)' data-toggle="modal" data-target="#deleteModal">Xóa</a>
                            </td>
                            </tr>
                        <%
                   }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" tabindex="-1" role="dialog" id="editModal">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Chỉnh sửa sản phẩm</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <form>
            <div class="modal-body">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input class="form-control" type="text" placeholder="Nhập tên sản phẩm" id="edit-product-name" name="name">
                </div>
                <div class="form-group">
                    <label for="price">Giá sản phẩm</label>
                    <input class="form-control" type="number" placeholder="Nhập giá bán" id="edit-product-price" name="price">
                </div>
                <input type="hidden" id="id" name="id" value="">
            </div>
            <div class="modal-footer">
                <button type="button" onclick="updateProduct()" class="btn btn-primary">Lưu thay đổi</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
            </div>
        </form>
      </div>
    </div>
  </div>

  <div class="modal hide" tabindex="-1" role="dialog" id="deleteModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title">Xác nhận xóa</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="modal-body">
            <p>Bạn có chắc là muốn xóa sản phẩm có id: <span id="delete-id"></span></p>
        </div>
        <div class="modal-footer">
            <button type="button" onclick="deleteProduct()" class="btn btn-danger" >Xóa</button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
        </div>
        </div>
    </div>
</div>

<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });

    function deleteProduct(){
        var id = document.getElementById("delete-id").innerText
        $.ajax({
            url: "/ProductServlet",
            method: "delete",
            async: false,
            contentType: "application/json",
            data: JSON.stringify({
                "id": id
            }),
            success: (resp) => {
                if(resp.status)
                    window.location.reload()
            }
        })
    }

    function changeInputValue(oldId, oldName, oldPrice) {
        var id = document.getElementById("id");
        var name = document.getElementById("edit-product-name");
        var price = document.getElementById("edit-product-price");

        id.value = oldId;
        name.value = oldName;
        price.value = oldPrice;
    }

    function updateProduct() {
        var id = document.getElementById("id").value;
        var name = document.getElementById("edit-product-name").value;
        var price = document.getElementById("edit-product-price").value;

        $.ajax({
            url: "/ProductServlet",
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify({
                id: id,
                name: name,
                price: price
            }),
            success: function (resp) {
                if (resp.status) {
                    window.location.reload();
                }
            }
        });
    }

    function changeDeleteModalId(newId){
        var id = document.getElementById("delete-id")

        id.innerText = newId
    }

</script>
</body>
</html>
