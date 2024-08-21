package com.mimi.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mimi.timeline.bo.TimelineBO;
import com.mimi.timeline.domain.CardView;

import jakarta.servlet.http.HttpSession;

@Controller
public class TimelineContriller {
	
	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/timeline/timeline-view")
	public String timelineView(Model model, HttpSession session, 
			@RequestParam("sort") Integer sort) {
		if (sort == 1) {
			Integer userId = (Integer)session.getAttribute("userId");
			List<CardView> cardViewList = timelineBO.generateCardViewList(userId);
			
			
			model.addAttribute("cardViewList", cardViewList);

		} else if (sort == 2) {
			Integer userId = (Integer)session.getAttribute("userId");
			List<CardView> cardViewList = timelineBO.generateCardViewSortList(userId);
			
			model.addAttribute("cardViewList", cardViewList);
		}
			return "timeline/timeline";
	}
}
