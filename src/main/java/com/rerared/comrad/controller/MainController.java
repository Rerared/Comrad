package com.rerared.comrad.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rerared.comrad.domain.User;
import com.rerared.comrad.domain.UserInfo;
import com.rerared.comrad.repos.UserInfoRepository;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {
	@Autowired
	private UserInfoRepository userRepository;

	@Value("${upload.path}")
	private String uploadPath;

	@GetMapping("/")
	public String greeting(Map<String, Object> model) {
		return "greeting";
	}

	@GetMapping("/main")
	public String main(@RequestParam(required = false) String filter,Model model) {
		Iterable<UserInfo> users = userRepository.findAll();
		
		if (filter != null && !filter.isEmpty()) {
            users = userRepository.findBySirname(filter);
        } else {
        	users = userRepository.findAll();
        }
		
		model.addAttribute("users", users);
		model.addAttribute("filter", filter);
		return "main";
	}

	@PostMapping("/main")
	public String add(
			@AuthenticationPrincipal User user,
			@RequestParam String name, 
			@RequestParam String sirname,
			Map<String, Object> model,
			@RequestParam("file") MultipartFile file

	) throws IOException {
		UserInfo userInfo = new UserInfo(name, sirname, user);

		if (file != null && !file.getOriginalFilename().isEmpty()) {
			File uploadDir =  new File(uploadPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			String uuidFile = UUID.randomUUID().toString();
			String resultFilename = uuidFile + "." + file.getOriginalFilename();

			file.transferTo(new File(uploadPath + "/" + resultFilename));

			userInfo.setFilename(resultFilename);
		}

		userRepository.save(userInfo);

		Iterable<UserInfo> users = userRepository.findAll();

		model.put("users", users);

		return "main";
	}

}
