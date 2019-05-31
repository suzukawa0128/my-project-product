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
		<h2 class="text-center mt-3">新規登録</h2>
		<div class="text-center">
			<s:if test="errorMsg != ''">
				<p><s:property value="errorMsg"/></p>
			</s:if>
		</div>
		<div class="container">
			<s:form action="UserCreateConfirmAction">
				<div class="row">
					<div class="col text-right">
						ユーザーID
					</div>
					<div class="col">
						<input type="text" name="userId">
					</div>
				</div>
				<div class="row">
					<div class="col text-right">
						メールアドレス
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
						<s:submit class="btn btn-success" value="登録"/>
				</div>
			</s:form>
		</div>
	</body>
</html>