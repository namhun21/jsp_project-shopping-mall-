<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@ page import="product.dao.ProductDAO"%>
<%@ page import="product.vo.ProductVO" %>
<%@ page import="pagelist.vo.PageList" %>
<%@ include file = "pagelist.jsp" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Yoga Studio Template">
    <meta name="keywords" content="Yoga, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>상품 등록</title>
	
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400,500,600,700,800,900&display=swap"
        rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
	<!--  <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
    -->
	<style>
	.photoFrame{
      width: 300px;
      height: 300px;
    }
    .photoFrame:hover{
      cursor: pointer;
    }
	.btn-file{
            position: relative;
            overflow: hidden;
        }
        .btn-file input[type=file] {
            position: absolute;
            top: 0;
                right: 0;
            min-width: 100%;
            min-height: 100%;
            font-size: 100px;
            text-align: right;
            filter: alpha(opacity=0);
            opacity: 0;
            outline: none;
            background: white;
            cursor: inherit;
            display: block;
        }
	</style>
</head>
<%
	ProductDAO productDAO = ProductDAO.getInstance();
	PageList listAll = productDAO.listAll(currentPage, pageSize, blockSize);
	pageContext.setAttribute("page", listAll);
%>
<body>
    <!-- Page Preloder -->
   
    
    <!-- Search model -->
	<div class="search-model">
		<div class="h-100 d-flex align-items-center justify-content-center">
			<div class="search-close-switch">+</div>
			<form class="search-model-form">
				<input type="text" id="search-input" placeholder="Search here.....">
			</form>
		</div>
	</div>
	<!-- Search model end -->

    <!-- Header Section Begin -->
    <header class="header-section">
        <div class="container-fluid">
            <div class="inner-header">
                <div class="logo">
                    <a href="./index.html"><img src="img/logo.png" alt=""></a>
                </div>
                <div class="header-right">
                    <img src="img/icons/search.png" alt="" class="search-trigger">
                    <img src="img/icons/man.png" alt="">
                    <a href="#">
                        <img src="img/icons/bag.png" alt="">
                        <span>2</span>
                    </a>
                </div>
                <div class="user-access">
                    <a href="#">Register</a>
                    <a href="#" class="in">Sign in</a>
                </div>
                <nav class="main-menu mobile-menu">
                    <ul>
                        <li><a href="productmanage">상품관리</a></li>
                        <li><a href="productregist">상품등록</a></li>
                        <li><a href="#">사용자관리</a></li>
                        <li><a href="#">주문관리</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>
    <!-- Header Info Begin -->
    <div class="header-info">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <div class="header-item">
                        <img src="img/icons/delivery.png" alt="">
                        <p>Free shipping on orders over $30 in USA</p>
                    </div>
                </div>
                <div class="col-md-4 text-left text-lg-center">
                    <div class="header-item">
                        <img src="img/icons/voucher.png" alt="">
                        <p>20% Student Discount</p>
                    </div>
                </div>
                <div class="col-md-4 text-left text-xl-right">
                    <div class="header-item">
                    <img src="img/icons/sales.png" alt="">
                    <p>30% off on dresses. Use code: 30OFF</p>
                </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Header Info End -->
    <!-- Header End -->
	<!-- Page Add Section Begin -->
    <section class="page-add">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="page-breadcrumb">
                        <h2>환영합니다. 관리자님<span>.</span></h2>
                    </div>
                </div>
                
            </div>
        </div>
    </section>
    <!-- Page Add Section End -->
    
        <!-- Cart Total Page Begin -->
    <section class="cart-total-page spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h3>상품 리스트</h3>
                </div>
                <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">상품명</th>
                                    <th scope="col">카테고리</th>
                                    <th scope="col">상품가격</th>
                                    <th scope="col">상품설명</th>
                                    <th scope="col">추천수</th>
                                    <th scope="col">댓글수</th>
                                    <th scope="col">삭제</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="listAll" items="${page.list}">
                            	<tr>
                                    <th scope="row"><b>${listAll.pid }</b></th>
                                    <td><b>
                                    <c:if test="${listAll.categorycode eq 1 }">상의</c:if>
                                    <c:if test="${listAll.categorycode eq 2 }">하의</c:if>
                                    <c:if test="${listAll.categorycode eq 3 }">신빌</c:if>
                                    </b></td>
                                    <td><b>${listAll.price }</b></td>
                                    <td>${listAll.pcontent }</td>
                                    <td>${listAll.product_hit }</td>
                                    <td>${listAll.product_reply_cnt }</td>
                                    <!-- 
                                    <td><button type="button" class="delete_${listAll.pid}_btn"
											data-gdsNum="${listAll.pid}">삭제</button></td>
									<c:url var="url" value="../admin/bootReSell.jsp">
										<c:param name="gdsNum" value="${listAll.gdsNum}" />
										<c:param name="gdsEndDate" value="${listAll.gdsEndDate }"/>
										<c:param name="gdsStock" value="${listAll.gdsStock }"/>
		
									</c:url>
									-->
                                </tr>
                                <script>
											$(".delete_${listAll.gdsNum}_btn").click(function() {
																var confirm_val = confirm("정말 삭제하시겠습니까?");
																if (confirm_val) {
												
																	var gdsNum = $(this).attr("data-gdsNum");
																	console.log(gdsNum);
																	$.ajax({
																				url : "../DeleteGoodsAjax",
																				type : "post",
																				dataType : "json",
																				async : false,
																				data : {
																					gdsNum : gdsNum
																				},
																				success : function(result) {
																					if (result == 1) {
																						location.href = "../view/admin.jsp";
																					} else {
																						alert("삭제 실패");
																					}
																				},
																				error : function() {
																					alert("삭제를 할 수 없습니다.");
																				}
																			});
																}
															});
										</script>
                            	</c:forEach>
                            </tbody>
                        </table>    
                    
                
            </div>
        </div>
    </section>
    <!-- Cart Total Page End -->

</body>
</html>