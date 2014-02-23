package com.gmail.volodymyrdotsenko.qr.domain;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "tokens", versionField = "")
@RooSerializable
public class UserToken {
	
	@Id
	private String token;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ref_user_id")
	private UserData user;
}