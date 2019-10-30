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
    <%!
        String outputRow(Double mid, String currency, String code) {
            return mid + " " + currency + " " + code;
        }
    %>
    <%
        List<ResponseParser.RatesParsData> result = ((ResponseParser.FullParseData) request.getAttribute("result")).getRates();
        System.out.println("mid currency code");
        for (ResponseParser.RatesParsData data : result) {
            outputRow(data.getMid(), data.getCurrency(), data.getCode());
        }
    %>
    <c:forEach items="${result.getRates()}" var="rate">
        <tr>
            <td>${rate.getMid()}</td>
            <td>${rate.getCurrency()}</td>
            <td>${rate.getCode()}</td>
        </tr>
    </c:forEach>

    <h2>---END---</h2>
</body>
</html>
