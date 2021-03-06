// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.gmail.volodymyrdotsenko.qr.web;

import com.gmail.volodymyrdotsenko.qr.domain.UserData;
import com.gmail.volodymyrdotsenko.qr.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<UserData, String> ApplicationConversionServiceFactoryBean.getUserDataToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.gmail.volodymyrdotsenko.qr.domain.UserData, java.lang.String>() {
            public String convert(UserData userData) {
                return new StringBuilder().append(userData.getFirstName()).append(' ').append(userData.getLastName()).append(' ').append(userData.getEmail()).append(' ').append(userData.getPassword()).toString();
            }
        };
    }
    
    public Converter<String, UserData> ApplicationConversionServiceFactoryBean.getIdToUserDataConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.gmail.volodymyrdotsenko.qr.domain.UserData>() {
            public com.gmail.volodymyrdotsenko.qr.domain.UserData convert(java.lang.String id) {
                return UserData.findUserData(id);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getUserDataToStringConverter());
        registry.addConverter(getIdToUserDataConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
