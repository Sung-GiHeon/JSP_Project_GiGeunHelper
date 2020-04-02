<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
	<div style="max-height:5000px;"class="container">

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
             	 <img class="rounded mx-auto d-block img-fluid" src="./image/image2/2.1.jpg"  alt="First slide">
 				 <div class="carousel-caption">
 				 	<h3 style="color:#FFFFFF;">~박보영~</h3>
 				 	<p style="color:#FFFFFF;">토레타?</p>
 				 	
 				 </div>	            	
            </div>
            <div class="carousel-item">
              <img class="rounded mx-auto d-block img-fluid" src="./image/image2/2.2.jpg" alt="Second slide">
            	 <div class="carousel-caption">
 				 	<h3 style="color:#FFFFFF;">~한가인~</h3>
 				 	<p style="color:#FFBB00;">이쁘다</p>
 				 </div>	 
            </div>
            <div  class="carousel-item">
              <img class="rounded mx-auto d-block img-fluid" src="./image/image2/2.3.jpg" alt="Third slide">
            	 <div class="carousel-caption">
 				 	<h3 style="color:#000000;">~윤아~</h3>
 				 	<p style="color:#FF007F;">♥♥♥♥♥♥♥♥♥♥♥</p>
 				 </div>
 				
 				 <button  type="button" class="btn btn-link btn-lg active" style="color:#FFB2D9;'">♥</button>
				
            </div>
  
          </div>
          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">이전</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">다음</span>
          </a>
        </div>
        
  <div class="container">
    <div class="row justify-content-center mt-5 mb-2">
     
      <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Today Free Photo&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </h5>
      
    </div>
    <div style="height:100px; font-size:60px;"class="row justify-content-center " id="countdown">

    </div>
    <div class="row justify-content-center mt-2">
   
    </div>
           
        

        <div class="row">

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./image/image2/2.4.jpg" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Item One</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">헐Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="./image/image2/2.5.jpg" alt=""></a>
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
              <a href="#"><img class="card-img-top" src="./image/image2/2.6.jpg" alt=""></a>
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
              <a href="#"><img class="card-img-top" src="./image/image2/2.7.jpg" alt=""></a>
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
              <a href="#"><img class="card-img-top" src="./image/image2/2.8.jpg" alt=""></a>
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
              <a href="#"><img class="card-img-top" src="./image/image2/2.9.jpg" alt=""></a>
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
          </div>
		

			
							 	
        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->
	 </div>
	</div>
<script>
// Set the date we're counting down to
var countDownDate = new Date("Dec 18, 2019 15:19:00").getTime();

// Update the count down every 1 second
var x = setInterval(function() {

  // Get today's date and time
  var now = new Date().getTime();
    
  // Find the distance between now and the count down date
  var distance = countDownDate - now;
    
  // Time calculations for days, hours, minutes and seconds
  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    
  // Output the result in an element with id="demo"
  document.getElementById("countdown").innerHTML = days + "" + hours + "&nbsp: &nbsp;"
  + minutes + "&nbsp: &nbsp;" + seconds + "&nbsp &nbsp;";
    
  // If the count down is over, write some text 
  if (distance < 0) {
    clearInterval(x);
    location.href = "main3.jsp";
  }
}, 1000);
</script>

 
	 
<jsp:include page="footer.jsp"/>