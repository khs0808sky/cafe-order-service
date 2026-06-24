package com.portfolio.cafeorderservice.menu;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/api/menus")
    public List<Menu> getMenus() {
        return menuService.getMenus();
    }

    @GetMapping("/api/menus/{id}")
    public Menu getMenu(@PathVariable Long id) {
        return menuService.getMenu(id);
    }

    @PostMapping("/api/menus")
    public Menu createMenu(@Valid @RequestBody MenuCreateRequest request) {
        return menuService.createMenu(request);
    }

    @PatchMapping("/api/menus/{id}")
    public Menu updateMenu(
            @PathVariable Long id,
            @Valid @RequestBody MenuUpdateRequest request
    ) {
        return menuService.updateMenu(id, request);
    }

    @DeleteMapping("/api/menus/{id}")
    public void deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
    }
}