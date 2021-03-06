package com.github.habiteria.integration.controller.annotations;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;

/**
 * @author Alex Ivchenko
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(produces = HAL_JSON_VALUE)
@RestController
public @interface Rest {
}
