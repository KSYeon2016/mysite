package kr.ac.sungkyul.mysite.web.board;

import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("writeform".equals(actionName)){
			action = new BoardWriteFormAction();
		} else if("write".equals(actionName)){
			action = new BoardWriteAction();
		} else if("view".equals(actionName)){
			action = new BoardViewAction();
		} else if("delete".equals(actionName)){
			action = new BoardDeleteAction();
		} else if("modifyform".equals(actionName)){
			action = new BoardModifyFormAction();
		} else if("modify".equals(actionName)){
			action = new BoardModifyAction();
		} else {
			action = new BoardListAction();
		}
		
		return action;
	}

}
