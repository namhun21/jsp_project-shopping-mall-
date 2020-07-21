<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="nav.jsp" %>
<%@ include file="header.jsp" %>
<%@ page import="cart.dao.CartDAO"%>
<%@ page import="order.dao.OrderDAO"%>
<%@ page import="cart.dto.CartDTO"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html lang="zxx">
<head>
<%ArrayList<CartDTO> clist = (ArrayList<CartDTO>) request.getAttribute("clist");%>
<script type="text/javascript">
   function changecount(){
      var count = document.getElementById("count");
      var cartid = document.getElementById("cartid");
      alert(count.value, cartid.value);
      $.ajax({
         url: "Cart",
         type : "post",
         data : {count:count},
         dataType : "json",
         cache : false,
         success : function(data){
            console.log(data.count);
            //$("#data").html("<h1>"+data.name+"</h1>");
         },
         error : function(request,status,error){
            //console.log(data.name);
         }
      })
   }
   
               
</script>

</head>
<body>
    <!-- Page Add Section Begin -->
    <section class="page-add cart-page-add">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="page-breadcrumb">
                        <h2>Cart<span>.</span></h2>
                        <a href="Home">Home</a>
                        <a href="Category">Category</a>
                        <!--<a class="active" href="#">Night Dresses</a>-->
                    </div>
                </div>
                <div class="col-lg-8">
                    <img src="img/add.jpg" alt="">
                </div>
            </div>
        </div>
    </section>
    <!-- Page Add Section End -->

    
    <!-- Cart Page Section Begin -->
    <div class="cart-page">
        <div class="container">
            <div class="cart-table">
                <table>
                    <thead>
                        <tr>
                            <th class="product-h">Product</th>
                            <th>Price</th>
                            <th class="quan">Quantity</th>
                            <th>Total</th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:set var = "totalprice" value = "0" />
                   
               <c:forEach var="index" begin="${0}" end="${fn:length(clist)-1}" step="1">
               <tbody>
                        <tr>
                           <td class="price-col" hidden = "true" id = "cartid">${clist[index].cartId}</td>
                            <td class="product-col">
                                <img src="${clist[index].product_img}" alt="">
                                <div class="p-title">
                                    <h5>${clist[index].pName}</h5>
                                </div>
                            </td>
                            <td class="price-col">${clist[index].price}원</td>
                            <td class="quantity-col">
                                <div class="pro-qty">
                                   <span class = "dec qtybtn"></span>
                                    <input type="text" value ="${clist[index].pCount}" Id = "count">
                                    <a href = "Cart" onclick="changecount()">수정</a>
                                    <span class = "inc qtybtn"></span>
                                </div>             
                            </td>
                            <td class="total">${clist[index].pCount*clist[index].price}원</td>
                            <c:set var = "totalprice" value = "${totalprice + clist[index].pCount*clist[index].price}" />
                            <td class="product-close">x</td>
                        </tr>
                    </tbody>
                    </c:forEach>
                
                </table>
            </div>            
            <div class="cart-btn">
                <div class="row">
                    <div class="col-lg-6">
                    </div>
                    <div class="col-lg-5 offset-lg-1 text-left text-lg-right">
                        <div class="site-btn clear-btn"><a href = "Cart2?value=delete" onclick="javascript:alert('초기화 되었습니다.');" style="color: black">Clear Cart</a></div>
                        <div class="site-btn update-btn"><a href = "Product" style="color: black">Product</a></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="shopping-method">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="total-info">
                            <div class="total-table">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Total</th>
                                            <th>Shipping</th>
                                            <th class="total-cart">Total Cart</th>
                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                        <tr>
                                            <td class="total">${totalprice}원</td>
                                            <td class="shipping">2500원</td>
                                            <td class="total-cart-p">${totalprice + 2500}원</td>
                                        </tr>
                                    </tbody>
                                    
                                </table>
                            </div>
        
                            <div class="row">
                                <div class="col-lg-12 text-right">
                                    <a href = "Cart2?value=order" onclick="javascript:alert('3D은행 , 113-15694-12-356, 동국이  ${totalprice + 2500}원 입금해주세요~');" class="primary-btn chechout-btn">Proceed to checkout</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Cart Page Section End -->

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