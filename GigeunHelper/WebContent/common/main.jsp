<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<style>
.carousel-item > img{
width:1000px;
}

</style>
</head>
<jsp:include page="header.jsp"/>
	<div style="max-height:3000px;"class="container-fluid">

    <div class="row">

      <!-- /.col-lg-3 -->

      <div class="col-lg-9">

        <div style="max-width:3000px;"id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          </ol>
          <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
             	 <img class="rounded mx-auto d-block img-fluid" src="./image/image1/6.jpg" alt="First slide" style="cursor:pointer"  onclick="location.href='productList.pd'">
 				 <div class="carousel-caption">
 				 </div>	            	
            </div>
            <div class="carousel-item">
              <img class="rounded mx-auto d-block img-fluid" src="./image/image1/3.jpeg" alt="Second slide" style="cursor:pointer" onclick="location.href='productList.pd'">
            	 <div class="carousel-caption">
 				 </div>	 
            </div>
            <div  class="carousel-item">
              <img class="rounded mx-auto d-block img-fluid" src="./image/image1/1.jpg" alt="Third slide" style="cursor:pointer" onclick="location.href='productList.pd'">
            	 <div class="carousel-caption">
 				 </div>
            </div>
            
  
          </div>
         
	          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
	            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	            <span class="sr-only">이전</span>
	          </a>
	          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button"  data-slide="next">
	            <span  class="carousel-control-next-icon" aria-hidden="true"></span>
	            <span class="sr-only">다음</span>
	          </a>
         
        </div>
        <div id=wrap style="width:1112px;">
  			
				
				<div class="row justify-content-center mt-5 mb-2">

					<h5 colspan=2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Today Free
						Photo&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h5>

				</div>
				<div style="height: 100px; font-size: 60px;"
					class="row justify-content-center " id="countdown"></div>
				<div class="row justify-content-center mt-2"></div>
				<div class="row">
					<c:choose>
						<c:when test="${empty list}">
							<h1>오늘의 행사상품은 없습니다</h1>
						</c:when>
						<c:otherwise>
							<c:forEach var="d" items="${list}">
								<div class="col-lg-4 col-md-6 mb-4">
									<div class="card h-100">
										<a href="photo_detail.ph?product_num=${d.product_num}&member_num=${sessionScope.member.member_num}"><img class="card-img-top"
											src="upload/${d.product_origin_file}" alt=""></a>
										<!-- <div class="card-body">
											<h4 class="card-title">
												<a href="#">Item One</a>
											</h4>
											<h5>$24.99</h5>
											<p class="card-text">Lorem ipsum dolor sit amet,
												consectetur adipisicing elit. Amet numquam aspernatur!</p>
										</div>
										<div class="card-footer">
											<small class="text-muted">&#9733; &#9733; &#9733;
												&#9733; &#9734;</small>
										</div> -->
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>

					<!-- <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./image/image1/4.jpg" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Item One</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./image/image1/3.jpg" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Item Two</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor sit amet.</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./image/image1/6.jpg" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Item Three</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./image/image1/7.jpg" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Item Four</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./image/image1/8.jpg" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Item Five</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor sit amet.</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./image/image1/9.jpg" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Item Six</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>

            </div>
          </div> -->




					</div>
				<!-- /.row -->

				
			<!-- /.col-lg-9 -->
			</div>
		</div>
		<!-- /.row -->
	</div>
</div>
<script>
	// Set the date we're counting down to
	var today = new Date();
	var day = today.getDate()+1;
	var month = today.getMonth()+1;
	var year = today.getFullYear();
	
	var date = month+" "+day+", "+year;
	console.log(date);
	
	
	
	var countDownDate = new Date(date+" 00:00:00").getTime();

	// Update the count down every 1 second
	var x = setInterval(function() {

		// Get today's date and time
		var now = new Date().getTime();

		// Find the distance between now and the count down date
		var distance = countDownDate - now;

		// Time calculations for days, hours, minutes and seconds
		var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		var hours = Math.floor((distance % (1000 * 60 * 60 * 24))
				/ (1000 * 60 * 60));
		var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		var seconds = Math.floor((distance % (1000 * 60)) / 1000);

		// Output the result in an element with id="demo"
		document.getElementById("countdown").innerHTML = days + "" + hours
				+ "&nbsp: &nbsp;" + minutes + "&nbsp: &nbsp;" + seconds
				+ "&nbsp &nbsp;";

		// If the count down is over, write some text 
		if (distance < 0) {
		  clearInterval(x);
		 day = day+1
		 location.href = "main.pd";
		}
	}, 1000);
</script>



<jsp:include page="footer.jsp" />