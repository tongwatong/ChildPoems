<%@ page import="service.ArticleAction" %><%--
  Created by IntelliJ IDEA.
  User: 童啊童
  Date: 2019/10/4
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>上传页面</title>
    <script type="text/javascript" src="resource/plugin/jquery/jquery-3.3.1.min.js"></script>
    <!--    <style type="text/css">-->
    <!--        .toolbar {-->
    <!--            border: 1px solid #ccc;-->
    <!--        }-->
    <!--        .text {-->
    <!--            border: 1px solid #ccc;-->
    <!--            height: 400px;-->
    <!--        }-->
    <!--    </style>-->
    <%
        ArticleAction.action = "article_add";
    %>

</head>
<body>
<div>
    <div>
        <p>为方便实用，可以在Word中编辑好然后粘贴上来。</p>
    </div>
    <div>
        文章标题：<input type="text" id="iptArticleTitle">&nbsp;&nbsp;
        文章作者：<input type="text" id="iptArticleWriter" value="佚名">
    </div>
    <div style="margin-bottom: 5px">下面输入文章内容</div>
    <div id="div1">

    </div>
    <button id="btnSubmit">提交</button>
    <button id="btnBack">返回</button>
</div>


<script type="text/javascript" src="resource/plugin/wangEditor-3.1.1/release/wangEditor.min.js"></script>
<script type="text/javascript">
    var E = window.wangEditor
    var editor = new E('#div1')
    editor.create()

    $('#btnSubmit').click(function () {
        var articleContent = editor.txt.html()
        var articleText = editor.txt.text()
        var articleTitle = $("#iptArticleTitle").val()
        var articleWriter = $("#iptArticleWriter").val()

        if ((articleTitle == null || articleTitle.length <= 0) && (articleText == null || articleText <= 0)) {
            alert("标题和内容不能为空！")
        }else {
            $.ajax({
                url: "/article_servlet",
                type: "post",
                data: {articleContent : articleContent, articleTitle : articleTitle, articleWriter:articleWriter},
                dataType: "html",
                success: function (response) {
                    alert("上传成功")
                    window.location.href = "/index.jsp"
                },
                error: function (request) {
                    alter("错误")
                }

            })
            // window.location.href = "/article_servlet?articleTitle="+articleTitle+"&articleWriter="+
            //     articleWriter+"&articleContent="+articleContent;
        }

    })
    $("#btnBack").click(function () {
        window.location.href = "index.jsp"
    })

</script>
</body>
</html>
