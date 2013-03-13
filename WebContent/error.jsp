<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%><jsp:forward page="/error/"><jsp:param name="msg" value="<%=exception.toString()%>" /></jsp:forward>
