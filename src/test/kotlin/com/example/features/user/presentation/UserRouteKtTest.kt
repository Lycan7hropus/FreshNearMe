package com.example.features.user.presentation

import com.example.features.user.domain.usecases.*
import com.example.features.user.presentation.models.BasicUserDto
import com.example.plugins.configureRouting
import com.example.services.MockOfferService
import com.example.services.MockUserDtoService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.mockk.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import org.koin.ktor.ext.getKoin

class UserRoutesTest : FunSpec(){

     var getUserOffersUseCase: GetUserOffersUseCase = mockk()
     var getUserInfoUseCase: GetUserInfoUseCase  = mockk()
     var userWishlistUseCase: UserWishlistUseCase  = mockk()
     var saveUserUseCase: SaveUserUseCase  = mockk()
     var updateUserDataUseCase: UpdateUserDataUseCase = mockk()

    private val mockUserDtoService = MockUserDtoService()
    private val fakeUser = mockUserDtoService.randomBasicUserDto()

//    private val testModule = module {
//        single { getUserOffersUseCase }
//        single { getUserInfoUseCase }
//        single { userWishlistUseCase }
//        single { saveUserUseCase }
//        single { updateUserDataUseCase }
//    }
    init {


        test("GET /{userId} should return BasicUserDto") {
            testApplication {
                application {
//                    configureRouting()
//                    startKoin {
//                        modules(testModule)
//                    }
                    var getUserOffersUseCase: GetUserOffersUseCase = mockk()
                    var getUserInfoUseCase: GetUserInfoUseCase  = mockk()
                    var userWishlistUseCase: UserWishlistUseCase  = mockk()
                    var saveUserUseCase: SaveUserUseCase  = mockk()
                    var updateUserDataUseCase: UpdateUserDataUseCase = mockk()

                    coEvery {
                        getUserInfoUseCase.getBasicInfo(fakeUser.id)
                    } returns fakeUser


                    routing {
                        userRoutes(getUserOffersUseCase,getUserInfoUseCase,userWishlistUseCase,saveUserUseCase,updateUserDataUseCase)
                        //userRoutes()
                        //userRoutes(getKoin().get(),getKoin().get(),getKoin().get(), getKoin().get(),  getKoin().get())
                        //userRoutes(getKoin().inject<GetUserOffersUseCase>().value,getKoin().inject<GetUserInfoUseCase>().value,getKoin().inject<UserWishlistUseCase>().value, getKoin().inject<SaveUserUseCase>().value, getKoin().inject<UpdateUserDataUseCase>().value)
                    }
                }
                val response = client.get("/user/${fakeUser.id}")
                println(response.bodyAsText())
                response.status shouldBe HttpStatusCode.OK

            }
        }

        afterTest {

            clearMocks(
                getUserOffersUseCase,
                getUserInfoUseCase,
                userWishlistUseCase,
                saveUserUseCase,
                updateUserDataUseCase,
            )
            stopKoin()
        }
        afterSpec {
            coVerify() {
                getUserInfoUseCase.getBasicInfo(fakeUser.id)
            }
            stopKoin()
        }
    }
}
