package com.w3villa.view;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.InternalResourceView;

public class CustomJSTLView extends InternalResourceView {

	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Determine which request handle to expose to the RequestDispatcher.
		HttpServletRequest requestToExpose = getRequestToExpose(request);

		// Expose the model object as request attributes.
		exposeModelAsRequestAttributes(model, requestToExpose);

		// Expose helpers as request attributes, if any.
		exposeHelpers(requestToExpose);

		// Determine the path for the request dispatcher.
		String dispatcherPath = prepareForRendering(requestToExpose, response);

		System.out.println("dispatcherPath : " + dispatcherPath);
		// set original view being asked for as a request parameter
		String split[] = dispatcherPath.split("/WEB-INF/views/");
		request.setAttribute("partial", split[1]);

		
		// Obtain a RequestDispatcher for the target resource (typically a JSP).
		RequestDispatcher rd = request
				.getRequestDispatcher("/WEB-INF/views/template.jsp");
		rd.include(request, response);
	}


}
