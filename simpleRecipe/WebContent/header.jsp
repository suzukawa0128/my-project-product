<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script type="text/javascript" src="./js/header.js"></script>
</head>
<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
	<div class="w-100">
		<div class="w-75 mx-auto">
			<a class="navbar-brand" href="#">Simple Recipe</a>
			<button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#Navber" aria-controls="Navber" aria-expanded="false" aria-label="ナビゲーションの切替">
			  <span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse float-right" id="Navber">
				<s:form id="form" name="form">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active">
					  <a class="nav-link" href='<s:url action="GoAllRecipeAction" />'>みんなのレシピ</a>
					</li>
					<s:if test="#session.userId!=null">
						<li class="nav-item active">
						  <a class="nav-link" href='<s:url action="GoMyPageAction" />'>マイページ</a>
						</li>
					</s:if>
					<s:if test="#session.userId!=null">
						<li class="nav-item active">
						  <a class="nav-link" href='<s:url action="GoCreateRecipeAction" />'>レシピ登録</a>
						</li>
					</s:if>
					<s:if test="#session.userId!=null">
						<li class="nav-item active">
					  		<a class="nav-link" href='<s:url action="LogoutAction" />'>ログアウト</a>
						</li>
					</s:if>
					<s:else>
						<li class="nav-item active">
					  		<a class="nav-link" href='<s:url action="GoLoginAction" />'>トップ</a>
						</li>
					</s:else>
				</ul>
				</s:form>
			</div>
		</div>
	</div>
	</nav>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
   	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
   	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</header>