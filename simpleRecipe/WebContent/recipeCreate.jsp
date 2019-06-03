<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Simple Recipe</title>
        <script>
	        function tagAdd(){
	            var div_element = document.createElement("div");
	            div_element.innerHTML = '<div class="row mb-1 text-center"><div class="col"><s:textfield name="tag" value=""/></div></div>';
	            var parent_object = document.getElementById("tagRow");
	            parent_object.appendChild(div_element);
	        }
	        function ingAdd(){
	            var div_element = document.createElement("div");
	            div_element.innerHTML = '<div class="row mb-1"><div class="col"><s:textfield name="ing" value=""/></div><div class="col"><s:textfield name="amount" value=""/></div></div>';
	            var parent_object = document.getElementById("piyo");
	            parent_object.appendChild(div_element);
	        }
	        function procAdd1(){
	            var div_element = document.createElement("li");
	            div_element.innerHTML = '<s:textfield class="form-control mb-2" name="proc" placeholder=""/>';
	            var parent_object = document.getElementById("procRow");
	            parent_object.appendChild(div_element);
	        }
	        function procAdd2(){
	            var div_element = document.createElement("li");
	            div_element.innerHTML = '<s:textfield class="form-control mb-2" name="proc" value="" placeholder=""/>';
	            var parent_object = document.getElementById("procRow");
	            parent_object.appendChild(div_element);
	        }
	        function homeAction(){
	        	document.getElementById("form").submit();
	        }
	        function tagModalAction(){
	        	document.getElementById("tagModal").submit();
	        }
	        function saveImageAction(){
	        	document.getElementById("image").submit();
	        }
	        function saveDishNameAction(){
	        	document.getElementById("dishName").submit();
	        }
	        function saveDishInfoAction(){
	        	document.getElementById("dishInfo").submit();
	        }
	        function ingModalAction(){
	        	document.getElementById("ingModal").submit();
	        }
	        function saveCookingTimeAction(){
	        	document.getElementById("cookingTime").submit();
	        }
	        function saveProcAction(){
	        	document.getElementById("proc").submit();
	        }
	        function saveCookingTipAction(){
	        	document.getElementById("cookingTip").submit();
	        }
	        function createRecipeAction(){
	        	document.getElementById("recipeForm").submit();
	        }
        </script>
    </head>
    <body style="padding-top:4.5rem;">
	<jsp:include page="header.jsp" />
        <header></header>

		<!-- ここからタグモーダル -->

		<div class="modal fade" id="tag-modal" tabindex="-1" role="dialog" aria-labelledby="label1" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="label1">タグ</h5>
                    </div>
                    <s:form id="tagModal" action="TagModalAction">
						<s:if test="#session.tagList==null">
		                    <div class="modal-body">
		                            <div class="container">
		                                <div class="row mb-1 text-center">
		                                    <div class="col">
		                                        <s:textfield name="tag" value=""/>
		                                    </div>
		                                </div>
		                                <div class="row mb-1 text-center">
		                                    <div class="col">
		                                        <s:textfield name="tag" value=""/>
		                                    </div>
		                                </div>
		                                <div id="tagRow">
		                                </div>
		                            </div>
		                    </div>
		                    <div class="modal-footer">
		                    	<button type="button" class="btn btn-success" onclick="tagAdd();">add row</button>
		                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		                       	<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="tagModalAction()">OK</button>
		                    </div>
	                    </s:if>
	                    <s:else>
	                    	<div class="modal-body">
		                            <div class="container">
		                            	<s:iterator status="rowStatus" value="#session.tagList" var="t">
			                                <div class="row mb-1 text-center">
			                                    <div class="col">
			                                        <s:textfield name="tagList[%{#rowStatus.index}]" value="%{t}"/>
			                                    </div>
			                                </div>
			                            </s:iterator>
		                                <div id="tagRow">
		                                </div>
		                            </div>
		                    </div>
		                    <div class="modal-footer">
		                    	<button type="button" class="btn btn-success" onclick="tagAdd();">add row</button>
		                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		                       	<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="tagModalAction()">OK</button>
		                    </div>
	                    </s:else>
                    </s:form>
                </div>
            </div>
        </div>

		<!-- ここから材料・分量モーダル -->

        <div class="modal fade" id="ing-modal" tabindex="-1" role="dialog" aria-labelledby="label1" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="label1">材料・分量</h5>
                    </div>
                    <s:form id="ingModal" action="IngModalAction">
                    	<s:if test="#session.ingAmountList==null">
		                    <div class="modal-body">
		                            <div class="container">
		                                <div class="row">
		                                    <div class="col">
		                                        材料
		                                    </div>
		                                    <div class="col">
		                                        分量
		                                    </div>
		                                </div>
		                                <div class="row mb-1">
		                                    <div class="col">
		                                        <s:textfield name="ing" value=""/>
		                                    </div>
		                                    <div class="col">
		                                        <s:textfield name="amount" value=""/>
		                                    </div>
		                                </div>
		                                <div class="row mb-1">
		                                    <div class="col">
		                                        <s:textfield name="ing" value=""/>
		                                    </div>
		                                    <div class="col">
		                                        <s:textfield name="amount" value=""/>
		                                    </div>
		                                </div>
		                                <div id="piyo">
		                                </div>
		                            </div>
		                    </div>
		                    <div class="modal-footer">
		                    	<button type="button" class="btn btn-success" onclick="ingAdd();">add row</button>
		                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		                       	<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="ingModalAction()">OK</button>
		                    </div>
                  		</s:if>
                  		<s:else>
                  			<div class="modal-body">
	                            <div class="container">
	                                <div class="row">
	                                    <div class="col">
	                                        材料
	                                    </div>
	                                    <div class="col">
	                                        分量
	                                    </div>
	                                </div>
	                                <s:iterator status="rowStatus" value="#session.ingAmountList" var="isl">
	                                <div class="row mb-1">
	                                	<s:iterator status="list" value="isl" var="is">
		                                    <div class="col">
		                                        <s:textfield name="ingAmountList[%{#rowStatus.index}]" value="%{is}"/>
		                                    </div>
	                                    </s:iterator>
	                                </div>
	                                </s:iterator>
	                                <div id="piyo">
	                                </div>
	                            </div>
		                    </div>
		                    <div class="modal-footer">
		                    	<button type="button" class="btn btn-success" onclick="ingAdd();">add row</button>
		                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		                       	<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="ingModalAction()">OK</button>
		                    </div>
                  		</s:else>
                    </s:form>
                </div>
            </div>
        </div>

        <!-- ここからメイン画面 -->

        <h1 class="text-center">レシピ登録</h1>
		<s:if test="errorMsg!=null">
			<div class="text-center"><s:property value="errorMsg"/></div>
		</s:if>

		<s:form id="recipeForm" action="CreateRecipeAction">
	        <div class="menu container bg-light border border-dark">
	            <div class="row">
	                <div class="col-6 p-5">
		        		<p>画像を挿入</p>
		        		<!-- なぜこのs:formがいるのか -->
	                	<s:form id="image" action="SaveImageAction" method="post" enctype="multipart/form-data"></s:form>
	                	<s:form id="image" action="SaveImageAction" method="post" enctype="multipart/form-data">
							<s:file name="itemImage" label="画像ファイル" placeholder="画像ファイル" class="file"/>
							<button type="button" class="btn btn-primary" onclick="saveImageAction()">save</button>
						</s:form>
						<s:if test="#session.imageFilePath!=null && #session.imageFileName!=null">
							<img src='<s:property value="#session.imageFilePath"/>/<s:property value="#session.imageFileName"/>' class="w-100">
						</s:if>
						<s:else>
		                    <img src="images/img1.jpg" class="w-100">
						</s:else>
	                </div>
	                <div class="col-6 p-5">
	                	<div class="box mb-3">
	                		料理名・料理の説明
	                		<s:form id="dishName" action="SaveDishNameAction">
	                			<s:textfield class="form-control mb-2" name="dishName" value="%{#session.dishName}" placeholder="料理名"/>
	                			<button type="button" class="btn btn-primary" onclick="saveDishNameAction()">save</button>
	                		</s:form>
	                		<s:form id="dishInfo" action="SaveDishInfoAction">
	                			<s:textfield class="form-control mb-2" name="dishInfo" value="%{#session.dishInfo}" placeholder="料理の説明"/>
	                			<button type="button" class="btn btn-primary" onclick="saveDishInfoAction()">save</button>
	                		</s:form>
	                	</div>
	                	<div class="box mb-3">
	                		<label>タグ</label>
	                			<ul>
	                				<s:iterator value="#session.tagList">
	                					<li><s:property/></li>
	                				</s:iterator>
	                			</ul>
		                    	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#tag-modal">
		                        	タグ登録
		                    	</button>
	                	</div>
	                	<div class="box mb-3">
	                		<label>材料・分量</label>
	                			<ul>
		                			<s:iterator value="#session.ingAmountList">
		                				<li>
		                				<s:iterator status="list">
		                					<s:property/>
		                				</s:iterator>
		                				</li>
		                			</s:iterator>
		                		</ul>
		                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ing-modal">
		                        材料・分量登録
		                    </button>
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
			                			</div>
			                			<div class="float-right">
			                				<label>この料理の調理時間</label>
					                		<s:form id="cookingTime" action="SaveCookingTimeAction">
					                		<input type="hidden" id="selectedCookingTime" value="<s:property value='session.cookingTime'/>" >
			                				<script>
			                					t = document.getElementById('selectedCookingTime').value;
			                					num_t = Number(t);
												document.write("<select name='cookingTime'>");
												for(var i=5;i<=60;i+=5){
													if(i===num_t){
														document.write("<option selected>");
													}else{
														document.write("<option>");
													}
													document.write(i);
													document.write("</option>");
												}
												document.write("</select>");
											</script>
											<label>分</label>
					                			<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="saveCookingTimeAction()">save</button>
					                		</s:form>
										</div>
									</div>
                       				<s:form id="proc" action="SaveProcAction">
	                					<s:if test="#session.procList==null">
			                				<ol class="" id="procRow">
		                						<li class=""><s:textfield class="form-control mb-2" name="proc" placeholder=""/></li>
			                       				<li class=""><s:textfield class="form-control mb-2" name="proc" placeholder=""/></li>
			                       				<li class=""><s:textfield class="form-control mb-2" name="proc" placeholder=""/></li>
			                       			</ol>
			                       			<div class="text-right">
			                       				<button type="button" class="btn btn-success" onclick="procAdd1();">add row</button>
					                			<button type="button" class="btn btn-primary" onclick="saveProcAction()">save</button>
			                       			</div>
		                       			</s:if>
	                					<s:else>
	                						<ol id="procRow">
			                					<s:iterator status="rowStatus" value="#session.procList" var="xxx">
				                       				<li class=""><s:textfield class="form-control mb-2" name="procList[%{#rowStatus.index}]" value="%{xxx}"/></li>
				                       			</s:iterator>
				                       		</ol>
			                       			<div class="text-right">
			                       				<button type="button" class="btn btn-success" onclick="procAdd2();">add row</button>
					                			<button type="button" class="btn btn-primary" onclick="saveProcAction()">save</button>
			                       			</div>
		                       			</s:else>
			                		</s:form>
	                			</div>
	                			<div class="col-2"></div>
	                		</div>
	                	</div>
	                </div>
	            </div>
	        <div class="my-4">
		        <div class="text-right">
		        	<s:checkbox name="publicity" checked="checked"/><label>みんなのレシピに公開する</label>
		        </div>
	        </div>
	        </div>
	        <div class="container text-center">
	        	<div class="mx-auto">
		        	<a href='<s:url action="GoMyPageAction"/>' class="btn btn-secondary">キャンセル</a>
		        	<s:hidden name="dishId" value="#session.dishId"/>
		        	<s:hidden name="imageFilePath" value="#session.imageFilePath"/>
		        	<s:hidden name="imageFileName" value="#session.imageFileName"/>
	        		<button type="button" class="btn btn-success my-5" onclick="createRecipeAction()">レシピを登録する</button>
	        	</div>
	        </div>
        </s:form>
    </body>
</html>