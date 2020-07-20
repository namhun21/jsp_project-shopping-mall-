<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <%@ include file = "header.jsp" %>
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

<body>
    <!-- Page Preloder -->
   
    
   <%@ include file = "nav_admin.jsp" %>
	<!-- Page Add Section Begin -->
    <section class="page-add">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="page-breadcrumb">
                        <h2>상품 등록<span>.</span></h2>
                    </div>
                </div>
                <div class="col-lg-8">
                    <img src="img/add.jpg" alt="">
                </div>
            </div>
        </div>
    </section>
    <!-- Page Add Section End -->

    <!-- Cart Total Page Begin -->
    <section class="cart-total-page spad">
        <div class="container">
            <form action="productregistprocess" method = "post" class="checkout-form" enctype ="multipart/form-data">
                <div class="row">
                    <div class="col-lg-12">
                        <h3>상품 정보</h3>
                    </div>
                    <div class="col-lg-8">
                        <div class="row">
                            <div class="col-lg-2">
                                <p class="in-name">Name*</p>
                            </div>
                            <div class="col-lg-9">
                                <input type="text" placeholder="상품명*" name = "pName">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-2">
                                <p class="in-name">상품 설명*</p>
                            </div>
                            <div class="col-lg-9">
                                <input type="text" name = "pContent">
                            
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-2">
                                <p class="in-name">카테고리*</p>
                            </div>
                            <div class="col-lg-9">
                                <select class="cart-select country-usa" name = "categoryCode">
                                    <option value = "1" >상의</option>
                                    <option value = "2">하의</option>
                                    <option value = "3">신발</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-2">
                                <p class="in-name">상품가격*</p>
                            </div>
                            <div class="col-lg-9">
                                <input type="text" name = "price">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-2">
                                <p class="in-name">수량*</p>
                            </div>
                            <div class="col-lg-9">
                                <input type="text" name= "stock">
                            </div>
                        </div>
                        
           
                    </div>
                    <div class="col-lg-3">
                        <div class="order-table">
                            <div class="cart-item">
                                <span>사진 등록</span>
                            </div>
                            <div class = "inputArea" >
                            	<div class="cart-item">
                                <span>이미지</span>
                            </div>
                            	<div id = 'preview'>
                            	
                            	</div>
                            	<br>
                            	<label class = "btn btn-primary btn-file">
                            		사진등록<input type ="file" id = 'upload' name = 'upload' accept = "image/*">
                            		<input type = "hidden" id = 'img_name' name = 'img_name'>
                            	</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="payment-method">
                            <button type="submit">등록하기</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <!-- Cart Total Page End -->
  
</body>

<script type= "text/javascript">
var upload = document.querySelector('#upload');
var preview = document.querySelector('#preview');

upload.addEventListener('change',function (e) {
    var get_file = e.target.files;
	
    //var fileName = $("#upload").val();
    //alert(fileName);
    var image = document.createElement('img');

    /* FileReader 객체 생성 */
    var reader = new FileReader();

    /* reader 시작시 함수 구현 */
    reader.onload = (function (aImg) {
        console.log(1);

        return function (e) {
            console.log(3);
            /* base64 인코딩 된 스트링 데이터 */
            aImg.src = e.target.result
        }
    })(image)

    if(get_file){
        /* 
            get_file[0] 을 읽어서 read 행위가 종료되면 loadend 이벤트가 트리거 되고 
            onload 에 설정했던 return 으로 넘어간다.
            이와 함게 base64 인코딩 된 스트링 데이터가 result 속성에 담겨진다.
        */
        reader.readAsDataURL(get_file[0]);
        console.log(2);
    }

    preview.appendChild(image);
    var fileValue = $("#upload").val().split("\\");
	var fileName = fileValue[fileValue.length-1];
	console.log(fileName);
	
})

    

</script>


</html>