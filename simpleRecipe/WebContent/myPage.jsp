<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/simpleRecipe.css">

		<title>Simple Recipe</title>
		<script>
		function recipeDetailAction(){
    		document.getElementById("recipeDetail").submit();
    	}
		</script>
	</head>
	<body style="padding-top:4.5rem;">
	<jsp:include page="header.jsp" />
		<div class="text-center mb-3">
			<s:if test="errorMsg != null">
				<p><s:property value="errorMsg"/></p>
			</s:if>
		</div>
		<h3 class="text-center my-3">マイレシピ一覧</h3>
			<div class="container">
				<div class="row">
					<s:iterator value="#session.recipeList">
					<div class="recipe-card col-3 mb-2 border">
						<img class="img-thumbnail" style="width: 100%; height: 60%; object-fit: contain;" src='<s:property value="%{imageFilePath}"/>/<s:property value="%{imageFileName}"/>' alt="カードの画像">
						<div class="recipe-body">
							<s:form id="recipeDetail" action="RecipeDetailAction">
								<h5 class="card-title"><s:property value="%{dishName}" /></h5>
								<div><s:property value="dishInfo" /></div>
								<s:hidden name="dishId" value="%{dishId}"/>
								<input type="submit">
							</s:form>
						</div>
					</div>
					</s:iterator>
				</div>
			</div>
	</body>
</html>