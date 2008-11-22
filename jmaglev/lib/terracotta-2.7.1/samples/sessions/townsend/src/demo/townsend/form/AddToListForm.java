/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.townsend.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *  AddToListForm represents the form data submitted from the display page.
 *  The ActionServlet populates this form when a request for add is received
 *  from the display page.
 *
 *@author    Terracotta, Inc.
 */
public class AddToListForm extends ActionForm {

   //private Product product;
   private String id;

   public AddToListForm() {
      super();
      resetFields();
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

   public ActionErrors validate(ActionMapping mapping, HttpServletRequest req) {

      ActionErrors errors = new ActionErrors();

      if (id == null) {
         errors.add(ActionMessages.GLOBAL_MESSAGE,
               new ActionMessage("global.error.addtolist.requiredfield", "product"));
      }
      return errors;
   }

   public void reset(ActionMapping mapping, HttpServletRequest request) {
      resetFields();
   }

   protected void resetFields() {
      id = "";
   }
}

