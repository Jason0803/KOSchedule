package controller.diary;

import java.util.Vector;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.transport.nio.ParallelNioSender;

import controller.Controller;
import controller.ModelAndView;
import model.dao.diary.DiaryDAO;
import model.vo.diary.Diary;
import model.vo.diary.Memo;
import model.vo.diary.Note;
import model.vo.member.Member;
import sun.security.jca.GetInstance;
import util.CocoaDate;

public class NoteViewController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Member mvo = (Member)request.getSession().getAttribute("memberVO");
		String id = mvo.getId();
		boolean isCurr = Boolean.parseBoolean(request.getParameter("isCurr"));
		//Vector<Note> notes=DiaryDAO.getInstance().getAllNote(id);
		// #00161 : Apply getAllDiary
		Vector<Diary> notes = DiaryDAO.getInstance().getAllDiary(mvo.getId(), "note");
		Note note=null;
		
		request.setAttribute("notes", notes);
		if(isCurr) {
			int no=DiaryDAO.getInstance().getCurrNoteNo(id);
			note=DiaryDAO.getInstance().getNoteByNo(no);
		}else if(!isCurr) {
			note=DiaryDAO.getInstance().getNoteByNo(Integer.parseInt(request.getParameter("diaryNo")));
		} else {
			// Type Exception
			System.out.println("[NoteViewController] : NULL for 'isCurr' entered !");
			System.out.println(" - Current value for isCurr : " + isCurr);
		}
		request.setAttribute("note", note);
		
		return new ModelAndView("note_view.jsp");
	}

}
