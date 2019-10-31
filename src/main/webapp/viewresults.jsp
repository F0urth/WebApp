<%@ page import="main.logic.services.ResponseParser" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: kamil
  Date: 10/30/19
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
    <title>Results</title>
</head>
<body>
    <h2>---INFO---</h2>
    <h3>
        <% System.out.println(); %>
        EffectiveDate: <%= ((ResponseParser.FullParseData) request.getAttribute("result")).getEffectiveDate() %>
        <% System.out.println(); %>
        No: <%= ((ResponseParser.FullParseData) request.getAttribute("result")).getNo() %>
        <% System.out.println(); %>
        Table: <%= ((ResponseParser.FullParseData) request.getAttribute("result")).getTable() %>
    </h3>
    <h2>---TABLE---</h2>

    <table border="1">
        <tr>
            <th>Mid</th>
            <th>Currency</th>
            <th>Code</th>
        </tr>
        <c:forEach items="${result.getRates()}" var="rate">
            <tr>
                <td>${rate.getMid()}</td>
                <td>${rate.getCurrency()}</td>
                <td>${rate.getCode()}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>---END---</h2>
</body>
</html>
