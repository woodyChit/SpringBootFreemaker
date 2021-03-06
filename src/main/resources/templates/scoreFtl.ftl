<!DOCTYPE html>

<html lang="en">
<head>
    <script src="//cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>

    <script language="JavaScript">
        function randomScore(){
            var name = document.getElementById("name").value;
            if(name==''){
                return false
            }else{
                $.ajax({
                    type:"POST",
                    data:{name:name},
                    dataType:"json",
                    url:"/redis/rdScore.json",
                    success:function(json){
                        var name = json.s;
                        var score = json.v;
                        var rank = json.rank;
                        var newPath = '/score.html?name='+ name + '&score=' + score +'&myrank='+ rank;
                        console.log(newPath);
                        window.location.href=newPath;
                    }
                });
            }
        }
    </script>
</head>
<body>
<h1>RankScore of TOP10 rendered by FTL</h1>
<hr>

<table border="1">
    <tr><td>排名</td><td>姓名</td><td>得分</td></tr>
    <#if rank?exists && (rank?size>0) >
        <#list rank?keys as key>
            <tr>
                <td>${key_index+1}</td>
                <#if isNew?? && isNew==false && key==name>
                    <td bgcolor="#00FF00">${key} </td>
                <#else>
                    <td>${key}</td>
                </#if>
                <td>${rank["${key}"]}</td>
            </tr>
        </#list>
    </#if>
</table>
<p><i>共${total!}条数据</i></p>
<input type="text" id="name" />
<input type="button" onclick="randomScore()" id="btn" name="btn" value="新得分"/>
<br>
<hr>
<#if isNew?? && isNew==false>
    上次用户名${name!}，得分${score!},排名${myrank!}
</#if>
<br>
<a href="/">Back</a>
</body>

</html>