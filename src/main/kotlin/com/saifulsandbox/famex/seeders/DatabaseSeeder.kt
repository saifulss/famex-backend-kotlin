package com.saifulsandbox.famex.seeders

import com.github.javafaker.Faker
import com.saifulsandbox.famex.services.CategoryService
import com.saifulsandbox.famex.services.DatabaseService
import com.saifulsandbox.famex.services.ExpenseClaimService
import com.saifulsandbox.famex.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DatabaseSeeder : ApplicationRunner {
    @Autowired
    lateinit var databaseService: DatabaseService

    @Autowired
    lateinit var categoryService: CategoryService

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var expenseClaimService: ExpenseClaimService

    private val faker = Faker()

    override fun run(args: ApplicationArguments?) {
        if (databaseService.areAllTablesEmpty()) {
            println("DatabaseSeeder: Attempting to seed...")
            seedCategoriesTable()
            seedUsersTable()
            seedExpenseClaimsTable()
        } else {
            println("DatabaseSeeder: At least one database table is not empty. Skipping seeding...")
        }
    }

    private fun seedCategoriesTable() {
        val categoryNames = listOf("Cab", "Groceries", "Diapers", "Formula Milk")
        categoryNames.map { categoryService.createNewCategory(it) }
    }

    private fun seedUsersTable() {
        for (i in 1..10) {
            if (i == 1) {
                userService.createNewUser(
                        "test_user@example.com",
                        "secret",
                        "Test User"
                )
                continue
            }

            val name = faker.name()
            val fullName = "${name.firstName()} ${name.lastName()}"
            val displayName = fullName.toLowerCase().replace(" ", "_")
            val email = "$displayName@example.com"
            userService.createNewUser(
                    email,
                    "secret",
                    fullName
            )
        }
    }

    private fun seedExpenseClaimsTable() {
        val categoryIds = categoryService.getAllIds()
        val numCategoryIds = categoryIds.size

        val userIds = userService.getAllIds()
        val numUserIds = userIds.size

        for (i in 1..20) {
            val randomCategoryListIndex = faker.number().numberBetween(0, numCategoryIds)
            val randomCategoryId = categoryIds[randomCategoryListIndex]

            val randomUserListIndex = faker.number().numberBetween(0, numUserIds)
            val randomUserId = userIds[randomUserListIndex]

            val randomNumber = faker.number().numberBetween(1000, 10000)
            expenseClaimService.createNewExpenseClaim(
                    randomNumber.toLong(),
                    randomCategoryId,
                    faker.lorem().words(5).joinToString(" "),
                    randomUserId
            )
        }
    }
}