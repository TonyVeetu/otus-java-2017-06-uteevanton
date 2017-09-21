package uteevbkru.web;

import com.google.gson.Gson;
import uteevbkru.orm.dbService.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {

    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";
    private static final String ADMIN_PARAMETER_PASSWORD = "password";
    private static final String ADMIN_PARAMETER_NAME = "adm_login";

    private DBService dbService;

    public AdminServlet(DBService dbs){
        dbService = dbs;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestLogin = req.getParameter(ADMIN_PARAMETER_NAME);
        String password = req.getParameter(ADMIN_PARAMETER_PASSWORD);
        System.out.println("User enter login: "+requestLogin +", password: "+ password);

        try {
            if (requestLogin.equals("admin") && password.equals("123456")) {
                //TODO null pointer exception
                int[] cacheInfo = dbService.getCache().getCacheInfo();


                Map<String, Object> pageVariables = createPageVariablesMap(req, cacheInfo, "You are admin!");
                resp.getWriter().print(TemplateProcessor.instance().getPage(ADMIN_PAGE_TEMPLATE, pageVariables));
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_OK);
            }
            else{
                int[] cacheInfo = {-1,-1,-1,-1,-1,-1,-1};
                Map<String, Object> pageVariables = createPageVariablesMap(req, cacheInfo, "You aren't admin!");
                resp.getWriter().print(TemplateProcessor.instance().getPage(ADMIN_PAGE_TEMPLATE, pageVariables));
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

//    public void defFunction(HttpServletRequest req, HttpServletRequest resp){
//        int[] cacheInfo = {-1,-1,-1,-1,-1,-1,-1};
//        Map<String, Object> pageVariables = createPageVariablesMap(req, cacheInfo);
//        resp.getWriter().print(TemplateProcessor.instance().getPage(ADMIN_PAGE_TEMPLATE, pageVariables));
//        resp.setContentType("text/html;charset=utf-8");
//        resp.setStatus(HttpServletResponse.SC_OK);
//    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request, int[] cashInfo, String message) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("hit", cashInfo[0]);
        pageVariables.put("miss", cashInfo[1]);
        pageVariables.put("size", cashInfo[2]);
        pageVariables.put("maxsize", cashInfo[3]);
        pageVariables.put("idletime", cashInfo[4]);
        pageVariables.put("lifetime", cashInfo[5]);
        pageVariables.put("eternal", cashInfo[6]);
        pageVariables.put("message", message);

        return pageVariables;
    }

//    private static final String DEFAULT_USER_NAME = "UNKNOWN";
//    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";
//    private static final String ADMIN_PARAMETER_NAME = "adm_login";
//    private static final String ADMIN_PARAMETER_PASSWORD = "password";
//
//    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
//        Map<String, Object> pageVariables = new HashMap<>();
//        pageVariables.put("method", "method");//request.getMethod());
//        pageVariables.put("URL", request.getRequestURL().toString());
//        pageVariables.put("locale", request.getLocale());
//        pageVariables.put("sessionId", request.getSession().getId());
//        pageVariables.put("parameters", request.getParameterMap().toString());
//
//        //let's get login from session
//        String login = (String) request.getSession().getAttribute(LoginServlet.LOGIN_PARAMETER_NAME);
//        pageVariables.put("login", login != null ? login : DEFAULT_USER_NAME);
//
//        return pageVariables;
//    }
//
//    public void doGet(HttpServletRequest request,
//                      HttpServletResponse response) throws ServletException, IOException {
//
//        String requestLogin = request.getParameter(ADMIN_PARAMETER_NAME);
//        String password = request.getParameter(ADMIN_PARAMETER_PASSWORD);
//        System.out.println("User enter login: "+requestLogin +", password: "+ password);
//
//        if(requestLogin != null) {
//            if (requestLogin.equals("admin") && password.equals("12345")) {
//                //doPost(request, response);
//            }
//        }
//
//        Map<String, Object> pageVariables = createPageVariablesMap(request);
//
//        response.getWriter().println(TemplateProcessor.instance().getPage(ADMIN_PAGE_TEMPLATE, pageVariables));
//
//        response.setContentType("text/html;charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//
//    }
}
