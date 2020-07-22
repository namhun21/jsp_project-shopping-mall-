<%@page import="product.dto.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="nav.jsp" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
	<!-- Hero Slider Begin -->
    <section class="hero-slider">
        <div class="hero-items owl-carousel">
            <div class="single-slider-item set-bg" data-setbg="img/slider-1.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1>2020</h1>
                            <h2>Lookbook.</h2>
                            <a href="Product?value=All" class="primary-btn">See More</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="single-slider-item set-bg" data-setbg="img/slider-2.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1>2020</h1>
                            <h2>Lookbook.</h2>
                            <a href="Product?value=All" class="primary-btn">See More</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="single-slider-item set-bg" data-setbg="img/slider-3.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1>2020</h1>
                            <h2>Lookbook.</h2>
                            <a href="Product?value=All" class="primary-btn">See More</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Slider End -->

    <!-- Features Section Begin -->
    <section class="features-section spad">
        <div class="features-ads">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="single-features-ads first">
                            <img src="img/icons/f-delivery.png" alt="">
                            <h4>Free shipping</h4>
                            <p>Fusce urna quam, euismod sit amet mollis quis, vestibulum quis velit. Vesti bulum mal
                                esuada aliquet libero viverra cursus. </p>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="single-features-ads second">
                            <img src="img/icons/coin.png" alt="">
                            <h4>100% Money back </h4>
                            <p>Urna quam, euismod sit amet mollis quis, vestibulum quis velit. Vesti bulum mal esuada
                                aliquet libero viverra cursus. </p>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="single-features-ads">
                            <img src="img/icons/chat.png" alt="">
                            <h4>Online support 24/7</h4>
                            <p>Urna quam, euismod sit amet mollis quis, vestibulum quis velit. Vesti bulum mal esuada
                                aliquet libero viverra cursus. </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Features Box -->
        
    </section>
    <!-- Features Section End -->
	<%ArrayList<ProductDTO> plist = (ArrayList<ProductDTO>) request.getAttribute("plist");%>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(function onload() {//window.onload - jquery version
			var curPage = ${pagination.curPage};
			var page = document.getElementById("section"+curPage);
			page.style.display = "";
			
			var selectedSection = "";
			$("#sort_by").change(function() {
				selectedSection = $("#sort_by").val();
				var url = 'main?sort_by=' + selectedSection;

				window.location.href = url;
			});
			
		});
		
		var curPage = 1;
		
		function goPage(Clickedpage){
			$.ajax({
				url: "ProductPaging",
				type : "post",
				data : {Clickedpage:Clickedpage},
				dataType : "json",
				cache : false,
				success : function(data){
					console.log(data.Clickedpage);
					toClickedpage(data.Clickedpage);
					//$("#data").html("<h1>"+data.name+"</h1>");
				},
				error : function(request,status,error){
					//console.log(data.name);
				}
			})
		}
		
		function toClickedpage(Clickedpage){
			var page = document.getElementById("section"+curPage);
			page.style.display = "none";
			var page = document.getElementById("section"+Clickedpage);
			page.style.display = "";
			curPage = Clickedpage;
			console.log(Clickedpage+ curPage);
		}
		
	</script>
</head>

