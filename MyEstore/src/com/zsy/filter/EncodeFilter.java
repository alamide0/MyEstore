package com.zsy.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodeFilter implements Filter {
	private String encode = null;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		resp.setCharacterEncoding(encode);
		resp.setContentType("text/html;chartset="+encode);

		chain.doFilter(new MyHttpServletRequest(req), resp);

	}

	private class MyHttpServletRequest extends HttpServletRequestWrapper {
		private HttpServletRequest req = null;
		private boolean tag = true;

		public MyHttpServletRequest(HttpServletRequest request) {
			super(request);
			req = request;
		}

		@Override
		public Map<String,String[]> getParameterMap() {
			
			//req.get
			try {
				if (req.getMethod().equals("POST")) {
					req.setCharacterEncoding(encode);
					return req.getParameterMap();
				} else if (req.getMethod().equals("GET")) {
					Map<String, String[]> map = req.getParameterMap();
					if (tag) {
						for (Map.Entry<String, String[]> entry : map.entrySet()) {
							String[] value = entry.getValue();
							for (int i = 0; i < value.length; i++) {
								value[i] = new String(value[i]
										.getBytes("iso8859-1"), encode);
								//System.out.println(value[i]);
							}
						}
						tag = false;
					}
					return map;
				} else {
					return super.getParameterMap();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return super.getParameterMap();

		}

		@Override
		public String getParameter(String name) {
			return this.getParameterValues(name)==null?null:this.getParameterValues(name)[0];
		}

		@Override
		public String[] getParameterValues(String name) {
			return  this.getParameterMap().get(name);
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		encode = filterConfig.getServletContext().getInitParameter("encode");
	}

}
