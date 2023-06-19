package com.blogging.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogging.entities.Category;
import com.blogging.entities.Post;
import com.blogging.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);// custom finder method

	List<Post> findByCategory(Category category);

	// List<Post> findByTitleContaining(String keyword);
	@Query("select p  from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String keyword);
}
