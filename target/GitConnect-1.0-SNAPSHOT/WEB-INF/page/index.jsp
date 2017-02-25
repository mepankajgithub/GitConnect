<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h1>Git Connect</h1>
	<input type="button" id="Login" onclick="Login()" value="Login with Github">
</body>

<script type="text/javascript">
	function Login() {
		window.location.href = 'https://github.com/login/oauth/authorize?client_id=dd027905d5db92122d02&scope=user%20gist';
	}
</script>
</html>