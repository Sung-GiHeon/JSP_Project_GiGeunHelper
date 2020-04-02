<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/redmond/jquery-ui.min.css"/>
</head>
<body>
	<form>
	<input type="text" id="date" size="10"/>
	</form>
	<span id="details"></span>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script>
	$(function() {
	  $("#date").datepicker({
	    dateFormat: 'yy-mm-dd'
	  });
	});
</script>
</body>
</html>