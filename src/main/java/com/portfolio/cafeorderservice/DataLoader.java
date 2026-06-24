package com.portfolio.cafeorderservice;

import com.portfolio.cafeorderservice.menu.Menu;
import com.portfolio.cafeorderservice.menu.MenuRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final MenuRepository menuRepository;

    public DataLoader(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void run(String... args) {
        menuRepository.save(new Menu("아메리카노", 4000, "COFFEE", "기본 아이스 아메리카노"));
        menuRepository.save(new Menu("카페라떼", 4500, "COFFEE", "우유가 들어간 커피"));
        menuRepository.save(new Menu("바닐라라떼", 5000, "COFFEE", "바닐라 시럽이 들어간 라떼"));
        menuRepository.save(new Menu("초코라떼", 4800, "NON_COFFEE", "초코 맛 음료"));
        menuRepository.save(new Menu("딸기스무디", 5500, "ADE_SMOOTHIE", "딸기 스무디"));
    }
}