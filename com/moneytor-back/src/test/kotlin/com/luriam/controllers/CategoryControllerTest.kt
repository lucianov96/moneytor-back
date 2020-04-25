package com.luriam.controllers

import com.luriam.model.Category
import com.luriam.providers.ObjectMapperProvider
import com.luriam.providers.ValidatorProvider
import com.luriam.services.CategoryService
import com.luriam.services.UserService
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import spark.Request
import spark.Response

class CategoryControllerTest {
    private val mapper = ObjectMapperProvider().get()
    private val categoryService = mock(CategoryService::class.java)
    private val validator = ValidatorProvider().get()
    private val userService = mock(UserService::class.java)
    private val categoryController = CategoryController(mapper, validator, categoryService, userService)
    private val request = mock(Request::class.java)
    private val response = mock(Response::class.java)
    private val categoryList = listOf(
        Category(
            1, "mock", "short", 0
        ),
        Category(
            2, "mock child", "short", 0, 1
        )
    )

    @Test
    fun getAllCategories() {
        // GIVEN
        `when`(categoryService.getAll()).thenReturn(categoryList)

        // WHEN
        val response = categoryController.getAllCategories(request, response)

        assertEquals(categoryList, response)
    }
}
