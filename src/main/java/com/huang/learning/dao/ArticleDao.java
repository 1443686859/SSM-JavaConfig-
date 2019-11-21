package com.huang.learning.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao {
	/**
	 * 新增文章
	 */
	int addArticle(JSONObject jsonObject);

	/**
	 * 统计文章总数
	 */
	int countArticle(JSONObject jsonObject);

	/**
	 * 文章列表
	 */
	List<JSONObject> listArticle(JSONObject jsonObject);

	/**
	 * 更新文章
	 */
	int updateArticle(JSONObject jsonObject);
}
