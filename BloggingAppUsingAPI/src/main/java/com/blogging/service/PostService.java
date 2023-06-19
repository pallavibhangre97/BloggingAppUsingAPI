package com.blogging.service;

import java.util.List;

import com.blogging.dto.PostDto;
import com.blogging.payload.PostResponse;

public interface PostService {
	// create post
	PostDto createPost(PostDto postDto, int user_id, int categoryId);

	// get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);

	// get all post by user
	List<PostDto> getPostByUser(Integer userId);

	// delete post
	void detetePost(int post_id);

	// get all post
	// List<PostDto> getAllPost(int pageNumber, int pageSize);
	// customize post response
	PostResponse getAllPost(int pageNumber, int pageSize, String sortby);

	// get post by id
	PostDto getPostById(int post_id);

	// update post
	PostDto updatPost(PostDto postDto, Integer post_id);

	// search post
	List<PostDto> searchPost(String keyword);

}
