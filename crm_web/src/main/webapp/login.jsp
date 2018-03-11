<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<STYLE type=text/css>
			BODY {
				FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
			}
			TD {
				FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
			}
		</STYLE>
		<META content="MSHTML 6.00.6000.16809" name=GENERATOR>


		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript">
            $(function () {

                //$("#btn").prop("disabled",true);

                //验证码图片刷新
                $("#vcode").click(function () {
                    $(this).prop("src","${pageContext.request.contextPath }/user_createVerificationCode?"+new Date());
                });
                //验证码校验
                $("#txtcode").blur(function () {
                    var url = "${pageContext.request.contextPath }/user_checkVerificationCode"
					var code = $(this).val();
					$.get(url,{"code":code},function (result) {
						if(result != ""){
							$("#tip").html(result);
							$("#btn").prop("disabled",true);
                        }else{
                        	$("#tip").html("");
							$("#btn").removeAttr("disabled");
						}
                    });
                });
            })
		</script>
	</HEAD>
	<BODY>

		<FORM id=form1 name=form1 action="${pageContext.request.contextPath }/user_login" method="post">
		<DIV id=UpdatePanel1>
			<DIV id=div1 style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
			<DIV id=div2  style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
			<DIV>&nbsp;&nbsp; </DIV>
			<DIV>
				<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
	  				<TBODY>
					  	<TR>
					    	<TD style="HEIGHT: 105px"><IMG src="${pageContext.request.contextPath}/images/login_1.gif"  border=0></TD>
					    </TR>
					  	<TR>
    						<TD background="${pageContext.request.contextPath}/images/login_2.jpg" height=300>
      							<TABLE height=300 cellPadding=0 width=900 border=0>
							        <TBODY>
							        	<TR><TD colSpan=2 height=35></TD></TR>
							        	<TR>
							          		<TD width=360></TD>
							          		<TD>
									            <TABLE cellSpacing=0 cellPadding=2 border=0>
		              								<TBODY>
										              	<TR>
										                	<TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
										                	<TD style="HEIGHT: 28px" width=150>
										                		<INPUT id=user_code style="WIDTH: 130px" name=user_code required></TD>
										                	<TD style="HEIGHT: 28px" width=370>
										                		<SPAN  id=RequiredFieldValidator3 style="FONT-WEIGHT: bold; COLOR: red">${fieldErrors.msg[0] }</SPAN>
										                	</TD>
										                </TR>
										              	<TR>
											                <TD style="HEIGHT: 28px">登录密码：</TD>
											                <TD style="HEIGHT: 28px"><INPUT id=user_password style="WIDTH: 130px" type=password name=user_password required></TD>
											                <TD style="HEIGHT: 28px">
											                	<SPAN id=RequiredFieldValidator4 style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white"></SPAN>
											                </TD>
											            </TR>
										              	<TR>
										                	<TD style="HEIGHT: 28px">验证码：</TD>
										               	 	<TD style="HEIGHT: 28px"><INPUT id=txtcode style="WIDTH: 130px" name=txtcode required></TD>
										                	<TD style="HEIGHT: 28px">&nbsp;<span id="tip" style="color: red"></span></TD></TR>
										              	<TR>
										                	<TD style="HEIGHT: 18px"></TD>
										                	<TD style="HEIGHT: 18px"><img id="vcode"  src="${pageContext.request.contextPath }/user_createVerificationCode" /></TD>
										                	<TD style="HEIGHT: 18px"></TD>
										                </TR>
										              	<TR>
                											<TD></TD>
                											<TD>
                												<INPUT id=btn style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("btn", "", true, "", "", false, false))'  type=image src="${pageContext.request.contextPath}/images/login_button.gif" name=btn>
              												</TD>
              											</TR>
              										</TBODY>
              									</TABLE>
              								</TD>
              							</TR>
              						</TBODY>
              					</TABLE>
              				</TD>
              			</TR>
  						<TR>
    						<TD><IMG src="${pageContext.request.contextPath}/images/login_3.jpg" border=0></TD>
    					</TR>
    				</TBODY>
    			</TABLE>
    		</DIV>
    	</DIV>
		</FORM>
	<s:debug/>
	</BODY>
</HTML>
