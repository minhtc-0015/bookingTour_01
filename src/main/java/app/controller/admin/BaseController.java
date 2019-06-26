package app.controller.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.ui.Model;

import app.Constants.Constants;

public class BaseController {
	private static final Logger logger = Logger.getLogger(BaseController.class);

	
	public Properties getProperties() {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/messages.properties")) {
			prop.load(input);
			return prop;
		} catch (IOException ex) {
			logger.error(ex);
			return null;
		}
	}

	public PagedListHolder<?> setPagedListHolder(int pageNumber,List<?> listObj,PagedListHolder<?> pages){
		int pageSize = Constants.pagesize;
		if (pages == null) {
			pages = new PagedListHolder<>(listObj);
			pages.setPageSize(pageSize);
		} else {
			final int gotoPage = pageNumber - 1;
			if (gotoPage <= pages.getPageCount() && gotoPage >= 0)
				pages.setPage(gotoPage);

		}

		return pages;
	}

	public Model setModelPagination( int pageNumber, List<?> listObj, PagedListHolder<?> pages,
			Model model) {


		Constants.current = pages.getPage() + 1;
		Constants.begin = Math.max(1, Constants.current - listObj.size());
		Constants.end = Math.min(Constants.begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();

		model.addAttribute("beginIndex", Constants.begin);
		model.addAttribute("endIndex", Constants.end);
		model.addAttribute("currentIndex", Constants.current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("list", pages);

		return model;
	}
}
