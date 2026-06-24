package com.portfolio.cafeorderservice.menu;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MenuCreateRequest {

    @NotBlank(message = "메뉴 이름은 필수입니다.")
    private String name;

    @Min(value = 1, message = "가격은 1원 이상이어야 합니다.")
    private int price;

    @NotBlank(message = "카테고리는 필수입니다.")
    private String category;

    private String description;
}