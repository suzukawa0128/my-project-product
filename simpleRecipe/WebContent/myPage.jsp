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
		<div class="w-75 mx-auto clearfix">
			<h3 class="text-center">マイレシピ一覧</h3>
			<div class="my-3 float-right d-inline">
				<s:form action="SearchTagAction">
					<s:textfield name="tagName" placeholder="検索するタグを入力"/>
					<s:hidden name="myPageFlg" value="true"/>
					<s:submit value="タグ検索"/>
				</s:form>
			</div>
		</div>
		<s:if test="errorMsg!=null">
			<div class="text-center clearfix">
				<s:property value="errorMsg"/>
			</div>
		</s:if>
		<div class="container">
			<s:if test="#session.myTagRecipeListForCurrentPage==null">
				<div class="row">
					<s:iterator value="#session.myRecipeListForCurrentPage">
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
				<nav>
					<ul class="pagination justify-content-center my-5">
						<s:iterator begin="1" end="#session.totalPageSize" status="pageNo">
							<s:if test="#session.currentPageNo==#pageNo.count">
								<li class="page-item">
									<a class="page-link"><s:property value="%{#pageNo.count}"/></a>
								</li>
							</s:if>
							<s:else>
								<li class="page-item">
									<a class="page-link"
									href="
										<s:url action='GoThisPageAction'>
											<s:param name='myPageAllFlg' value='true'/>
											<s:param name='pageNo' value='%{#pageNo.count}'/>
										</s:url>
									">
										<s:property value="%{#pageNo.count}"/>
									</a>
								</li>
							</s:else>
						</s:iterator>
					</ul>
				</nav>
			</s:if>
			<s:else>
				<div class="row">
					<s:iterator value="#session.myTagRecipeListForCurrentPage">
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
				<nav>
					<ul class="pagination justify-content-center my-5">
						<s:iterator begin="1" end="#session.totalPageSize" status="pageNo">
							<s:if test="#session.currentPageNo==#pageNo.count">
								<li class="page-item">
									<a class="page-link"><s:property value="%{#pageNo.count}"/></a>
								</li>
							</s:if>
							<s:else>
								<li class="page-item">
									<a class="page-link"
									href="
										<s:url action='GoThisPageAction'>
											<s:param name='myPageTagFlg' value='true'/>
											<s:param name='pageNo' value='%{#pageNo.count}'/>
										</s:url>
									">
										<s:property value="%{#pageNo.count}"/>
									</a>
								</li>
							</s:else>
						</s:iterator>
					</ul>
				</nav>
			</s:else>
		</div>
	</body>
</html>