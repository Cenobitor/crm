<%--
  Created by IntelliJ IDEA.
  User: Mac
  Date: 25/02/2018
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function to_page(page){
            if(page){
                $("#page").val(page);
            }else{
                var requestPage  = $("#page").val();
                var totalPage  = "${totalPage}";
                if(Number(requestPage) > Number(totalPage)){
                    alert(requestPage +" 超过了最大页 ==" + totalPage);
                    //让他到达最后一页
                    $("#page").val(totalPage);
                }
            }
            document.customerForm.submit();
        }

        function changePageSize() {
            document.customerForm.submit();
        }
    </script>
</head>
<body>
<SPAN id=pagelink>
    <div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
        共[<b>${totalSize}</b>]条记录,[<b>${totalPage}</b>]页
        ,每页显示
        <select name="pageSize" onchange="changePageSize()">
            <option value="5" <c:if test="${pageSize==5 }">selected</c:if>>5</option>
            <option value="10" <c:if test="${pageSize==10 }">selected</c:if>>10</option>
            <option value="15" <c:if test="${pageSize==15 }">selected</c:if>>15</option>
            <option value="20" <c:if test="${pageSize==20 }">selected</c:if>>20</option>
        </select>
        条
        [
        <s:if test="currentPage == 1">
            前一页
        </s:if>
        <s:else>
            <a href="javascript:to_page(${currentPage-1})">前一页</a>
        </s:else>
        ]
        <b>${currentPage}</b>
        [

        <s:if test="currentPage == totalPage">
            后一页
        </s:if>
        <s:else>
            <a href="javascript:to_page(${currentPage+1})">后一页</a>
        </s:else>
		]
		到
		<input type="text" size="3" id="page" name="currentPage" onchange="changePageSize()"/>
		页
		<input type="button" value="Go" onclick="to_page()"/>
	</div>
</SPAN>
</body>
</html>
