<%@ page import="service.ArticleAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@include file="common/taglibs.jsp"%>
<html>
<head>
    <title>内容页面</title>
    <%
        ArticleAction.action = "article_list";
    %>
</head>
<body>
<h2>当前用户：${user_info.getUserName()}</h2>
<form action="${ctx}/search_servlet" method="get">
    <input type="text" name="articleTitle">
    <input type="submit" value="搜索">
    <input type="button" value="上传" onclick="btnAddArticleClick()">
    <hr>
    <div>文章标题：${article_info.getArticleTitle()}</div>
    <div>文章作者：${article_info.getArticleWriter()}</div>
    <div style="width: 40%">文章内容：<p style="word-wrap: break-word">${article_info.getArticleContent()}</p></div>
    <div style="width: 40%">
        <label>
            <a href="/article_servlet?flag=last" style="float: left">上一个</a>
            <a href="/article_servlet?flag=next" style="float: right">下一个</a>
        </label>
    </div>
</form>

<script type="text/javascript">
    function btnAddArticleClick() {
        window.location.href = "/addArticle.jsp"
    }
</script>
</body>
</html>
