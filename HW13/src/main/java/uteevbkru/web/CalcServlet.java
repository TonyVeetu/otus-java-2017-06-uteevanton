package uteevbkru.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anton on 27.09.17.
 */
public class CalcServlet extends HttpServlet {

    private final String NAME = "calc.html";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String str_a = request.getParameter("val_a");
        String str_b = request.getParameter("val_b");
        String str_op = request.getParameter("operation");

        Map<String, Object> pageVariables = createPageVariablesMap(request,str_a,str_b,str_op);
        response.getWriter().print(TemplateProcessor.instance().getPage(NAME,pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

//        double value_a = 0;
//        double value_b = 0;
//        boolean noError = true;
//
//        try {
//            value_a = Double.parseDouble(str_a);
//            value_b = Double.parseDouble(str_b);
//        }
//        catch ( Exception ex ) {
//            noError = false;
//        }
//
//        if ( noError ) {
//            double result = 0;
//            try {
//                if (str_op.equals("+"))
//                    result = functionSum( value_a, value_b );
//                if (str_op.equals("-"))
//                    result = functionDif( value_a, value_b );
//                if (str_op.equals("*"))
//                    result = functionMul( value_a, value_b );
//                if (str_op.equals("/") && (value_b!=0))
//                    result = functionDiv( value_a, value_b );
//                noError = false;
//
//            }
//            catch( Exception e){
//                e.printStackTrace();
//                noError = false;
//            }
//
//            if( noError ) {
//                doSetResult(response, result);
//                return;
//            }
//            else
//                doSetError(response);
//        }

    }

    protected void doSetResult( HttpServletResponse response, double result ) throws IOException {
        String reply = "{\"error\":0,\"result\":" + Double.toString(result) + "}";

        response.getWriter().print(reply);//reply.getBytes("UTF-8") );

        //response.getWriter().(TemplateProcessor.instance().getPage("calc", ));

        //response.getWriter().print(TemplateProcessor.instance().getPage("calc", pageVariables));
        //Map<String, Object> pageVariables = createPageVariablesMap(request);

        //??????
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus( HttpServletResponse.SC_OK );
    }

    protected void doSetError( HttpServletResponse response ) throws IOException {
        String reply = "{\"error\":1}";
        response.getOutputStream().write( reply.getBytes("UTF-8") );
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus( HttpServletResponse.SC_OK );
    }

    protected double functionSum( double a, double b ) {
        return a + b;
    }

    protected double functionDif( double a, double b ) {
        return a - b;
    }

    protected double functionMul( double a, double b ) {
        return a * b;
    }

    protected double functionDiv( double a, double b ) {
        return a / b;
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, String a, String b, String op) {
        Map<String, Object> pageVariables = new HashMap<>();

        double value_a = 0;
        double value_b = 0;
        boolean noError = true;

        try {
            value_a = Double.parseDouble(a);
            value_b = Double.parseDouble(b);
        }
        catch ( Exception e ) {
            e.printStackTrace();
            noError = false;
        }

        if ( noError ) {
            double result = 0;
            try {
                if (op.equals("+"))
                    result = functionSum( value_a, value_b );
                if (op.equals("-"))
                    result = functionDif( value_a, value_b );
                if (op.equals("*"))
                    result = functionMul( value_a, value_b );
                if (op.equals("/") && (value_b!=0))
                    result = functionDiv( value_a, value_b );
            }
            catch( Exception e){
                e.printStackTrace();
                noError = false;
            }

            if( noError ) {
                pageVariables.put("result", Double.toString(result)); //Double.toString(result));
            }
            else
                pageVariables.put("result", "error");
        }
        return pageVariables;
    }
}
