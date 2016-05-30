package messages.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import messages.dao.Dao;
import messages.objects.Comments;

@Controller
public class MessageController {

	@Autowired
	private Dao dao;

	@RequestMapping(value = "/")
	public ModelAndView listComment(ModelAndView model) throws IOException {
		List<Comments> listComm = dao.selectAll();
		model.addObject("listComm", listComm);
		model.setViewName("main");

		return model;
	}

	@RequestMapping(value = "/add-com", method = RequestMethod.GET)
	public ModelAndView newComment(ModelAndView model) {
		Comments newComments = new Comments();
		model.addObject("comm", newComments);
		model.setViewName("messageInput");
		return model;
	}

	@RequestMapping(value = "/save-com", method = RequestMethod.POST)
	public ModelAndView saveComment(@Valid @ModelAttribute("comm") Comments comments, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("messageInput");
		}

		dao.create(comments);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/quote", method = RequestMethod.GET)
	public ModelAndView quote(HttpServletRequest request) {
		int commentId = Integer.parseInt(request.getParameter("id"));
		Comments comments = dao.select(commentId);
		ModelAndView model = new ModelAndView("messageInput");
		model.addObject("comm", comments);

		return model;
	}

}
