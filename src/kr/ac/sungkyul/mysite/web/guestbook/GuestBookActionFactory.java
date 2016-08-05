package kr.ac.sungkyul.mysite.web.guestbook;

import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("insert".equals(actionName)){
			action = new GuestBookInsertAction();
		} else if("delete".equals(actionName)){
			action = new GuestBookDeleteAction();
		} else if("deleteform".equals(actionName)){
			action = new GuestBookDeleteFormAction();
		} else {
			action = new GuestBookAction();
		}
		
		return action;
	}

}
