<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="page"%>
<%
	int op = Integer.parseInt(request.getParameter("pg"));
	switch (op) {
	
	    
	    case 94:%><%@ include file="pages\departamentos.jsp" %><% break;
	    case 96:%><%@ include file="pages\produtos.jsp" %><% break;
	    case 97:%><%@ include file="pages\mercadorias.jsp" %><% break;
	    case 98:%><%@ include file="pages\fornecedores.jsp" %><% break;
	    case 99:%><%@ include file="pages\movimentacaos.jsp" %><% break;
	    
	}
%>