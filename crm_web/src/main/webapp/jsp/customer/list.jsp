<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>客户列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<link href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script language=javascript>
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

    function loadDict(type_code , tagId, oldId){
        //发起请求，获取字典数据 按类型查询字典数据
        var oldVal = oldId;
        var url = "${pageContext.request.contextPath }/baseDict_findByType";
        $.post(url , {"dict_type_code" :type_code } , function(result){

            $(result).each(function(i , n){ //i : 遍历的下标，  n : 遍历出来的对象   字典对象

                //找到标签，然后追加内容 val(aa) , text(aa) , html(xxx) , append(xxx)
                $(tagId).append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>")
            });
            $(tagId).find("option[value="+oldId+"]").prop("selected","selected")

        } , "json");
    }

    $(function(){
        loadDict("001" , "#cust_industry","${cust_industry.dict_id }"); //001  -- 客户行业
        loadDict("002" , "#cust_source","${cust_source.dict_id }"); //002  -- 客户来源
        loadDict("006" , "#cust_level","${cust_level.dict_id }");//006  -- 客户级别
    })

	function del(id) {
		var flag = confirm("确定删除该客户吗?")
		if(flag){
		    location.href = "${pageContext.request.contextPath }/customer_delete?cust_id="+id;
		}
    }
    function edit(id) {
		location.href = "${pageContext.request.contextPath }/customer_edit.action?cust_id="+id;
    }

</script>
<meta content="MSHTML 6.00.2900.3492" name=GENERATOR>
</head>
<body>
	<form id="customerForm" name="customerForm" action="${pageContext.request.contextPath }/customer_findByPage.action" method=post>
		<table cellSpacing=0 cellPadding=0 width="98%" border=0>
			<tbody>
				<tr>
					<td width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg" border=0></td>
					<td width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg" height=20></td>
					<td width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg" border=0></td>
				</tr>
			</tbody>
		</table>
		<table cellSpacing=0 cellPadding=0 width="98%" border=0>
			<tbody>
				<tr>
					<td width=15 background="${pageContext.request.contextPath }/images/new_022.jpg">
						<img src="${pageContext.request.contextPath }/images/new_022.jpg" border=0>
					</td>
					<td vAlign=top width="100%" bgColor=#ffffff>
						<table cellSpacing=0 cellPadding=5 width="100%" border=0>
							<tr>
								<td class=manageHead>当前位置：客户管理 &gt; 客户列表</td>
							</tr>
							<tr>
								<TD height=2></TD>
							</tr>
						</table>
						<table borderColor=#cccccc cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
							<tbody>
								<tr>
									<td height=25>
										<table cellSpacing=0 cellPadding=2 border=0>
											<tbody>
												<tr>
													<td>客户名称：</td>
													<td><s:textfield class="textbox" id="sChannel2" style="WIDTH: 80px" maxLength="50" name="cust_name" /></td>
													<td>客户电话：</td>
													<td><s:textfield class="textbox" id="sChannel2" style="WIDTH: 80px" maxLength="50" name="cust_phone" /></td>
													<%--<td>客户联系人：</td>--%>
													<td>
													<%--<s:textfield class="textbox" id="sChannel2" style="WIDTH: 80px" maxLength="50" name="cust_name" />--%>
													</td>

													<td>客户来源 ：</td>
													<td>
														<select name="cust_source.dict_id" class=textbox id="cust_source" style="WIDTH: 180px;height:21px">
															<option value="">---请选择---</option>
														</select>
													</td>
													<td>客户行业：</td>
													<td>
														<select name="cust_industry.dict_id" class=textbox id="cust_industry" style="WIDTH: 180px;height:21px">
															<option value="">---请选择---</option>
														</select>
													</td>
													<td>客户级别：</td>
													<td>
														<select name="cust_level.dict_id" class=textbox id="cust_level" style="WIDTH: 180px;height:21px">
															<option value="">---请选择---</option>
														</select>
													</td>

													<td><input class=button id=sButton2 type=submit value=" 筛选 " name=sButton2></td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table id=grid style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc" cellSpacing=1 cellPadding=2 rules=all border=0>
											<tbody>
												<tr style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<td>客户名称</td>
													<td>客户级别</td>
													<td>客户来源</td>
													<td>所属行业</td>
													<td>联系地址</td>
													<td>联系电话</td>
													<td>操作</td>
												</tr>
												<c:forEach items="${list }" var="customer">
												<tr style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<td>${customer.cust_name }</td>
													<td>${customer.cust_level.dict_item_name }</td>
													<td>${customer.cust_source.dict_item_name }</td>
													<td>${customer.cust_industry.dict_item_name }</td>
													<td>${customer.cust_address }</td>
													<td>${customer.cust_phone }</td>
													<td>
													<a href="javascript:void(0)" onclick="edit(${customer.cust_id})">修改</a>
													&nbsp;&nbsp;
													<a href="javascript:void(0)" onclick="del(${customer.cust_id})">删除</a>
													</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</td>
								</tr>
								 <tr>
									<td>
										<span id=pagelink>
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

												<!-- 当前页已经是最后一页 -->
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
										</span>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
					<td width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
						<img src="${pageContext.request.contextPath }/images/new_023.jpg" border=0>
					</td>
				</tr>
			</tbody>
		</table>
		<table cellSpacing=0 cellPadding=0 width="98%" border=0>
			<tbody>
				<tr>
					<td width=15><img src="${pageContext.request.contextPath }/images/new_024.jpg" border=0></td>
					<td align=middle width="100%" background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></td>
					<td width=15><img src="${pageContext.request.contextPath }/images/new_026.jpg" border=0></td>
				</tr>
			</tbody>
		</table>
	</form>
<s:debug></s:debug>
</body>
</html>
