package com.studygroup.util;

import com.studygroup.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtClaimsUtil {

    public static List<SimpleGrantedAuthority> ClaimsMapToList(Map<String,Object> claims) {

        Map<String, Object> authoritiesMap = new HashMap<>(claims);

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
            for (int i = 0; i < Role.values().length; i++) {
                Role role = Role.values()[i];
                if (authoritiesMap.containsKey(role.name())) {
                authorityList.add(new SimpleGrantedAuthority((String) authoritiesMap.get(role.name())));
            }
        }
        return authorityList;
    }
    public static Map<String,Object> SimpleGrantedListToMap(List<SimpleGrantedAuthority> authorities){
        Map<String, Object> authoritiesMap = new HashMap<>();

        for (SimpleGrantedAuthority authority : authorities) {
            authoritiesMap.put(authority.getAuthority(), authority.getAuthority());
        }

        return authoritiesMap;
    }
}
