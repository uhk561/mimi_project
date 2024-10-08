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
			 @RequestParam(value = "sort", required = false, defaultValue = "latest") String sort,
			 @RequestParam(value = "addressSearch", required = false) String addressSearch,
	         @RequestParam(value = "range", required = false) String range) {
			
		
			Integer userId = (Integer)session.getAttribute("userId");
			List<CardView> cardViewList = timelineBO.generateCardViewList(userId, sort, addressSearch, range);
			
			
			model.addAttribute("cardViewList", cardViewList);
			model.addAttribute("selectedSort", sort); // 선택한 값을 페이지화면(뷰)에서 유지시키기위해 모델에 추가
			model.addAttribute("addressSearch", addressSearch); // 선택한 값을 페이지화면(뷰)에서 유지시키기위해 모델에 추가
		    model.addAttribute("selectedRange", range);  // 선택한 값을 페이지화면(뷰)에서 유지시키기위해 모델에 추가

			return "timeline/timeline";
			
	}
			
}
