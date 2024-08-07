package com.mimi.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimelineContriller {
	
	@GetMapping("/timeline/timeline-view")
	public String timelineView() {
		
		
		return "timeline/timeline";
	}
}
