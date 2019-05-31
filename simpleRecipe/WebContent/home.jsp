<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Simple Recipe</title>
	</head>
	<body style="padding-top:4.5rem;">
	<jsp:include page="header.jsp" />
		<h2 class="text-center mt-3">Simple Recipe</h2>
		<div class="container">
			<div class="text-center text-primary">
				<s:if test="successMsg!=null">
					<s:property value="successMsg"/>
				</s:if>
			</div>
			<s:form action="LoginAction">
				<div class="row">
					<div class="col text-right">
						メールアドレス/ID
					</div>
					<div class="col">
						<input type="text" name="mail">
					</div>
				</div>
				<div class="row mb-2">
					<div class="col text-right">
						パスワード
					</div>
					<div class="col">
						<input type="password" name="pass">
					</div>
				</div>
				<div class="text-center mb-2">
						<s:submit class="btn btn-success" value="ログイン"/>
				</div>
			</s:form>

			<div class="text-center">
				<a href='<s:url action="GoUserCreateAction"/>' class="btn btn-primary">ユーザー登録</a>
			</div>
		</div>
	</body>
</html>