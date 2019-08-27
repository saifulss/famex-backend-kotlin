package com.saifulsandbox.famex.controllers

import com.saifulsandbox.famex.dtofactories.UserDtoFactory
import com.saifulsandbox.famex.dtos.UserDto
import com.saifulsandbox.famex.requestbodies.UserRequestBody
import com.saifulsandbox.famex.services.UserService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    // index
    // show
    // store
    // update
    // destroy

    @GetMapping
    fun index(): List<UserDto> = userService.getAll().map { UserDtoFactory.createFromEntity(it) }

    @PostMapping
    fun store(@RequestBody requestBody: UserRequestBody): UserDto = UserDto(
            userService.createNewUser(
                    requestBody.email,
                    requestBody.password,
                    requestBody.displayName
            )
    )

}