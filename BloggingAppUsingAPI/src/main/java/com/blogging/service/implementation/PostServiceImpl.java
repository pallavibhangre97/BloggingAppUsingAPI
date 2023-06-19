package com.blogging.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogging.dto.PostDto;
import com.blogging.entities.Category;
import com.blogging.entities.Post;
import com.blogging.entities.User;
import com.blogging.exception.ResourceNotFoundException;
import com.blogging.payload.PostResponse;
import com.blogging.repository.CategoryRepo;
import com.blogging.repository.PostRepo;
import com.blogging.repository.UserRepo;
import com.blogging.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostRepo postRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	UserRepo userRepo;
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		Optional<User> user = userRepo.findById(userId);

		if (user.isEmpty()) {
			throw new ResourceNotFoundException(userId);
		}
		User user1 = user.get();

		Optional<Category> category1 = categoryRepo.findById(categoryId);

		if (category1.isEmpty())
			throw new ResourceNotFoundException(categoryId);

		Category category = category1.get();

		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new java.util.Date());
		post.setUser(user1);
		post.setCategory(category);
		Post savedPost = postRepo.save(post);
		return modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public void detetePost(int post_id) {
		Optional<Post> post = postRepo.findById(post_id);
		if (post.isEmpty()) {
			throw new ResourceNotFoundException(post_id);
		} else {
			Post deletePost = post.get();
			postRepo.delete(deletePost);
		}

	}

	@Override
	public PostDto updatPost(PostDto postDto, Integer post_id) {

		Optional<Post> post1 = postRepo.findById(post_id);
		if (post1.isEmpty()) {
			throw new ResourceNotFoundException(post_id);

		}
		Post post = post1.get();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post savedPost = postRepo.save(post);
		return modelMapper.map(savedPost, PostDto.class);
	}

	/*
	 * @Override public List<PostDto> getAllPost(int pageNumber, int pageSize) {
	 * //pageNumber 1 pageSize 5 every page will have 5 records Pageable pageable =
	 * PageRequest.of(pageNumber, pageSize);
	 * 
	 * Page<Post> post = (Page<Post>) postRepo.findAll(pageable);
	 * 
	 * List<Post> posts = post.getContent(); List<PostDto> postDtos = new
	 * ArrayList<>(); for (Post p : posts) { postDtos.add(modelMapper.map(p,
	 * PostDto.class));
	 * 
	 * } return postDtos; }
	 */

	// customize post response using pagination
	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy) {
//pageNumber 1 pageSize 5 every page will have 5 records 
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Post> post = (Page<Post>) postRepo.findAll(pageable);

		List<Post> posts = post.getContent();
		List<PostDto> postDtos = new ArrayList<>();
		for (Post p : posts) {
			postDtos.add(modelMapper.map(p, PostDto.class));

		}

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setLastPage(post.isLast());
		postResponse.setPageNumber(post.getNumber());
		postResponse.setPageSize(post.getSize());
		postResponse.setTotalElement(post.getTotalElements());
		postResponse.setTotalPage(post.getTotalPages());
		return postResponse;
	}

	/*
	 * public List<PostDto> getAllPost() {
	 * 
	 * List<Post> post = postRepo.findAll(); List<PostDto> postDtos = new
	 * ArrayList<>(); for (Post p : posts) { postDtos.add(modelMapper.map(p,
	 * PostDto.class)); } return postDtos; }
	 * 
	 */
	@Override
	public PostDto getPostById(int post_id) {
		Optional<Post> postOptional = postRepo.findById(post_id);
		if (postOptional.isEmpty()) {
			throw new ResourceNotFoundException(post_id);
		}
		Post post = postOptional.get();
		return modelMapper.map(post, PostDto.class);

	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Optional<Category> category = categoryRepo.findById(categoryId);
		if (category.isEmpty()) {
			throw new ResourceNotFoundException(categoryId);
		}
		Category category2 = category.get();

		List<Post> posts = postRepo.findByCategory(category2);
		List<PostDto> postDtos = new ArrayList<>();

		for (Post p : posts) {

			postDtos.add(modelMapper.map(p, PostDto.class));
		}

		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		Optional<User> userOptional = userRepo.findById(userId);
		if (userOptional.isEmpty()) {
			throw new ResourceNotFoundException(userId);
		}
		User user = userOptional.get();
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDtos = new ArrayList<>();
		for (Post p : posts) {

			postDtos.add(modelMapper.map(p, PostDto.class));
		}

		return postDtos;

	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = postRepo.searchByTitle("%" + keyword + "%");
		List<PostDto> postDtos = new ArrayList<>();
		for (Post p : posts) {
			postDtos.add(modelMapper.map(p, PostDto.class));
		}
		return postDtos;
	}

}
