<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                    <meta name="author" content="Hỏi Dân IT" />
                    <title>Dashboard - Hỏi Dân IT</title>
                    <link href="/css/styles.css" rel="stylesheet" />
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
                </head>

                <body class="sb-nav-fixed">
                    <jsp:include page="../layout/header.jsp" />
                    <div id="layoutSidenav">
                        <jsp:include page="../layout/sidebar.jsp" />
                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-4">
                                    <h1 class="mt-4">Manage Orders</h1>
                                    <ol class="breadcrumb mb-4">
                                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                        <li class="breadcrumb-item active">Products</li>
                                    </ol>
                                    <div>
                                        <div class=" mt-5">
                                            <div class="row">
                                                <div class="col-12 mx-auto">
                                                    <div class="d-flex justify-content-between">
                                                        <h3>Product detail with id: ${id}</h3>

                                                    </div>
                                                    <hr />

                                                    <table class="table table-hover table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th>Sản phẩm</th>
                                                                <th>Tên</th>
                                                                <th>Giá cả</th>
                                                                <th>Số lượng</th>
                                                                <th>Thành tiền</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="order" items="${order}">
                                                                <tr>
                                                                    <th>${order.id}</th>
                                                                    <td>
                                                                        <p class="mb-0 pe-4">
                                                                            <fmt:formatNumber type="number"
                                                                                value="${order.totalPrice}" /> đ
                                                                        </p>
                                                                    </td>
                                                                    <td>${order.receiverName}</td>
                                                                    <td>${order.status}</td>
                                                                    <td>
                                                                        <a href="/admin/order/${order.id}"
                                                                            class="btn btn-success">View</a>
                                                                        <a href="/admin/order/update/${product.id}"
                                                                            class="btn btn-warning mx-2">Update</a>
                                                                        <a href="/admin/order/delete/${product.id}"
                                                                            class="btn btn-danger">Delete</a>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>

                                                    </table>
                                                </div>

                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </main>
                            <jsp:include page="../layout/footer.jsp" />
                        </div>
                    </div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                        crossorigin="anonymous"></script>
                    <script src="/js/scripts.js"></script>
                </body>

                </html>