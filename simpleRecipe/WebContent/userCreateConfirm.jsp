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
		<h3 class="text-center">以下の内容でよろしいですか？</h3>
		<div class="text-center">
		<s:form action="UserCreateCompleteAction">
			<table class="mx-auto my-3">
				<tr>
					<th>ユーザーID</th>
					<td><s:property value="userId"/></td>
				</tr>
				<tr>
					<th>メールアドレス</th>
					<td><s:property value="mail"/></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><s:property value="pass"/></td>
				</tr>
			</table>
			<s:submit value="完了"/>
		</s:form>
		</div>
	</body>
</html>