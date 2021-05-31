package com.c.refactoring.menuexamples;

import java.util.Arrays;
import java.util.List;

public class MenuAccess {

    public void setAuthorizationsInEachMenus(
            List<MenuItem> menuItemsList, Role[] roles) {

        if (roles == null)
            return;

        menuItemsList.forEach(menuItem->
                setAccessMenuItem(roles, menuItem)
        );
    }

    private void setAccessMenuItem(Role[] roles, MenuItem menuItem) {

        if (doesHaveUserAccessRole(roles, menuItem.getReadAccessRole())) {
            menuItem.setAccess(Constants.READ);
            menuItem.setVisible(true);
        }

        if (doesHaveUserAccessRole(roles, menuItem.getWriteAccessRole())) {
            menuItem.setAccess(Constants.WRITE);
            menuItem.setVisible(true);
        }
    }

    private boolean doesHaveUserAccessRole(Role[] roles, String accessRole) {
        return Arrays.stream(roles)
                .anyMatch(roleItem -> roleItem.getName().equals(accessRole));
    }

}
