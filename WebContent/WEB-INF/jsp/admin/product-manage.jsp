<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="product.dao.ProductDAO"%>
<%@ page import="product.dto.ProductDTO" %>
<%@ page import="pagelist.vo.ProductPageList" %>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <%@include file = "../../../include/header.jsp" %>
    <title>상품 등록</title>
	
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
	//ProductDAO productDAO = ProductDAO.getInstance();	
	//PageList listAll = productDAO.listAll(currentPage, pageSize, blockSize);
	
	//pageContext.setAttribute("page", listAll,PageContext.SESSION_SCOPE);
	ProductPageList listAll = (ProductPageList)request.getAttribute("page");
	int count = (int)request.getAttribute("count");
%>
<body>
    <%@include file = "../../../include/nav_admin.jsp" %>
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
    <%
    	
    %>
        <!-- Cart Total Page Begin -->
    <section class="cart-total-page spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h3>상품 리스트</h3>
                    (총 레코드 수 : <%= count %>)
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
                                    <th scope="row"><b>${listAll.pname }</b></th>
                                    <td><b>
                                    <c:if test="${listAll.categorycode == '1' }">상의</c:if>
                                    <c:if test="${listAll.categorycode == '2' }">하의</c:if>
                                    <c:if test="${listAll.categorycode == '3' }">신발</c:if>
                                    </b></td>
                                    <td><b>${listAll.price }</b></td>
                                    <td>${listAll.pcontent }</td>
                                    <td>${listAll.product_hit }</td>
                                    <td>${listAll.product_reply_cnt }</td> 
                                    <td><button type="button" class="delete_${listAll.pid}_btn"
											data-pid="${listAll.pid}">삭제</button></td>                                   
                                </tr>    
                                <script type = "text/javascript">
											$(".delete_${listAll.pid}_btn").click(function() {
																var confirm_val = confirm("정말 삭제하시겠습니까?");
																if (confirm_val) {
												
																	var pid = $(this).attr("data-pid");
																	console.log(pid);
																	$.ajax({
																				url : "deleteproduct",
																				type : "post",
																				dataType : "json",
																				async : false,
																				data : {
																					pid : pid
																				},
																				success : function(result) {
																					if (result == 1) {
																						location.href = "productmanage";
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
        <div id="paginationBox" class="mb-3">
				<ul class="pagination">
					<c:if test="${! page.isEmpty() }">
						<!-- 게시판개수가 0이 아니라면 -->



						<c:if test="${page.startPage > 1}">
							<!-- 시작페이지가 1이상 즉 11 21 31 .... -->
							<c:url var="url" value="productmanage">
								<c:param name="currentPage" value="${page.startPage-1}" />
								<c:param name="pageSize" value="${page.pageSize }" />
								<c:param name="blockSize" value="${page.blockSize }" />
							</c:url>
							<li class="page-item"><a class="page-link" href="${url }">이전</a>
						</c:if>
						<c:forEach var="i" begin="${page.startPage }"
							end="${page.endPage }">
							<%-- 현재 페이지는 링크가 생기지 않게 한다. --%>
							<li class="page-item active"><c:if
									test="${i eq page.currentPage }">
									<a class="page-link" href="#">${i}</a>
								</c:if></li>

							<%-- 그 외 페이지는 다시 리턴 하면서 호출 --%>
							<li class="page-item"><c:if test="${i ne page.currentPage }">

									<c:url var="url" value="productmanage">
										<c:param name="currentPage" value="${i}" />
										<c:param name="pageSize" value="${page.pageSize }" />
										<c:param name="blockSize" value="${page.blockSize }" />
									</c:url>
									<a class="page-link" href="${url }">${i }</a>

								</c:if></li>
						</c:forEach>
						<%-- 마지막 페이지 번호가 전체페이지 수보다 적다면 다음 페이지가 존재한다. --%>

						<c:if test="${page.endPage < page.totalPage }">
							<li class="page-item"><c:url var="url" value="productmanage">
									<c:param name="currentPage" value="${page.endPage+1}" />
									<c:param name="pageSize" value="${page.pageSize }" />
									<c:param name="blockSize" value="${page.blockSize }" />
								</c:url> <a class="page-link" href="${url }">다음</a></li>
						</c:if>
					</c:if>
				</ul>
			</div>
     
    </section>
    <!-- Cart Total Page End -->

</body>
</html>