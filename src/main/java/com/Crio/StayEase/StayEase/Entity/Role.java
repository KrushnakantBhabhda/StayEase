package com.Crio.StayEase.StayEase.Entity;



import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Enum representing different user roles and their associated permissions in the system.
 *
 * <p>This enum defines various roles within the application, each with a specific set of permissions.
 * The roles are categorized as ADMIN, MANAGER, and CUSTOMER, and each role is associated with one or
 * more permissions that define what actions the role can perform.</p>
 *
 * <p>Each role provides a list of authorities that include both the role name and its permissions,
 * which are used by Spring Security for authorization checks.</p>
 *
 * @see Permission
 */

public enum Role {

    /**
     * Role with administrative privileges, including both read and write access.
     */
    ADMIN,
    /**
     * Role with management privileges, including both read and write access.
     */
    MANAGER,
    /**
     * Role with customer privileges, including read access.
     */
    CUSTOMER
    /**
     * The set of permissions associated with this role.
     */
   
  
}