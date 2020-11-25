package com.kkj.board.workspace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService {

	@Autowired WorkspaceDao workspaceDao;

	public int doInsert(WorkspaceVO workspaceVO) {
		return workspaceDao.doInsert(workspaceVO);
	}

	public WorkspaceVO doSelectOne(WorkspaceVO workspaceVO) {
		return workspaceDao.doSelectOne(workspaceVO);
	}

	public List<WorkspaceVO> doSelectList() {
		return workspaceDao.doSelectList();
	}

	public int doUpdate(WorkspaceVO workspaceVO) {
		return workspaceDao.doUpdate(workspaceVO);
	}

	public int doDelete(WorkspaceVO workspaceVO) {
		return workspaceDao.doDelete(workspaceVO);
	}

}
