<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<!--<link rel="stylesheet" type="text/css" href="../Styles/admin-all.css" />-->
<link rel="stylesheet" type="text/css" href="../styles/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" type="text/css" href="../styles/superTables.css" />
<link rel="stylesheet" type="text/css" href="../styles/common.css" />
<!--<link rel="stylesheet" type="text/css" href="../Styles/base.css"/>-->
<script type="text/javascript" src="../scripts/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>

<!--<link rel="stylesheet" type="text/css" href="../Styles/formui.css"/>-->
<script type="text/javascript" src="../scripts/tb.js"></script>
<script type="text/javascript">
        $(function () {
            $(".datepicker").datepicker();
            //$('#list').hide();
            //$('#find').click(function () {
            //    $('#list').show();
            //}) 
        })
        // 保存事件
		function save()
		{
		    document.forms[0].action = "<%=path%>/hotsearch/newEdit.htm";
			document.forms[0].submit();
		}

</script>
</head>
<body>
    <form id="hotShearchList" method="post" onkeydown="if(event.keyCode==13){save();return false;}">
    <div class="alert alert-heading"><h4>热搜词编辑</h4></div>
	    <%@include file="../common/common_msg.jsp"%>
        <table class="tbform" style="margin:0px; padding:0px;">
		<thead>
            <tr>
                <td colspan="1" class="sub-title">&nbsp;热搜词信息</td>
            </tr>
            <tr>
                <td colspan="6" class="auto-style2">&nbsp;※最多输入20个字</td>
            </tr>
        </thead>
            <tbody>
                <tr>
                   <td class="tdl">热搜词1</td>
                    <td class="td_detail">
                        <input class="input-large" onchange="checkrepeat();" id="termName01" name="termName01" value="${odlList.termName01}"/>
                    </td>
					<td class="tdl">热搜词2</td>
                    <td class="td_detail">
                        <input class="input-large" onchange="checkrepeat();" id="termName02"  name="termName02" value="${odlList.termName02}"/>
                    </td>
                </tr>
                <tr>
                   <td class="tdl">热搜词3</td>
                    <td class="td_detail">
                        <input class="input-large" onchange="checkrepeat();" id="termName03"  name="termName03" value="${odlList.termName03}"/>
                    </td>
					<td class="tdl">热搜词4</td>
                    <td class="td_detail">
                        <input class="input-large" onchange="checkrepeat();" id="termName04"  name="termName04" value="${odlList.termName04}"/>
                    </td>
                </tr>
				<tr>
                   <td class="tdl">热搜词5</td>
                    <td class="td_detail">
                        <input class="input-large" onchange="checkrepeat();" id="termName05"  name="termName05" value="${odlList.termName05}"/>
                    </td>
					<td class="tdl">热搜词6</td>
                    <td class="td_detail">
                        <input class="input-large" onchange="checkrepeat();" id="termName06"  name="termName06" value="${odlList.termName06}"/>
                    </td>
                </tr>
				<tr>
                   <td class="tdl">热搜词7</td>
                    <td class="td_detail">
                        <input class="input-large" onchange="checkrepeat();" id="termName07"  name="termName07" value="${odlList.termName07}"/>
                    </td>
					<td class="tdl">热搜词8</td>
                    <td class="td_detail">
                        <input class="input-large" onchange="checkrepeat();" id="termName08"  name="termName08" value="${odlList.termName08}"/>
                    </td>
                </tr>
				<tr>
                   <td class="tdl">热搜词9</td>
                    <td class="td_detail">
                        <input class="input-large" onchange="checkrepeat(this);" id="termName09"  name="termName09" value="${odlList.termName09}"/>
                    </td>
					<td class="tdl">热搜词10</td>
                    <td class="td_detail">
                        <input class="input-large" onchange="checkrepeat(this);" id="termName10"  name="termName10" value="${odlList.termName10}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button class="btn btn-inverse no_repeat_submit" onclick="save();return false;" >保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>