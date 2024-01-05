package com.barux.progettoSpring.publisher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDTO {
    @Transient
    private Integer id;

    @NotBlank(message = "name can't be blank")
    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "foundedAt date is required")
    @PastOrPresent(message = "founding date must be in the past or in the present")
    private Date foundedAt;
}