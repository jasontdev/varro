/*
 * Copyright (c) 2018. Jason Telfer.
 */

package com.jmtelfer.varro.session;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CurrentUser implements Serializable {
    private Long id = -1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void logout() {
        this.id = -1L;
    }
}
