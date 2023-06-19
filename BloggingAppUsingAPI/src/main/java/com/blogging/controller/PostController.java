package com.blogging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.dto.PostDto;
import com.blogging.payload.ApiResponse;
import com.blogging.payload.PostResponse;
import com.blogging.service.implementation.PostServiceImpl;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostServiceImpl postServiceImpl;

	// Create post
	@PostMapping("/user/{userID}/category/{categoryID}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userID,
			@PathVariable Integer categoryID) {
		PostDto postDto2 = postServiceImpl.createPost(postDto, userID, categoryID);
		System.out.println("postDto2 :" + postDto2);
		return new ResponseEntity<PostDto>(postDto2, HttpStatus.CREATED);
	}

	// Delete Post
	@DeleteMapping("/delete/{postID}")
	public ResponseEntity<?> deletePost(@PathVariable Integer postID) {
		postServiceImpl.detetePost(postID);
		System.out.println("post deleted successfully");
		return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
	}

	// Get Post by Category
	@GetMapping("category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postDtos = postServiceImpl.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}

	// Get Post by User
	@GetMapping("user/{userId}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> postDtos = postServiceImpl.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}

	// Get All Post
	@GetMapping("/allpost")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy

	) {

		PostResponse postsDtos = postServiceImpl.getAllPost(pageNumber, pageSize, sortBy);
		return new ResponseEntity<PostResponse>(postsDtos, HttpStatus.OK);
	}

	// Get Post by Id
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto postsDtos = postServiceImpl.getPostById(postId);
		return new ResponseEntity<>(postsDtos, HttpStatus.OK);
	}

	// update post
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto postsDtos = postServiceImpl.updatPost(postDto, postId);
		return new ResponseEntity<>(postsDtos, HttpStatus.OK);
	}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String keyword)

	{
		List<PostDto> searchPost = postServiceImpl.searchPost(keyword);

		return new ResponseEntity<>(searchPost, HttpStatus.OK);
	}

}
