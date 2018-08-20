<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/19
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/public/head.jspf"%>
    <style type="text/css">
        form div{
            margin: 5px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            var dg = parent.$("iframe[title='类别管理'").get(0).contentWindow.$("#dg");

            $("#cc").combobox({
                url:'account_query.action',
                valueField:'id',
                textField:'login',
                panelHeight:'auto',
                panelWidth:120,
                width:120,
                editable:false
            });

            var rows = dg.datagrid("getSelections");

            $("#ff").form("load",{
                id:rows[0].id,
                type:rows[0].type,
                hot:rows[0].hot,
                'account.id':rows[0].account.id
            });

            $("input[name=type]").validatebox({
                required:true,
                missingMessage:'请输入类别名称'
            });

            $("#ff").form("disableValidation");

            $("#btn").click(function (){
                if($("#ff").form("validate")){
                    $("#ff").form('submit',{
                        url:'category_update.action',
                        success:function () {
                            parent.$("#win").window("close");
                            dg.datagrid("reload");
                        }
                    });
                }
            });
        });
    </script>
</head>

<body>
<form id="ff" method="post">
    <div>
        <label for="name">类别名称：</label><input type="text" name="type"/>
    </div>
    <div>
        <label for="hot">热点：</label>
        是<input type="radio" name="hot" value="1"/>&nbsp;
        否<input type="radio" name="hot" value="0"/>
    </div>
    <div>
        <label for="account">所属管理员:</label>
        <input id="cc" name="account.id"/>
    </div>
    <div>
        <a id="btn" href="#" class="easy-linkbutton" data-options="iconCls:'icon-edit'">更新</a>
        <input type="hidden" name="id"/>
    </div>
</form>
</body>
</html>
