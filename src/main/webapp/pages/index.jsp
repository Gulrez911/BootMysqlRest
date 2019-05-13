<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link href="css/custom.css" type="text/css" rel="stylesheet">
<body>
	<div align="center">

		<form action="save" method="post">
			<table>
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lastName"></td>
				</tr>
				<tr>
					<td>
						<button type="submit">Submit</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>