package beans;

import javafx.scene.control.TextFormatter;

import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Managed bean.
 * @author w3spoint
 */
@Stateless
public class ChangePageBean{

    private int pageId;

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
       this.pageId = pageId;
        System.out.println(pageId);
    }


    }


