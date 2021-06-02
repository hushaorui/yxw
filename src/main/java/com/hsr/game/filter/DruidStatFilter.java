package com.hsr.game.filter;

import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {
		@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
})
public class DruidStatFilter extends WebStatFilter {
	private static final Log log = LogFactory.getLog(DruidStatFilter.class);

	public DruidStatFilter() {
		log.info(this.getClass().getName() + " init ...");
	}
}
