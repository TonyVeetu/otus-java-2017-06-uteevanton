package uteevbkru.frontend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import uteevbkru.frontend.service.TimeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TimerServlet extends HttpServlet {

    private static final String REFRESH_VARIABLE_NAME = "refreshPeriod";
    private static final String TIME_VARIABLE_NAME = "time";
    private static final String TIMER_PAGE_TEMPLATE = "timer.html";

    private static final int PERIOD_MS = 1000;

    // HW13
    @Autowired
    private TimeService timeService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initAutowiredBeans();
    }
    private void initAutowiredBeans() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(REFRESH_VARIABLE_NAME, String.valueOf(PERIOD_MS));
        pageVariables.put(TIME_VARIABLE_NAME, timeService.getTime());

        response.getWriter().println(TemplateProcessor.instance().getPage(TIMER_PAGE_TEMPLATE, pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
