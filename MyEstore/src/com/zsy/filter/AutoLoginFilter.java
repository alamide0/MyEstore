package com.zsy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zsy.domain.User;
import com.zsy.factory.BasicFactory;
import com.zsy.service.UserService;

public class AutoLoginFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// System.out.println("lanjie");
		if (req.getSession(false) == null
				&& req.getSession().getAttribute("user") == null) {
			// System.out.println("11");
			Cookie[] cs = req.getCookies();
			if (cs != null) {
				Cookie f = null;
				for (Cookie c : cs) {
					if ("autologin".equals(c.getName())) {
						f = c;
						break;
					}
				}

				if (f != null) {
					String value = f.getValue();
					String username = value.split(":")[0];
					String password = value.split(":")[1];
					System.out.println(username + ":" + password);
					UserService service = BasicFactory.getFactory()
							.getInstance(UserService.class);
					User user = service
							.findUserByNameAndPwd(username, password);
					if (user != null) {
						req.getSession().setAttribute("user", user);
					}
				}
			}
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
