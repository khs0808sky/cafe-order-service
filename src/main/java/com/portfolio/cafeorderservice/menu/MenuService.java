package com.portfolio.cafeorderservice.menu;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getMenus() {
        return menuRepository.findByActiveTrue();
    }

    public Menu getMenu(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 메뉴입니다."));
    }

    public Menu createMenu(MenuCreateRequest request) {
        Menu menu = new Menu(
                request.getName(),
                request.getPrice(),
                request.getCategory(),
                request.getDescription()
        );

        return menuRepository.save(menu);
    }

    @Transactional
    public Menu updateMenu(Long id, MenuUpdateRequest request) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 메뉴입니다."));

        menu.update(
                request.getName(),
                request.getPrice(),
                request.getCategory(),
                request.getDescription()
        );

        return menu;
    }

    @Transactional
    public void deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 메뉴입니다."));

        menu.deactivate();
    }
}