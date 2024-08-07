package com.mimi.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {

	@GetMapping("/post-create-view")
	public String postCreateView() {
		return "post/postCreate";
	}
	
}
