/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.sharedqueue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
public class SimpleHttpHandler extends AbstractHandler {

   private final demo.sharedqueue.Queue queue;

   /**
    *  Description of the Field
    */
   public static final String ACTION = "/webapp";

   /**
    *  Description of the Field
    */
   public static final String ACTION_ADDWORK = "/addWork";

   /**
    *  Description of the Field
    */
   public static final String ACTION_GETINFO = "/getInfo";

   /**
    *  Description of the Field
    */
   public static final String UNITS_OF_WORK = "unitsOfWork";

   public SimpleHttpHandler(Queue queue) {
      this.queue = queue;
   }

   public final void handle(String target, HttpServletRequest request,
         HttpServletResponse response, int dispatch) throws IOException,
         ServletException {
      Request base_request = (request instanceof Request) ? (Request) request
            : HttpConnection.getCurrentConnection().getRequest();
      if (target.equals(ACTION_ADDWORK)) {
         doAddWork(base_request, response);
      }
      else if (target.equals(ACTION_GETINFO)) {
         doGetInfo(base_request, response);
      }
   }

   private final int getIntForParameter(HttpServletRequest request,
         final String name) {
      String param = request.getParameter(name);
      try {
         return param == null ? 0 : Integer.parseInt(param);
      }
      catch (NumberFormatException nfe) {
         return 0;
      }
   }

   private final void doAddWork(Request request, HttpServletResponse response)
          throws IOException {
      final int unitsOfWork = getIntForParameter(request, UNITS_OF_WORK);
      final Thread processor = new Thread(
               new Runnable() {
                  public void run() {
                     for (int i = 0; i < unitsOfWork; i++) {
                        queue.addJob();
                        // added slight delay to improve visuals
                        try {
                           Thread.sleep(50);
                        }
                        catch (InterruptedException ie) {
                           System.err.println(ie.getMessage());
                        }
                     }
                  }
               });
      processor.start();
      response.sendRedirect(ACTION);
   }

   private final void doGetInfo(Request request, HttpServletResponse response)
          throws IOException {
      response.setContentType("text/xml");
      response.setStatus(HttpServletResponse.SC_OK);

      response.getWriter()
            .println(
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
      response.getWriter().println("<root>");
      response.getWriter().println(queue.getXmlData());
      response.getWriter().println("</root>");
      request.setHandled(true);
   }
}
