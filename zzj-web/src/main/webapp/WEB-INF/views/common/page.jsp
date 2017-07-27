<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="../scripts/page.js"></script>
<script type="text/javascript">
var totalPageCount = 0;
var pageNo = 1;
$(function () {
	// 初始化参数
	initPage();
	// 创建分页方法
	pagging(pageNo, totalPageCount);
});
// 初始化参数
function initPage(){
	totalPageCount = parseInt($("#totalPageCount").val());
	pageNo = parseInt($("#pageNo").val());
}
// 页面跳转到
function goPage (goPageNo) {
	if ($("#choose_sel option") != null)
	{
		$("#choose_sel option").attr("selected", true);
	}
	$("input[name=pageNo]").val(goPageNo);
	document.forms[0].action = link;
	document.forms[0].target = "_self";
	document.forms[0].submit();
}
</script>
<input type="hidden" value="${resultList.pageNo}" name="pageNo" id="pageNo">
<input type="hidden" value="${resultList.totalPageCount}" name="totalPageCount" id="totalPageCount">
<th colspan="100" id="pagelink">当前第${resultList.pageNo}页/共${resultList.totalPageCount}页&nbsp;&nbsp;共${resultList.totalCount}条记录&nbsp;&nbsp;
</th>