<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Gist</title>
</head>
<body>
	<div style="border: 1px solid">
		<h1>
			<u>Gists</u>
		</h1>
		<form method="POST" action="show">
			<div>
				<div style="float: left;">
					<label> File Name </label> <input type="text" name = "fileName" value="${gistDetail.gistModel.fileName}" />
				</div>
				<br /> <br />
				<div style="float: left;">
					<label> Description </label> <input type="text"  name = "description" 
						value="${gistDetail.gistModel.description}" />
				</div>
				<br />
				<br />
				<div style="float: left;">
					<input type="submit" value="Submit" />
				</div>
			</div>
		</form>
		<br /> <br />
		<h2>
			<u>Gists Information</u>
		</h2>
		<table width="100%" style="border: 1px dotted" cellpadding="5"
			cellspacing="5">
			<tr>
				<td>Name</td>
				<td>Description</td>
			<tr>
				<c:forEach items="${gistDetail.list}" var="gist">
					<tr>
						<td>${gist.fileName}</td>
						<td>${gist.description}</td>
					</tr>
				</c:forEach>
		</table>
	</div>
</body>
</html>
