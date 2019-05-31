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
		<h3 class="text-center">みんなのレシピ</h3>
			<div class="container">
				<div class="row h-a">
					<s:iterator value="#session.publicRecipeList">
					<div class="recipe-card col-3 mb-2 border">
						<span>作者: <s:property value="%{author}"/></span>
						<img class="img-thumbnail" style="width: 100%; height: 60%; object-fit: contain;" src='<s:property value="%{imageFilePath}"/>/<s:property value="%{imageFileName}"/>'>
						<div class="recipe-body">
							<s:form id="recipeDetail" action="RecipeDetailAction">
								<h5><s:property value="%{dishName}" /></h5>
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