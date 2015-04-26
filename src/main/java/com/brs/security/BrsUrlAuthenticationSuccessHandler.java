package com.brs.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class BrsUrlAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	protected final Log logger = LogFactory.getLog(this.getClass());
	private RequestCache requestCache = new HttpSessionRequestCache();
	protected String baseBrsAuthContext;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}
		String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl()
				|| (targetUrlParameter != null && StringUtils
						.isNotBlank(request.getParameter(targetUrlParameter)))) {
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}

		clearAuthenticationAttributes(request);

		String targetUrl = savedRequest.getRedirectUrl();
		targetUrl = resetDefTargetUrl(request, targetUrl);
		logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	protected String resetDefTargetUrl(HttpServletRequest request,
			String targetUrl) throws ServletException {
		String contextPath = request.getContextPath();
		String contextPathUri = StringUtils.substringBefore(targetUrl,
				contextPath);
		String brsLoginSuccessRedirectUrl = this.getDefaultTargetUrl();
		try {
			if (!targetUrl.equals(contextPathUri + brsLoginSuccessRedirectUrl)) {
				targetUrl = contextPathUri + contextPath
						+ brsLoginSuccessRedirectUrl;
			}
		} catch (Exception ex) {
			logger.error("resetDefTargetUrl:getRequestParameters:Exception", ex);
			throw new ServletException(ex);
		}

		return targetUrl;
	}

	public String getBaseBrsAuthContext() {
		return baseBrsAuthContext;
	}

	public void setBaseBrsAuthContext(String baseBrsAuthContext) {
		this.baseBrsAuthContext = baseBrsAuthContext;
	}
}