package com.example.univbet;

import beans.ChangePageBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("changeBean")
@SessionScoped
public class ChangePageDto implements Serializable {

    @EJB
    ChangePageBean changePageBean;

    public void setPageId(int pageId)
    {
        this.changePageBean.setPageId(pageId);
    }

    public int getPageId()
    {
        return this.changePageBean.getPageId();
    }
}
