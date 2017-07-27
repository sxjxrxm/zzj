<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">

var errItem = new Array(); 

<c:forEach items="${errItem}" var="a"> 
 errItem.push('${a}');
</c:forEach> 
$(function () {
	for(var i = 0; i<errItem.length;i++){
		if($('#' + errItem[i]) != undefined) {
			$('#' + errItem[i]).addClass('input-error');
			if(i == 0) {
				$('#' + errItem[i]).focus();
			}
		}
	}
 });
</script>
<c:if test="${errMsg!= null && fn:length(errMsg) > 0}">
<div class="ul-error" id="error" style="margin-top:5px;margin-bottom:5px;">
    <ul>
		<c:forEach items="${errMsg}" var="p" >
		<c:if test="${p.trim() != ''}">
		<li>${p}</li>
		</c:if>
		</c:forEach>
	</ul>
</div>
</c:if>
<c:if test="${infoMsg!= null && fn:length(infoMsg) > 0}">
<div class="ul-info" id="info" style="margin-top:5px;margin-bottom:5px;">
    <ul>
		<c:forEach items="${infoMsg}" var="p" >
		<c:if test="${p.trim() != ''}">
		<li>${p}</li>
		</c:if>
		</c:forEach>
	</ul>
</div>
</c:if>
<c:if test="${warningMsg!= null && fn:length(warningMsg) > 0}">
<div class="ul-warning" id="warning" style="margin-top:5px;margin-bottom:5px;">
    <ul>
		<c:forEach items="${warningMsg}" var="p" >
		<c:if test="${p.trim() != ''}">
		<li>${p}</li>
		</c:if>
		</c:forEach>
	</ul>
</div>
</c:if>