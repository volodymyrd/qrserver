// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gmail.volodymyrdotsenko.qr.domain;

import com.gmail.volodymyrdotsenko.qr.domain.UserData;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect UserData_Roo_Jpa_Entity {
    
    declare @type: UserData: @Entity;
    
    declare @type: UserData: @Table(name = "users");
    
    @Version
    @Column(name = "version")
    private Integer UserData.version;
    
    public Integer UserData.getVersion() {
        return this.version;
    }
    
    public void UserData.setVersion(Integer version) {
        this.version = version;
    }
    
}
