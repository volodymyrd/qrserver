// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gmail.volodymyrdotsenko.qr.domain;

import com.gmail.volodymyrdotsenko.qr.domain.UserToken;
import javax.persistence.Entity;
import javax.persistence.Table;

privileged aspect UserToken_Roo_Jpa_Entity {
    
    declare @type: UserToken: @Entity;
    
    declare @type: UserToken: @Table(name = "tokens");
    
}
