package tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class IfEmptyPutAsteriskTag extends TagSupport {

   private String field;

   public void setField(String field) {
      this.field = field;
   }

   @Override
   public int doStartTag() throws JspException {
      try {
         JspWriter out = pageContext.getOut();
         if (field == null || field.isEmpty()) {
            out.print("<span class='asterisk'> *</span>");
         }
      } catch (IOException e) {
         System.err.println(e);
      }

      return SKIP_BODY;
   }

}
