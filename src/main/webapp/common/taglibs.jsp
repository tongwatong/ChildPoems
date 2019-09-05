<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/5
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--taglib 标签库 指令--%>
<%--引用了jstl核心库--%>
<%--prefix 含义，前缀--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<c: 应用jstl 声明变量--%>
<%--ctx 上下文 相当于定义一个变量名，若要使用value直接写ctx就行--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>