// 创建分页方法
function pagging(pageNo, totalPageCount) {
	// 上一页
	if (pageNo > 1) {
		$("#pagelink").append("<a onclick='goPage(1);return false;'>首页</a>&nbsp;");
		$("#pagelink").append("<a onclick='goPage("+(pageNo-1)+");return false;'>上一页</a>&nbsp;");
	}
	
	// 中间页码
	// 当前页为第一页或者最后一页时显示4个页码
	// 当前页为中间页码时 显示第1页和最后一页，当前页前后各显示2个页码
	if (pageNo != 1 && pageNo >= 4 && totalPageCount != 4) {
		$("#pagelink").append("<a onclick='goPage("+1+");return false;'>"+1+"</a>&nbsp;");
	}
	if (pageNo > 4 && pageNo <= totalPageCount && totalPageCount > 5) {
		$("#pagelink").append("...&nbsp;");
	}
	var start = pageNo -2,end = pageNo+2;
	if ((start > 1 && pageNo < 4) || pageNo == 1) {
		end++;
	}
	if (pageNo > totalPageCount-4 && pageNo >= totalPageCount) {
		start--;
	}
	for (;start <= end; start++) {
		if(start <= totalPageCount && start >= 1){
			if(start != pageNo){
				$("#pagelink").append("<a onclick='goPage("+start+");return false;'>"+start+"</a>&nbsp;");
			}else{
				$("#pagelink").append("<a class='current'>"+start+"</a>&nbsp;");
			}
		}
	}
	if (pageNo < totalPageCount && pageNo >= 1 && totalPageCount > 5 && end < totalPageCount) {
		$("#pagelink").append("...&nbsp;");
	}
	if (pageNo != totalPageCount && pageNo < totalPageCount -2  && totalPageCount != 4) {
		$("#pagelink").append("<a onclick='goPage("+totalPageCount+");return false;'>"+totalPageCount+"</a>&nbsp;");
	}
	// 下一页
	if (pageNo < totalPageCount) {
		$("#pagelink").append("<a onclick='goPage("+(pageNo-1+2)+");return false;'>下一页</a>&nbsp;");
		$("#pagelink").append("<a onclick='goPage("+totalPageCount+");return false;'>尾页</a>");
	}
}