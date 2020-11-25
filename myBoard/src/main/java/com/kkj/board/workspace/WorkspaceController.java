package com.kkj.board.workspace;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class WorkspaceController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired WorkspaceService workspaceService;
	
	@RequestMapping(value = "workspace/doListing.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doListing() {
		LOG.debug("==========================");
		LOG.debug("==workspace/doListing.do==");
		LOG.debug("==========================");
		
		List<WorkspaceVO> outList = workspaceService.doSelectList();
		
		for(WorkspaceVO vo : outList) {
			LOG.debug("==outVO==" + vo);
		}
		
		Gson gson = new Gson();
    	String json = gson.toJson(outList);
		
		return json;
		
	}
}
