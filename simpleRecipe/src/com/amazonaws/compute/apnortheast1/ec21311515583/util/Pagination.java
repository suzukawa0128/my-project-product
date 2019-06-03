package com.amazonaws.compute.apnortheast1.ec21311515583.util;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.compute.apnortheast1.ec21311515583.dto.PaginationDTO;
import com.amazonaws.compute.apnortheast1.ec21311515583.dto.RecipeDTO;

public class Pagination {

	// getPage は、[[ 任意のページ番号における、商品一覧のページ情報
	// ]]を取得する。（商品リストproductList、１ページの表示数pageSize、表示するページ番号pageNoが必要）
	public PaginationDTO getPage(List<RecipeDTO> recipeList, int pageSize, int pageNo) {
		PaginationDTO paginationDTO = new PaginationDTO();
		// 例として右に「全35件を20件ずつ表示する場合の、2ページ目の値」のイメージをそれぞれ追記。
		int a = recipeList.size(); //35
		int b = pageSize; //20
		int c = (int)(a / b) + 1; //2
		int initialNumberForCurrentPage = (pageSize * (pageNo - 1)) + 1; //21
		paginationDTO.setTotalPageSize(c); // 全ページ数2
		paginationDTO.setCurrentPageNo(pageNo); // 表示するページ番号2
		paginationDTO.setTotalRecordSize(a); //全レシピ件数35
		paginationDTO.setStartRecordNo(initialNumberForCurrentPage); // 21番目の商品から表示
		paginationDTO.setEndRecordNo(initialNumberForCurrentPage + pageSize - 1); // 40番目の商品まで表示

		// ページャーとしてページ下部に表示する数字のリストを準備。
		List<Integer> nList = new ArrayList<Integer>();
		for (int i=1; i<=c; i++) {
			nList.add(i);
		}
		paginationDTO.setPageNumberList(nList);

		// ページに表示する商品リストを準備。
		List<RecipeDTO> recipeListForCurrentPage = new ArrayList<RecipeDTO>();
		int endNumberForCurrentPage = Math.min(paginationDTO.getEndRecordNo(), paginationDTO.getTotalRecordSize()); //35（40と35を比べて小さいほう）
		for (int i=initialNumberForCurrentPage-1; i<endNumberForCurrentPage; i++) {
			RecipeDTO recipe = recipeList.get(i);
			recipeListForCurrentPage.add(recipe);
		}
		paginationDTO.setRecipeListForCurrentPage(recipeListForCurrentPage);

		// 次ページ、前ページが存在するかどうか。
		boolean hasNextPage;
		boolean hasPreviousPage;
		if(pageNo==1){
			hasPreviousPage = false;
		}else{
			hasPreviousPage = true;
		}
		if(pageNo>=paginationDTO.getTotalPageSize()) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
		paginationDTO.setHasNextPage(hasNextPage);
		paginationDTO.setHasPreviousPage(hasPreviousPage);

		// 次ページ、前ページの番号。
		paginationDTO.setNextPageNo(pageNo + 1);
		paginationDTO.setPreviousPageNo(pageNo - 1);

		return paginationDTO;
	}
}