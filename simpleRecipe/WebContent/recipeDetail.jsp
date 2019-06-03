<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Simple Recipe</title>
        <script>
        	function homeAction(){
        		document.getElementById("form").submit();
        	}
        	function editRecipeAction(){
        		document.getElementById("editForm").submit();
        	}
        </script>
    </head>
    <body style="padding-top:4.5rem;">
	<jsp:include page="header.jsp" />
        <header></header>

        <h1 class="text-center">レシピ詳細画面</h1>
	        <div class="menu container bg-light border border-dark">
	            <div class="row">
	                <div class="col-6 p-5">
	                    <img src='<s:property value="#session.imageFilePath"/>/<s:property value="#session.imageFileName"/>' class="w-100">
	                </div>
	                <div class="col-6 p-5">
	                	<div class="box mb-3">
	                		作者: <s:property value="#session.author"/>
	                		<h3><s:property value="#session.dishName"/></h3>
	                		<s:property value="#session.dishInfo"/>
	                	</div>
	                	<div class="box mb-3 clearfix">
	                		<label>タグ</label>
                			<div class="ml-2">
                				<s:iterator value="#session.tagList" var="tag">
               						<s:form action="SearchTagAction">
               							<div class="float-left mr-2">
                							<s:hidden name="tagName" value="%{tag}"/>
                							<s:submit value="%{tag}"/>
               							</div>
               						</s:form>
                				</s:iterator>
                			</div>
	                	</div>
	                	<div class="box mb-3 clearfix">
	                		<label>材料・分量</label>
	                			<ul>
		                			<s:iterator value="#session.ingAmountList">
		                				<li><s:iterator><s:property/><span> </span></s:iterator></li>
		                			</s:iterator>
		                		</ul>
	                	</div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="col border-top border-dark">
	                	<div class="procedure container py-3">
	                		<div class="row mb-2">
	                			<div class="col-2"></div>
	                			<div class="col-8">
	                				<div class="mb-5">
	                					<div class="float-left">
			                				<label>手順</label>
				                			<ol>
			                					<s:iterator value="#session.procList">
				                					<li><s:property /></li>
			                					</s:iterator>
				                			</ol>
			                			</div>
			                			<div class="float-right">
			                				<label>この料理の調理時間 </label>
			                				<s:property value="#session.cookingTime"/>
											<label>分</label>
										</div>
									</div>
	                			</div>
	                			<div class="col-2"></div>
	                		</div>
	                	</div>
	                </div>
	            </div>
	        </div>
	        <s:if test="#session.userId==#session.author || #session.adminFlg!=null">
		<s:form id="editForm" action="EditRecipeAction">
		        <div class="text-center my-3">
		        	<button type="button" class="btn btn-success" onclick="editRecipeAction()">レシピを編集する</button>
			        </div>
        </s:form>
		        <div class="text-center mb-3">
		        	<a href='<s:url action="RecipeDeleteAction"/>' class="btn btn-danger" onclick='return confirm("本当にレシピを削除しますか？");'>レシピを削除する</a>
		        </div>
			</s:if>
        <script src="script.js"></script>
    </body>
</html>