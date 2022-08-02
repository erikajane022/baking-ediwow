<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New User</title>
</head>
<body>
    <div align="center">
        <h2>New User</h2>
        <form:form action="save" method="post" modelAttribute="useraccount">
            <table border="0" cellpadding="5">
            <tr>
            	 <tr>
                    <td>Account Number: </td>
                    <td><form:input path="accountno" /></td>
                </tr>
                <tr>
                    <td>Contact No: </td>
                    <td><form:input path="contactno" /></td>
                </tr>    
                <tr>
                    <td>Total Bal: </td>
                    <td><form:input path="totalbal" /></td>
                </tr>  
                <tr>
                    <td colspan="2"><input type="submit" value="Save"></td>
                </tr>                    
            </table>
        </form:form>
    </div>

<h5><a href="/new-transaction">Transfer Money</a></h5>
<h5><a href="/transaction">Transactions</a></h5>
<h5><a href="/useraccount">User</a></h5>

</body>
</html>