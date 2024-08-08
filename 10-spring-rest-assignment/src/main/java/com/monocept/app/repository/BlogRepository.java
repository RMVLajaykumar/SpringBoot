package com.monocept.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monocept.app.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{

}
