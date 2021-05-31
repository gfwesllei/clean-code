package com.c.refactoring.menuexamples;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuAccessTest {

    @Test
    public void testSetAuthorizationsInEachMenus() {

        Role[] userRoles = { new Role("MenuARead"), new Role("MenuBWrite"),
                new Role("MenuCRead"), new Role("MenuCWrite") };

        MenuItem menuItemA = new MenuItem("A", "MenuARead", "MenuAWrite");
        MenuItem menuItemB = new MenuItem("B", "MenuBRead", "MenuBWrite");
        MenuItem menuItemC = new MenuItem("C", "MenuCRead", "MenuCWrite");
        MenuItem menuItemD = new MenuItem("D", "MenuDRead", "MenuDWrite");

        MenuItem[] menuItemsArray = {
                menuItemA,
                menuItemB,
                menuItemC,
                menuItemD
        };

        createMenuAndSetAutorizationRoles(menuItemsArray, userRoles);

        assertEquals(Constants.READ, menuItemA.getAccess());
        assertTrue(menuItemA.isVisible());

        assertEquals(Constants.WRITE, menuItemB.getAccess());
        assertTrue(menuItemB.isVisible());

        assertEquals(Constants.WRITE, menuItemC.getAccess());
        assertTrue(menuItemC.isVisible());

        assertNull(menuItemD.getAccess());
        assertFalse(menuItemD.isVisible());

    }

    private void createMenuAndSetAutorizationRoles(MenuItem[] menuItemsArray, Role[] userRoles) {
        List<MenuItem> menuItems = Arrays.asList(menuItemsArray);

        MenuAccess menuAccess = new MenuAccess();

        menuAccess.setAuthorizationsInEachMenus(menuItems, userRoles);
    }
}