<body>
    

    <!-- Categories Page Section Begin -->
    <section class="categories-page spad">
        <div class="container">
            <div class="categories-controls">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="categories-filter">
                            <div class="cf-left">
                                <form action="">
                                    <select class="sort" id="sort_by">
                                        <option value="sortby">Sort by</option>
                                        <option value="orders">Orders</option>
                                        <option value="price">Price</option>
                                    </select>
                                </form>
                            </div>
                            <div class="cf-right">
                           		<!-- private int curPage; // 현재 페이지
								private int totalPage; // 전체 페이지 개수
								private int totalContentCnt; // 전체 게시물 개수
								private int contentCnt = 5; // 한페이지에 표시할 게시물 개수
								private int pageCnt = 3; // 보여줄 페이지 개수
								private boolean prev; // 이전 페이지 버튼
								private boolean next; // 다음 페이지 버튼
								private int curBlock; // 현재 페이지 블록
								private int lastBlock; // 마지막 페이지 블록
								private int startPage; // 현재 페이지 블록의 시작 페이지
								private int endPage; // 현재 페이지 블록의 마지막 페이지 -->
                              <span><%=plist.size() %> Products</span>
                                <c:forEach var="i" begin="${pagination.startPage }" end="${pagination.endPage }">
                                	<c:choose>
                                		<c:when test="${pagination.curPage eq i }">
                                			<button type="button" class="btn btn-light" onclick="goPage(${i})">${pagination.curPage }</button>
                                		</c:when>
                                		<c:otherwise>
                                			<button type="button" class="btn btn-light" onclick="goPage(${i})">${i}</button>
                                		</c:otherwise>
                                	</c:choose>
                                </c:forEach> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            
            <c:forEach var="j" begin="0" end="${fn:length(plist)/9}">
            <div id="section${j+1 }" style="display: none">
            	<c:set var="i" value="${(j)*9 }"></c:set>
            	<c:if test="${i lt fn:length(plist)}">
            	<div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="single-product-item">
                        <figure>
                            <img src="${plist[i].product_img}" alt="">
                            <div class="p-status new">new</div>
                            <div class="hover-icon">
                                <a href="${plist[i].product_img}" class="pop-up"><img src="img/icons/zoom-plus.png"
                                        alt=""></a>
                            </div>
                        </figure>
                        <div class="product-text">
                          	<a href="ProductDetail?pid=${plist[i].pid}">
                                <h6>${plist[i].pname} test</h6>
                            </a>
                            <p> ${plist[i].price}원</p>
                        </div>
                    </div>
                </div>
	            <div class="col-lg-6 col-md-6">
	                <div class="row">
			            <c:forEach var="index" begin="${i+1 }" end="${i+4 }">
				            <c:if test="${index lt fn:length(plist)}">
		                        <div class="col-lg-6 col-md-6">
		                            <div class="single-product-item">
		                                <figure>
		                                    <img src="${plist[index].product_img}" alt="">
		                                    <div class="p-status sale">sale</div>
		                                    <div class="hover-icon">
		                                    <a href= "${plist[index].product_img}" class="pop-up"><img
		                                                src="img/icons/zoom-plus.png" alt=""></a>
		                                    </div>
		                                </figure>
		                                <div class="product-text">
		                                    <a href="ProductDetail?pid=${plist[index].pid}">
		                                        <h6>${plist[index].pname}</h6>
		                                    </a>
		                                    <p> ${plist[index].price}원</p>
		                                </div>
		                            </div>
		                        </div>
	                        </c:if>
			            </c:forEach>
	                </div>
	            </div>
			    <c:forEach var="index" begin="${i+5 }" end="${i+8 }">
			    	<c:if test="${index lt fn:length(plist)}">
			           	<div class="col-lg-3 col-md-6">
			                <div class="single-product-item">
			                    <figure>
			                        <img src="${plist[index].product_img}" alt="">
			                        <div class="p-status popular">popular</div>
			                        <div class="hover-icon">
			                            <a href="${plist[index].product_img}" class="pop-up"><img src="img/icons/zoom-plus.png"
			                                    alt=""></a>
			                        </div>
			                    </figure>
			                    <div class="product-text">
			                        <a href="ProductDetail?pid=${plist[index].pid}">
			                            <h6>${plist[index].pname}</h6>
			                        </a>
			                        <p>${plist[index].price}원</p>
			                    </div>
			                </div>
		                </div>
	                </c:if>
                </c:forEach>
            </div>
            </c:if>
            </div>
            </c:forEach>
            
            
            <!-- <div class="more-product">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <a href="#" class="primary-btn">Load More</a>
                    </div>
                </div>
            </div> -->
        </div>
    </section>
    <!-- Categories Page Section End -->

    <!-- Footer Section Begin -->
    <footer class="footer-section spad">
        <div class="container">
            <div class="newslatter-form">
                <div class="row">
                    <div class="col-lg-12">
                        <form action="#">
                            <input type="text" placeholder="Your email address">
                            <button type="submit">Subscribe to our newsletter</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="footer-widget">
                <div class="row">
                    <div class="col-lg-3 col-sm-6">
                        <div class="single-footer-widget">
                            <h4>About us</h4>
                            <ul>
                                <li>About Us</li>
                                <li>Community</li>
                                <li>Jobs</li>
                                <li>Shipping</li>
                                <li>Contact Us</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="single-footer-widget">
                            <h4>Customer Care</h4>
                            <ul>
                                <li>Search</li>
                                <li>Privacy Policy</li>
                                <li>2019 Lookbook</li>
                                <li>Shipping & Delivery</li>
                                <li>Gallery</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="single-footer-widget">
                            <h4>Our Services</h4>
                            <ul>
                                <li>Free Shipping</li>
                                <li>Free Returnes</li>
                                <li>Our Franchising</li>
                                <li>Terms and conditions</li>
                                <li>Privacy Policy</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="single-footer-widget">
                            <h4>Information</h4>
                            <ul>
                                <li>Payment methods</li>
                                <li>Times and shipping costs</li>
                                <li>Product Returns</li>
                                <li>Shipping methods</li>
                                <li>Conformity of the products</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="social-links-warp">
			<div class="container">
				<div class="social-links">
					<a href="" class="instagram"><i class="fa fa-instagram"></i><span>instagram</span></a>
					<a href="" class="pinterest"><i class="fa fa-pinterest"></i><span>pinterest</span></a>
					<a href="" class="facebook"><i class="fa fa-facebook"></i><span>facebook</span></a>
					<a href="" class="twitter"><i class="fa fa-twitter"></i><span>twitter</span></a>
					<a href="" class="youtube"><i class="fa fa-youtube"></i><span>youtube</span></a>
					<a href="" class="tumblr"><i class="fa fa-tumblr-square"></i><span>tumblr</span></a>
				</div>
			</div>

<div class="container text-center pt-5">
                <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart color-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
            </div>


		</div>
    </footer>
    <!-- Footer Section End -->

    <!-- Js Plugins -->

    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.magnific-popup.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/main.js"></script>
</body>

</html>