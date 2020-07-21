<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="nav.jsp" %>
<%@ include file="header.jsp" %>
<%@ page import="product.dao.ProductDAO"%>
<%@ page import="product.dto.ProductDTO"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>

</head>
<body>
	<!-- Latest Product Begin sql로 top,bottom,shoes로 불러오기-->
    <section class="latest-products spad">
        <div class="container">
            <div class="product-filter">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="section-title">
                            <h2>Products</h2>
                        </div>
                        <% 
	                        ProductDAO pdao = ProductDAO.getInstance();
		            		ArrayList<ProductDTO> list; 
		            		ProductDTO data = new ProductDTO();
		            		String category="";
            			%>
                        <ul class="product-controls">
                            <li><a href="Product?value=All" style="color: gray">All</a></li>
                            <li><a href="Product?value=Top" style="color: gray">Top</a></li>
                            <li><a href="Product?value=Bottom" style="color: gray">Bottom</a></li>
                            <li><a href="Product?value=Shoes" style="color: gray">Shoes</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row" id="product-list">
				<% category = (String)request.getAttribute("a");
				if(category.equals("Top"))
				{
					list = pdao.productSelect(category);
					for(int i=0; i < list.size(); i++){
					data = list.get(i);%>
	                <div class="col-lg-3 col-sm-6 mix all dresses bags">
	                    <div class="single-product-item">
	                        <figure>
	                            <a href="#"><img src = "<%= data.getProduct_img()%>" alt=""></a>
	                            <div class="p-status">new</div>
	                        </figure>
                        	<div class="product-text">
                            	<h6><%=data.getPname() %></h6>
                            	<p><%=data.getPrice() %>원</p>
                        	</div>
                    	</div>
	                </div>
                <%}
				}
				else if(category.equals("Bottom"))
				{
					list = pdao.productSelect(category);
					for(int i=0; i < list.size(); i++){
					data = list.get(i);%>
	                <div class="col-lg-3 col-sm-6 mix all dresses bags">
	                    <div class="single-product-item">
	                        <figure>
	                            <a href="#"><img src = "<%= data.getProduct_img()%>" alt=""></a>
	                            <div class="p-status">new</div>
	                        </figure>
                        	<div class="product-text">
                            	<h6><%=data.getPname() %></h6>
                            	<p><%=data.getPrice() %>원</p>
                        	</div>
                    	</div>
	                </div>
                <%}
				}
				else if(category.equals("Shoes"))
				{
					list = pdao.productSelect(category);
					for(int i=0; i < list.size(); i++){
					data = list.get(i);%>
	                <div class="col-lg-3 col-sm-6 mix all dresses bags">
	                    <div class="single-product-item">
	                        <figure>
	                            <a href="#"><img src = "<%= data.getProduct_img()%>" alt=""></a>
	                            <div class="p-status">new</div>
	                        </figure>
                        	<div class="product-text">
                            	<h6><%=data.getPname() %></h6>
                            	<p><%=data.getPrice() %>원</p>
                        	</div>
                    	</div>
	                </div>
                <%}
				}
				else
				{
					list = pdao.selectProductAll();
					for(int i=0; i < list.size(); i++){
					data = list.get(i);%>
	                <div class="col-lg-3 col-sm-6 mix all dresses bags">
	                    <div class="single-product-item">
	                        <figure>
	                            <a href="#"><img src = "<%= data.getProduct_img()%>" alt=""></a>
	                            <div class="p-status">new</div>
	                        </figure>
                        	<div class="product-text">
                            	<h6><%=data.getPname() %></h6>
                            	<p><%=data.getPrice() %>원</p>
                        	</div>
                    	</div>
	                </div>
                <%}
				}
			%>
            </div>
        </div>
    </section>
    <!-- Latest Product End -->
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