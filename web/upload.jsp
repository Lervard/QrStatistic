
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>为二维码加个统计</title>
</head>

<body>
<div>
    <h3>选择二维码图片上传到服务器</h3>
    <form action="produce" method="post" enctype="multipart/form-data">
        <input type="file" name="*.jpg; *.png">
        <input type="submit" value="上传"> 
    </form>
</div>
</body>

</html>

